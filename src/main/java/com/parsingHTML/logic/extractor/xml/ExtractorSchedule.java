package com.parsingHTML.logic.extractor.xml;

import com.parsingHTML.logic.element.DayName;
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
 * Используется для получения информации о расписание из xml.
 */
public class ExtractorSchedule {
    private static final Logger LOGGER = LogManager.getLogger(ExtractorSchedule.class);
    private Document document;
    XPath xPath = XPathFactory.newInstance().newXPath();

    public ExtractorSchedule(Document document) {
        this.document = document;
    }

    /**
     * Применение expression на {@link ExtractorSchedule#document}.
     *
     * @param expression выражение для получения NodeList.
     * @return результат.
     */
    public NodeList select(String expression) {
        try {
            NodeList evaluate = (NodeList) xPath.compile(expression).evaluate(document, XPathConstants.NODESET);
            LOGGER.debug("select return " + evaluate);
            return evaluate;
        } catch (XPathExpressionException e) {
            LOGGER.warn("Failed select " + expression, e);
            return null;
        }
    }

    /**
     * Получить все уроки на конкретный день.
     *
     * @param dayName Имя дня.
     * @return все уроки на этот день.
     */
    public ArrayList<Lesson> extractLesson(final DayName dayName) {
        NodeList select = select(XPathExpression.selectLesson(dayName));
        ArrayList<Lesson> lessons = new ArrayList<>();
        for (int i = 0; i < select.getLength(); i++) {
            lessons.add(ConverterSchedule.convertLesson(select.item(i)));
        }
        return lessons;
    }


    public DayTime extractDayTime(final DayName dayName, final int number) {
        LOGGER.debug("extractDayTime dayName " + dayName.getNameRu() + " number = " + number);
        NodeList select = select(XPathExpression.selectLessonTime(dayName, number));
        Node item = null;
        if (select.getLength() == 1) {
            item = select.item(0);
        } else {
            LOGGER.warn("Failed extractDayTime select.getLength() = " + select.getLength());
        }
        return ConverterSchedule.convertDayTime(item);
    }

    public ArrayList<Lesson> extractLessonWhitTime(final DayName dayName) {
        ArrayList<Lesson> lessons = extractLesson(dayName);
        for (Lesson lesson : lessons) {
            DayTime dayTime = extractDayTime(dayName, lesson.getNumber());
            lesson.setTime1(dayTime.getTimeFirstLesson());
            lesson.setTime2(dayTime.getTimeSecondLesson());
        }
        return lessons;
    }

}
