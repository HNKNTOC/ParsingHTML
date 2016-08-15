package com.parsingHTML.logic;

import com.parsingHTML.logic.element.DayName;
import com.parsingHTML.logic.element.ElementFactory;
import com.parsingHTML.logic.element.ElementJsoupFactory;
import com.parsingHTML.logic.lessone.Lesson;
import com.parsingHTML.logic.lessone.Schedule;
import com.parsingHTML.logic.selector.SelectorLink;
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
import java.util.List;

/**
 * Main
 */
public class Main {
    private static ElementFactory ElementFactory = new ElementJsoupFactory();
    private static final String path = "src\\test\\resources\\com.parsingHTML.logic.out";
    private static final String nameOUTFile = "output.xml";
    private static String charsetName = "UTF-8";

    public static void main(String[] args) throws TransformerException, ParserConfigurationException, IOException {
        testSchedule();
    }

    private static void testSchedule() throws IOException, TransformerException {
        Schedule schedule = new Schedule(start());
        List<Lesson> lessons = schedule.extractLesson(DayName.MONDAY);
        for (Lesson lesson : lessons) {
            System.out.println(lesson);
        }
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
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }
}
