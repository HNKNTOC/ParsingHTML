package com.parsingHTML.logic.extractor.xml;


import com.parsingHTML.logic.element.DayName;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * тест для ExtractorSchedule.
 */
public class ExtractorScheduleTest {

    private ExtractorSchedule extractorSchedule;
    private Document doc;

    public ExtractorScheduleTest() {
        try {
            File file = new File("src\\main\\resources\\xml\\example.xml");
            DocumentBuilderFactory dbFactory
                    = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(file);
            extractorSchedule = new ExtractorSchedule(doc);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void select() throws Exception {
        NodeList select = extractorSchedule.select(XPathExpression.selectLesson(DayName.WEDNESDAY));
        assertTrue(select.getLength() == 4);
    }

    @Test
    public void extractLesson() throws Exception {
        ArrayList<Lesson> lessons = extractorSchedule.extractLesson(DayName.TUESDAY);
        assertTrue(lessons.size() == 6);
    }

    @Test
    public void extractDayTime() throws Exception {
        DayTime dayTime = extractorSchedule.extractDayTime(DayName.FRIDAY, 2);
        final String start1 = "10:15";
        final String start2 = "11:05";
        final String end1 = "11:00";
        final String end2 = "11:50";

        assertEquals(dayTime.getStart1(), start1);
        assertEquals(dayTime.getStart2(), start2);
        assertEquals(dayTime.getEnd1(), end1);
        assertEquals(dayTime.getEnd2(), end2);

        assertEquals(dayTime.getTimeFirstLesson(), start1 + "-" + end1);
        assertEquals(dayTime.getTimeSecondLesson(), start2 + "-" + end2);

        final String separator = "_";

        assertEquals(dayTime.getTimeFirstLesson(separator), start1 + separator + end1);
        assertEquals(dayTime.getTimeSecondLesson(separator), start2 + separator + end2);

    }

}