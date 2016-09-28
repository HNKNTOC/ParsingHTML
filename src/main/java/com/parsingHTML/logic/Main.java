package com.parsingHTML.logic;

import com.parsingHTML.logic.element.DayName;
import com.parsingHTML.logic.extractor.xml.ExtractorSchedule;
import com.parsingHTML.logic.extractor.xml.LessonTime;
import com.parsingHTML.logic.selector.SelectorLink;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Element;
import org.w3c.dom.Document;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Main
 */
public class Main {
    private static final Logger LOGGER = LogManager.getLogger(Main.class);
    private static final String nameOUTFile = "output.xml";
    private static final String charsetName = "UTF-8";

    public static void main(String[] args) throws TransformerException, ParserConfigurationException, IOException {
        start();
    }

    private static void testSchedule() throws IOException, TransformerException {
        LessonTime lessonTime1 = ExtractorSchedule.extractDayTime(DayName.THURSDAY, 7, start());
        System.out.println(lessonTime1);
    }

    public static void testingGoLink() throws IOException {
        SelectorLink selectorLink = new SelectorLink();
        show(selectorLink);
        selectorLink.goButton(1);
        show(selectorLink);
        selectorLink.goButton(1);
        show(selectorLink);
        selectorLink.goButton(1);
        show(selectorLink);
    }

    private static void show(SelectorLink selectorLink) throws IOException {
        ArrayList<String> button = selectorLink.parsingButtonNames();
        System.out.println("===========");
        for (String s : button) {
            System.out.println(s);
        }
        System.out.println("===========");

    }

    private static Document start() throws IOException, TransformerException {

        File timeContent = getFile("rasp.bukep.ru.html");
        File scheduleContent = getFile("rasp.bukep.ru2.html");

        Element schedule = ParsingHTML.parsingSchedule(
                new FileInputStream(timeContent), new FileInputStream(scheduleContent), charsetName);

        Document document = ParsingHTML.transformation(schedule);

        saveOut(document);

        return document;

    }

    private static void saveOut(Document doc) throws TransformerException {
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        Result output = new StreamResult(createFile(nameOUTFile));
        Source input = new DOMSource(doc);

        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://.html.apache.org/xslt}indent-amount", "2");
        transformer.transform(input, output);
    }


    private static File getFile(String name) {
        return new File("src\\test\\resources\\html\\" + name);
    }

    private static File createFile(String name){
        File file = new File("src\\test\\resources\\html\\" + name);
        try {
            if (!file.createNewFile()) LOGGER.warn("Failed create file!! File = " + file.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }
}
