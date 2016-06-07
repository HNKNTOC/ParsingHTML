package com.company.parsingHTML.logic;


import com.company.parsingHTML.logic.file.FileManagerDefault;
import com.company.parsingHTML.logic.loader.LoaderHTML;
import com.company.parsingHTML.logic.loader.LoaderHTMLDefault;
import com.company.parsingHTML.logic.parsing.tag.ParserRoot;
import com.company.parsingHTML.logic.xml.ConstructorDOM;
import com.company.parsingHTML.logic.xml.ElementXML;
import org.jsoup.Jsoup;
import org.w3c.dom.Document;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * Created by Nikita on 03.05.2016.
 */
public class Main {
    public static void main(String[] args) throws TransformerException, ParserConfigurationException, IOException {
        ConstructorDOM constructorDOM = new ConstructorDOM();

        org.jsoup.nodes.Document parse0 = Jsoup.parse(getFile("rasp.bukep.ru.html"), null);
        org.jsoup.nodes.Document parse1 = Jsoup.parse(getFile("rasp.bukep.ru2.html"), null);
        ParserRoot parserRoot = new ParserRoot();
        ElementXML schedule = new ElementXML("schedule");
        startingSetting(schedule);

        ElementXML weekTime = parserRoot.parsing(parse0);
        ElementXML groupLesson = parserRoot.parsing(parse1);

        schedule.addChildren(weekTime);
        schedule.addChildren(groupLesson);

        constructorDOM.newElement(schedule);
        Document designing = constructorDOM.getXML();

        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        Result output = new StreamResult(new File("src\\main\\resources\\test\\output.xml"));
        Source input = new DOMSource(designing);

        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        transformer.transform(input, output);
    }

    private static void startingSetting(ElementXML elementXML) {
        ElementXML updateTime = new ElementXML("updateTime");
        updateTime.setText(new Date().toString());
        elementXML.addChildren(updateTime);

        ElementXML university = new ElementXML("university");
        university.addAttribute("dayName","БУКЭП");
        elementXML.addChildren(university);
    }



    public static File getFile(String name){
        return new FileManagerDefault().getFile(name);
    }

    /**
     * Загрузка html
     * @param stringURL
     */
    public static void loadHTML(String stringURL){
        LoaderHTML loaderHTMLDefault = new LoaderHTMLDefault();
        File file = loaderHTMLDefault.loadHTML("http://rasp.bukep.ru/");
    }

}
