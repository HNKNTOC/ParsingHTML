package com.parsingHTML.logic.extractor.xml;

import com.parsingHTML.logic.element.DayName;
import com.parsingHTML.logic.element.NumeratorName;
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
    private static XPath xPath = XPathFactory.newInstance().newXPath();
    

    /**
     * Применение expression на Document.
     *
     * @param expression выражение для получения NodeList.
     * @return результат.
     */
    public static NodeList executeSelect(final String expression, final Document document) {
        LOGGER.info("executeSelect() expression = " + expression);
        try {
            NodeList evaluate = (NodeList) xPath.compile(expression).evaluate(document, XPathConstants.NODESET);
            LOGGER.debug("executeSelect() return NodeList = " + evaluate.getLength());
            return evaluate;
        } catch (XPathExpressionException e) {
            LOGGER.error("Failed executeSelect() " + expression, e);
            return new MockNodeList();
        }
    }

    /**
     * Получить все уроки на конкретный день.
     *
     * @param numerator Нумератор дня.
     * @param dayName Имя дня.
     * @return все уроки на этот день.
     */
    //TODO Не передовать множиство параметров передать только 1 обьект в котором будут все параметры.
    public static ArrayList<Lesson> extractLesson(final DayName dayName, final NumeratorName numerator, final Document document) {
        NodeList select = executeSelect(XPathExpression.selectLesson(dayName, numerator), document);
        ArrayList<Lesson> lessons = new ArrayList<>();
        for (int i = 0; i < select.getLength(); i++) {
            lessons.add(ConverterSchedule.convertLesson(select.item(i)));
        }
        return lessons;
    }


    public static LessonTime extractDayTime(final DayName dayName, final int number, final Document document) throws Exception {
        LOGGER.debug("extractDayTime dayName " + dayName + " number = " + number);
        NodeList select = executeSelect(XPathExpression.selectLessonTime(dayName, number), document);
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

    public static ArrayList<Lesson> extractLessonWhitTime(final DayName dayName, NumeratorName numerator, final Document document) throws Exception {
        ArrayList<Lesson> lessons = extractLesson(dayName, numerator, document);
        for (Lesson lesson : lessons) {
            LessonTime lessonTime = extractDayTime(dayName, lesson.getNumber(), document);
            lesson.setTime1(lessonTime.getTimeFirstLesson());
            lesson.setTime2(lessonTime.getTimeSecondLesson());
        }
        return lessons;
    }

    /**
     * Используется для возвращения пустого NodeList дабы не возвращать Null.
     */
    private static class MockNodeList implements NodeList {
        private static final Logger LOGGER = LogManager.getLogger(MockNodeList.class);

        @Override
        public Node item(int index) {
            LOGGER.warn("Use item() MockNodeList!!");
            return null;
        }

        @Override
        public int getLength() {
            LOGGER.warn("Use getLength() MockNodeList!!");
            return 0;
        }
    }
}
