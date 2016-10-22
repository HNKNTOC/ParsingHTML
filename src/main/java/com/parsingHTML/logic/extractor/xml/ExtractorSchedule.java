package com.parsingHTML.logic.extractor.xml;

import com.parsingHTML.logic.element.AttributeName;
import com.parsingHTML.logic.element.DayName;
import com.parsingHTML.logic.element.ElementName;
import com.parsingHTML.logic.element.NumeratorName;
import com.parsingHTML.logic.extractor.xml.XPathBuilder.XPathElement;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.util.ArrayList;

/**
 * Используется для получения информации о расписание из Document.
 */
public class ExtractorSchedule {
    private static final Logger LOGGER = LogManager.getLogger(ExtractorSchedule.class);
    private static XPath xPath = XPathFactory.newInstance().newXPath();


    /**
     * Применение expression на Document.
     *
     * @param expression выражение для получения NodeList.
     * @return результат.
     */
    public static NodeList executeSelect(final String expression, final Document document) throws XPathExpressionException {
        LOGGER.info("executeSelect() expression = " + expression);
        try {
            NodeList evaluate = (NodeList) xPath.compile(expression).evaluate(document, XPathConstants.NODESET);
            LOGGER.debug("executeSelect() return NodeList = " + evaluate.getLength());
            return evaluate;
        } catch (XPathExpressionException e) {
            LOGGER.error("Failed executeSelect() " + expression, e);
            throw e;
        }
    }

    /**
     * Получить все уроки по переданным параметрам в xPathLesson.
     *
     * @param xPathLesson Document для получения Lesson.
     * @param dayName     Имя дня.
     * @return все уроки подходящие по переданным параметрам.
     * @throws XPathExpressionException если не удалось получить Lesson.
     */
    public static ArrayList<Lesson> extractLesson(
            final DayName dayName, final XPathElement xPathLesson, final Document doc) throws XPathExpressionException {
        LOGGER.debug("extractDayTime dayName " + dayName + " xPathLesson = " + xPathLesson);
        NodeList select = executeSelect(LessonExpression.createXPathForLesson(dayName, xPathLesson), doc);
        ArrayList<Lesson> lessons = new ArrayList<>();
        for (int i = 0; i < select.getLength(); i++) {
            lessons.add(ConverterSchedule.convertLesson(select.item(i)));
        }
        return lessons;
    }

    /**
     * Получить все уроки на конкретный день.
     *
     * @param dayName       Имя дня.
     * @param numeratorName Нумератор дня.
     * @param doc           Document из которого нужно получить день.
     * @return все уроки подходящие по переданным параметрам.
     * @throws XPathExpressionException если не удалось получить Lesson.
     */
    public static ArrayList<Lesson> extractLesson(
            final DayName dayName, final NumeratorName numeratorName, final Document doc) throws XPathExpressionException {
        LOGGER.debug("extractDayTime dayName " + dayName + " numeratorName = " + numeratorName);
        XPathElement xPathLesson = new XPathElement(ElementName.LESSON)
                .addAttr(AttributeName.NUMERATOR, numeratorName.getName());
        return extractLesson(dayName, xPathLesson, doc);
    }


    public static LessonTime extractDayTime(final DayName dayName, final XPathElement xPathTime, final Document doc) throws Exception {
        LOGGER.debug("extractDayTime dayName " + dayName + " xPathTime = " + xPathTime);
        NodeList select = executeSelect(LessonExpression.createXPathForTime(dayName, xPathTime), doc);
        Node item = null;
        if (select.getLength() == 1) {
            item = select.item(0);
        } else {
            Exception e = new Exception("Failed extractDayTime executeSelect.getLength() = " + select.getLength());
            LOGGER.warn(e);
            throw e;
        }
        return ConverterSchedule.convertDayTime(item);
    }

    public static LessonTime extractDayTime(final DayName dayName, final int number, final Document doc) throws Exception {
        LOGGER.debug("extractDayTimeDayName dayName = " + dayName + " number = " + number);
        XPathElement xPathTime = new XPathElement(ElementName.LESSON_TIME)
                .addAttr(AttributeName.NUMBER, number);
        return extractDayTime(dayName, xPathTime, doc);
    }

    public static ArrayList<Lesson> extractLessonWhitTime(
            final DayName dayName, final XPathElement xPathLesson, final Document doc) throws Exception {
        LOGGER.debug("extractLessonWhitTime dayName = " + dayName + " xPathLesson = " + xPathLesson);
        ArrayList<Lesson> lessons = extractLesson(dayName, xPathLesson, doc);
        for (Lesson lesson : lessons) {
            LessonTime lessonTime = extractDayTime(dayName, lesson.getNumber(), doc);
            lesson.setTime1(lessonTime.getTimeFirstLesson());
            lesson.setTime2(lessonTime.getTimeSecondLesson());
        }
        return lessons;
    }

    public static ArrayList<Lesson> extractLessonWhitTime(final DayName dayName, final NumeratorName numerator, final Document doc) throws Exception {
        LOGGER.debug("extractLessonWhitTime dayName = " + dayName + " numerator = " + numerator);
        XPathElement xPathLesson = new XPathElement(ElementName.LESSON)
                .addAttr(AttributeName.NUMERATOR, numerator.getName());
        return extractLessonWhitTime(dayName, xPathLesson, doc);
    }
}
