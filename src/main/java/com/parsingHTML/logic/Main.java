package com.parsingHTML.logic;


import com.parsingHTML.logic.file.FileManagerDefault;
import com.parsingHTML.logic.loader.LoaderHTML;
import com.parsingHTML.logic.loader.LoaderHTMLDefault;
import com.parsingHTML.logic.parsing.tag.daytime.ParserWeekTime;
import com.parsingHTML.logic.parsing.tag.grouplesson.ParserGroupLesson;
import com.parsingHTML.logic.xml.factory.ElementFactoryJsoup;
import com.parsingHTML.logic.xml.factory.XMLFactory;
import org.jsoup.Jsoup;
import org.jsoup.helper.W3CDom;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

/**
 * Created by Nikita on 03.05.2016.
 */
public class Main {
    private static XMLFactory XMLFactory = new ElementFactoryJsoup();
    private static final String path = "src\\main\\resources\\out";
    private static final String nameOUTFile = "output.xml";

    public static void main(String[] args) throws TransformerException, ParserConfigurationException, IOException {
        start();
    }

    private static void transformation(Document documentDom, Element element) {
        org.jsoup.nodes.Document document = new org.jsoup.nodes.Document("");
        W3CDom w3CDom = new W3CDom();
        document.appendChild(element);
        w3CDom.convert(document, documentDom);
    }

    private static void start() throws IOException, TransformerException {

        org.jsoup.nodes.Document parse0 = Jsoup.parse(getFile("rasp.bukep.ru.html"), null);
        org.jsoup.nodes.Document parse1 = Jsoup.parse(getFile("rasp.bukep.ru2.html"), null);
        Element schedule = XMLFactory.createSchedule();
        Element root = createRoot(schedule);

        ParserWeekTime parserWeekTime = new ParserWeekTime();
        Element weekTime = parserWeekTime.parsing(parse0);
        ParserGroupLesson parserGroupLesson = new ParserGroupLesson();
        Element groupLesson = parserGroupLesson.parsing(parse1);

        root.appendChild(weekTime);
        root.appendChild(groupLesson);

        Document doc = createDOC();
        transformation(doc, schedule);

        saveOut(doc);
    }

    private static void saveOut(Document doc) throws TransformerException {
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        Result output = new StreamResult(createFile(nameOUTFile));
        Source input = new DOMSource(doc);

        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://html.apache.org/xslt}indent-amount", "2");
        transformer.transform(input, output);
    }

    private static Document createDOC() {
        try {
            return DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Element createRoot(Element schedule) {
        Element updateTime = XMLFactory.createUpdateTime();
        schedule.appendChild(updateTime);
        Element university = XMLFactory.createUniversity();
        schedule.appendChild(university);

        return university;
    }


    public static File getFile(String name) {
        FileManagerDefault fileManagerDefault = new FileManagerDefault(new File("src\\main\\resources\\html\\save"));
        return fileManagerDefault.getFile(name);
    }

    private static File createFile(String name){
        FileManagerDefault fileManagerDefault = new FileManagerDefault(new File(path));
        fileManagerDefault.createFile(name);
        return fileManagerDefault.getFile(name);
    }

    /**
     * Загрузка html
     *
     * @param stringURL
     */
    public static void loadHTML(String stringURL) {
        LoaderHTML loaderHTMLDefault = new LoaderHTMLDefault();
        File file = loaderHTMLDefault.loadHTML("http://rasp.bukep.ru/");
    }

}
