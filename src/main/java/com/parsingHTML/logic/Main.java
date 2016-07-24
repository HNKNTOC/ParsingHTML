package com.parsingHTML.logic;


import com.parsingHTML.logic.file.FileManagerDefault;
import com.parsingHTML.logic.loader.LoaderHTML;
import com.parsingHTML.logic.loader.LoaderHTMLDefault;
import com.parsingHTML.logic.parsing.ElementJsoupFactory;
import com.parsingHTML.logic.parsing.XMLFactory;
import org.jsoup.nodes.Element;
import org.w3c.dom.Document;

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
    private static XMLFactory XMLFactory = new ElementJsoupFactory();
    private static final String path = "src\\main\\resources\\out";
    private static final String nameOUTFile = "output.xml";
    private static String charsetName = "UTF-8";

    public static void main(String[] args) throws TransformerException, ParserConfigurationException, IOException {
        start();
    }

    private static void start() throws IOException, TransformerException {

        File timeContent = getFile("rasp.bukep.ru.html");
        File scheduleContent = getFile("rasp.bukep.ru2.html");

        Element schedule = ParsingHTML.parsingSchedule(timeContent, scheduleContent, charsetName);


        saveOut(ParsingHTML.transformation(schedule));

    }

    private static void saveOut(Document doc) throws TransformerException {
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        Result output = new StreamResult(createFile(nameOUTFile));
        Source input = new DOMSource(doc);

        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://html.apache.org/xslt}indent-amount", "2");
        transformer.transform(input, output);
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
