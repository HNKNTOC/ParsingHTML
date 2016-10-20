package com.parsingHTML.logic.extractor.xml;


import com.parsingHTML.logic.element.AttributeName;
import com.parsingHTML.logic.element.DayName;
import com.parsingHTML.logic.element.ElementName;
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
        XPathBuilder.XPathElement xPathLesson = new XPathBuilder.XPathElement(ElementName.LESSON);
        xPathLesson.addAttr(AttributeName.NUMERATOR, NumeratorName.NUMERATOR.getName());

        NodeList select = ExtractorSchedule.executeSelect(
                LessonExpression.createXPathForLesson(DayName.SATURDAY, xPathLesson), doc);
        assertTrue(select.getLength() == 3);
    }

    @Test
    public void extractLessonWednesdayDenominator() throws Exception {
        ArrayList<Lesson> lessons = ExtractorSchedule.extractLesson(DayName.WEDNESDAY, NumeratorName.DENOMINATOR, doc);
        assertTrue(lessons.size() == 1);
        assertEquals(lessons.get(0),
                new Lesson("Информатика 9", "Лабораторное занятие 432, 432", 2,
                        NumeratorName.DENOMINATOR, "ст. преп. Гостищева Т.В.,преп. Чепелева Н.В."));
    }

    @Test
    public void extractLessonTuesdayNumerator() throws Exception {
        ArrayList<Lesson> lessons = ExtractorSchedule.extractLesson(DayName.TUESDAY,
                NumeratorName.NUMERATOR, doc);
        assertTrue(lessons.size() == 3);
        assertEquals(lessons.get(0),
                new Lesson("Русский язык и литература", "Практическое занятие 310н", 2,
                        NumeratorName.NUMERATOR, "преп. Дмитрийчук А.Ю."));
        assertEquals(lessons.get(1),
                new Lesson("Естествознание", "Лекционное занятие 303н", 3,
                        NumeratorName.NUMERATOR, "ст. преп. Абраменко Л.И."));
        assertEquals(lessons.get(2),
                new Lesson("Физика", "Лабораторное занятие 503ан", 4,
                        NumeratorName.NUMERATOR, "асс. Омельченко Е.И."));
    }

    @Test
    public void extractLessonTuesdayEmpty() throws Exception {
        ArrayList<Lesson> lessons = ExtractorSchedule.extractLesson(DayName.FRIDAY, NumeratorName.EMPTY, doc);
        assertTrue(lessons.size() == 3);

        assertEquals(lessons.get(0),
                new Lesson("Информатика 9", "Лабораторное занятие 106, 108, 108", 2,
                        NumeratorName.EMPTY, "преп. Чепелева Н.В.,ст. преп. Гостищева Т.В.,ст. преп. Гостищева Т.В."));
        assertEquals(lessons.get(1),
                new Lesson("История 9", "Практическое занятие 104н", 3,
                        NumeratorName.EMPTY, "ст. преп. Нестерова Л.И."));
        assertEquals(lessons.get(2),
                new Lesson("Обществознание (вкл. экономику и право)", "Практическое занятие 513н", 4,
                        NumeratorName.EMPTY, "асс. Скоков А.Л."));

    }

    @Test
    public void extractLessonTuesdayAll() throws Exception {
        XPathBuilder.XPathElement xPathLesson = new XPathBuilder.XPathElement(ElementName.LESSON);
        ArrayList<Lesson> lessons = ExtractorSchedule.extractLesson(DayName.TUESDAY, xPathLesson, doc);
        assertTrue(lessons.size() == 6);
        assertEquals(lessons.get(0),
                new Lesson("Русский язык и литература", "Практическое занятие 310н", 2,
                        NumeratorName.NUMERATOR, "преп. Дмитрийчук А.Ю."));
        assertEquals(lessons.get(1),
                new Lesson("Русский язык и литература", "Практическое занятие 305н", 2,
                        NumeratorName.DENOMINATOR, "преп. Дмитрийчук А.Ю."));
        assertEquals(lessons.get(2),
                new Lesson("Естествознание", "Лекционное занятие 303н", 3,
                        NumeratorName.NUMERATOR, "ст. преп. Абраменко Л.И."));
        assertEquals(lessons.get(3),
                new Lesson("История 9", "Лекционное занятие 411н", 3,
                        NumeratorName.DENOMINATOR, "ст. преп. Нестерова Л.И."));
        assertEquals(lessons.get(4),
                new Lesson("Физика", "Лабораторное занятие 503ан", 4,
                        NumeratorName.NUMERATOR, "асс. Омельченко Е.И."));
        assertEquals(lessons.get(5),
                new Lesson("Математика: алгебра, начала математического анализа, геометрия", "Практическое занятие 514н", 4,
                        NumeratorName.DENOMINATOR, "ст. преп. Колосова И.В."));

    }


    @Test
    public void extractLessonWhitTime() throws Exception {
        ArrayList<Lesson> lessons = ExtractorSchedule.extractLessonWhitTime(DayName.THURSDAY, NumeratorName.NUMERATOR, doc);
        assertTrue(lessons.size() == 2);
        Lesson lesson = lessons.get(1);

        Lesson outResult = new Lesson("Введение в специальность", "Лекционное занятие 115", 5,
                NumeratorName.NUMERATOR, "асс. Коптелова Л.В.");
        outResult.setTime1("16:20-17:05");
        outResult.setTime2("17:10-17:55");

        assertEquals(lesson, outResult);
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