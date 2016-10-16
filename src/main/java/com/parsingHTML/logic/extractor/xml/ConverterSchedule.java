package com.parsingHTML.logic.extractor.xml;

import com.parsingHTML.logic.element.AttributeName;
import com.parsingHTML.logic.element.NumeratorName;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jsoup.helper.Validate;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

/**
 * Конвертирует из {@link Node} элементы расписания.
 */
public class ConverterSchedule {
    private static final Logger LOGGER = LogManager.getLogger(ConverterSchedule.class);
    private static final String defaultValueForNumber = "-1";

    public static Lesson convertLesson(Node node) {
        LOGGER.debug("convertLesson = " + node);
        Validate.notNull(node, "Node must not be null!");
        NamedNodeMap attributes = node.getAttributes();

        final String name, teacherNames, description;

        name = toAttributeValue(attributes, AttributeName.LESSON_NAME);
        teacherNames = toAttributeValue(attributes, AttributeName.TEACHER);
        description = toAttributeValue(attributes, AttributeName.DESCRIPTION);

        final int number = Integer.parseInt(toAttributeValue(attributes, AttributeName.NUMBER, defaultValueForNumber));
        final NumeratorName numeratorName = NumeratorName.fromString(
                toAttributeValue(attributes, AttributeName.NUMERATOR));
        Lesson lesson = new Lesson(name, description, number, numeratorName, teacherNames);
        LOGGER.debug("convertLesson return = " + lesson);
        return lesson;
    }

    public static LessonTime convertDayTime(Node item) {
        NamedNodeMap attributes = item.getAttributes();
        final String start1, start2, end1, end2;
        final int number;

        start1 = toAttributeValue(attributes, AttributeName.START1);
        start2 = toAttributeValue(attributes, AttributeName.START2);
        end1 = toAttributeValue(attributes, AttributeName.END1);
        end2 = toAttributeValue(attributes, AttributeName.END2);
        number = Integer.parseInt(toAttributeValue(attributes, AttributeName.NUMBER, defaultValueForNumber));

        return new LessonTime(number, start1, start2, end1, end2);
    }

    /**
     * Получить значение атрибута.
     *
     * @param namedNodeMap  Атрибуты.
     * @param attributeName Имя атрибута.
     * @return значение атрибута.
     */
    public static String toAttributeValue(NamedNodeMap namedNodeMap, AttributeName attributeName) {
        Node namedItem = namedNodeMap.getNamedItem(attributeName.getName());
        if (namedItem == null) {
            LOGGER.warn("Filed toAttributeValue " + attributeName);
            return null;
        }
        return namedItem.getTextContent();
    }

    /**
     * Получить значение атрибута.
     *
     * @param namedNodeMap  Атрибуты.
     * @param attributeName Имя атрибута.
     * @param defaultValue  Значение по умолчанию. Будет возвращено в заместо null.
     * @return значение атрибута.
     */
    public static String toAttributeValue(NamedNodeMap namedNodeMap, AttributeName attributeName, String defaultValue) {
        LOGGER.debug("toAttributeValue() namedNodeMap = " + namedNodeMap + " attributeName = " + attributeName + " defaultValue = " + defaultValue);
        String resulted = toAttributeValue(namedNodeMap, attributeName);
        if (resulted == null) return defaultValue;
        LOGGER.debug("toAttributeValue() return " + resulted);
        return resulted;
    }
}
