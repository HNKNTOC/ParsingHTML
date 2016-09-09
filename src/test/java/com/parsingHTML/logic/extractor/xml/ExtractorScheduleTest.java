package com.parsingHTML.logic.extractor.xml;


import com.parsingHTML.logic.element.DayName;
import com.parsingHTML.logic.element.NumeratorName;
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

    private Document doc;

    public ExtractorScheduleTest() {
        try {
            File file = new File("src\\main\\resources\\xml\\example.xml");
            DocumentBuilderFactory dbFactory
                    = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(file);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void select() throws Exception {
        NodeList select = ExtractorSchedule.executeSelect(XPathExpression.selectLesson(DayName.WEDNESDAY), doc);
        assertTrue(select.getLength() == 4);
    }

    @Test
    public void extractLesson() throws Exception {
        ArrayList<Lesson> lessons = ExtractorSchedule.extractLesson(DayName.TUESDAY, doc);
        assertTrue(lessons.size() == 6);
        Lesson lesson0 = new Lesson("Русский язык и литература", "Практическое занятие 310н", 2, NumeratorName.NUMERATOR, "Name Teacher");
        Lesson lesson1 = new Lesson("Русский язык и литература", "Практическое занятие 305н", 2, NumeratorName.DENOMINATOR, "Name Teacher");
        Lesson lesson2 = new Lesson("Естествознание", "Лекционное занятие 303н", 3, NumeratorName.NUMERATOR, "Name Teacher");
        Lesson lesson3 = new Lesson("История 9", "Лекционное занятие 411н", 3, NumeratorName.DENOMINATOR, "Name Teacher");
        Lesson lesson4 = new Lesson("Физика", "Лабораторное занятие 503ан", 4, NumeratorName.NUMERATOR, "Name Teacher");
        Lesson lesson5 = new Lesson("Математика: алгебра, начала математического анализа, геометрия", "Практическое занятие 514н", 4, NumeratorName.DENOMINATOR, "Name Teacher");
        assertEquals(lessons.get(0), lesson0);
        assertEquals(lessons.get(1), lesson1);
        assertEquals(lessons.get(2), lesson2);
        assertEquals(lessons.get(3), lesson3);
        assertEquals(lessons.get(4), lesson4);
        assertEquals(lessons.get(5), lesson5);

    }

    @Test
    public void extractLessonWhitTime() throws Exception {
        ArrayList<Lesson> lessons = ExtractorSchedule.extractLessonWhitTime(DayName.THURSDAY, doc);
        assertTrue(lessons.size() == 5);
        Lesson lesson = lessons.get(3);
        assertTrue(lesson.getNumber() == 5);
        assertEquals(lesson.getName(), "Введение в специальность");
        assertEquals(lesson.getDescription(), "Лекционное занятие 115");
        assertEquals(lesson.getNumeratorName(), NumeratorName.NUMERATOR);
        assertEquals(lesson.getTeacher(), "Name Teacher");

        assertEquals(lesson.getTime1(), "16:20-17:05");
        assertEquals(lesson.getTime2(), "17:10-17:55");
    }

    @Test
    public void extractDayTime() throws Exception {
        LessonTime lessonTime = ExtractorSchedule.extractDayTime(DayName.FRIDAY, 2, doc);
        final String start1 = "10:15";
        final String start2 = "11:05";
        final String end1 = "11:00";
        final String end2 = "11:50";

        assertEquals(lessonTime.getStart1(), start1);
        assertEquals(lessonTime.getStart2(), start2);
        assertEquals(lessonTime.getEnd1(), end1);
        assertEquals(lessonTime.getEnd2(), end2);

        assertEquals(lessonTime.getTimeFirstLesson(), start1 + "-" + end1);
        assertEquals(lessonTime.getTimeSecondLesson(), start2 + "-" + end2);

        final String separator = "_";

        assertEquals(lessonTime.getTimeFirstLesson(separator), start1 + separator + end1);
        assertEquals(lessonTime.getTimeSecondLesson(separator), start2 + separator + end2);

    }

}