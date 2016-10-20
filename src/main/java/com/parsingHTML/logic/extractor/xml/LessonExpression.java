package com.parsingHTML.logic.extractor.xml;

import com.parsingHTML.logic.element.AttributeName;
import com.parsingHTML.logic.element.DayName;
import com.parsingHTML.logic.element.ElementName;
import com.parsingHTML.logic.extractor.xml.XPathBuilder.XPathElement;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Класс используется для создания XPath expression для Lesson.
 */
public class LessonExpression {

    private static XPathBuilder builder = new XPathBuilder();
    private static final Logger LOGGER = LogManager.getLogger(LessonExpression.class);

    /**
     * XPathElement для создания пути до Lesson.
     */
    private static final XPathElement[] PATH_ELEMENT_TO_LESSON = {
            new XPathElement(ElementName.SCHEDULE),
            new XPathElement(ElementName.UNIVERSITY),
            new XPathElement(ElementName.GROUP_LESSON),
    };

    /**
     * XPathElement для создания пути до Time.
     */
    private static final XPathElement[] PATH_ELEMENT_TO_TIME = {
            new XPathElement(ElementName.SCHEDULE),
            new XPathElement(ElementName.UNIVERSITY),
            new XPathElement(ElementName.WEEK_TIME),
    };

    /**
     * Добовление XPathElement в builder.
     */
    public static void addXPathElementInPath(XPathElement xPathElement) {
        LOGGER.debug("add xPathElement = " + xPathElement);
        builder.add(xPathElement);
    }

    public static String getXPath() {
        String expression = builder.getExpression();
        LOGGER.debug("getXPath = " + expression);
        builder.clear();
        return expression;
    }

    /**
     * Используется для создания XPathExpression для Lesson.
     * По параметрам в lesson создается XPathExpression если парамер null он игнорируется.
     *
     * @param xPathLesson Lesson на основе которого будет создан XPathExpression.
     * @return XPathExpression созданое на основе lesson.
     */
    public static String createXPathForLesson(final DayName dayName, final XPathElement xPathLesson) {
        LOGGER.debug("CreateXPathLesson DayName = " + dayName + " lesson = " + xPathLesson);
        for (XPathElement pathElement : PATH_ELEMENT_TO_LESSON) {
            addXPathElementInPath(pathElement);
        }
        XPathElement elementLesson = new XPathElement(ElementName.DAY_LESSON);
        elementLesson.addAttr(AttributeName.DAY_NUMBER, String.valueOf(dayName.getDayNumber()));
        addXPathElementInPath(elementLesson);
        addXPathElementInPath(xPathLesson);
        return getXPath();
    }

    public static String createXPathForTime(final DayName dayName, final XPathElement xPathTime) {
        LOGGER.debug("CreateXPathTime DayName = " + dayName + " XPathElement Time = " + xPathTime);
        for (XPathElement pathElement : PATH_ELEMENT_TO_TIME) {
            addXPathElementInPath(pathElement);
        }
        XPathElement elementTime = new XPathElement(ElementName.DAY_TIME);
        elementTime.addAttr(AttributeName.DAY_TIME_NUMBER, overrideDayName(dayName).getDayNumber());
        addXPathElementInPath(elementTime);
        addXPathElementInPath(xPathTime);
        return getXPath();
    }

    /**
     * Переопределение дня.
     * Заменяет FRIDAY,THURSDAY,TUESDAY,WEDNESDAY на MONDAY.
     * MONDAY и SATURDAY возвращает без изменений.
     * Так как расписание времени у FRIDAY,THURSDAY,TUESDAY,WEDNESDAY такое же как и у MONDAY.
     *
     * @param dayName DayName который нужно переопределить.
     * @return переопределенный DayName.
     */
    private static DayName overrideDayName(DayName dayName) {
        if (dayName == DayName.SATURDAY) {
            return DayName.SATURDAY;
        } else {
            return DayName.MONDAY;
        }
    }


}
