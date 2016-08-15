package com.parsingHTML.logic.lessone;

import com.parsingHTML.logic.element.DayName;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.util.ArrayList;
import java.util.List;

/**
 * Используется для получения информации о расписание из xml.
 */
public class Schedule {
    private static final Logger LOGGER = LogManager.getLogger(Schedule.class);
    private Document document;
    XPath xPath = XPathFactory.newInstance().newXPath();

    public Schedule(Document document) {
        this.document = document;
    }

    /**
     * Применение expression на {@link Schedule#document}.
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
    public List<Lesson> extractLesson(DayName dayName) {
        NodeList select = select(XPathExpression.selectLesson(dayName));
        ArrayList<Lesson> lessons = new ArrayList<>();
        for (int i = 0; i < select.getLength(); i++) {
            lessons.add(ConverterSchedule.convert(select.item(i)));
        }
        return lessons;
    }
}
