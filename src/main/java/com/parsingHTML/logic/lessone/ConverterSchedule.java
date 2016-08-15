package com.parsingHTML.logic.lessone;

import com.parsingHTML.logic.element.AttributeName;
import com.parsingHTML.logic.element.NumeratorName;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

/**
 * Конвертирует из {@link Node} элементы расписания.
 */
public class ConverterSchedule {
    private static final Logger LOGGER = LogManager.getLogger(ConverterSchedule.class);

    public static Lesson convertLesson(Node node) {
        LOGGER.debug("convertLesson = " + node);
        NamedNodeMap attributes = node.getAttributes();

        final String name, teacher, description;

        name = toAttributeValue(attributes, AttributeName.NAME);
        teacher = toAttributeValue(attributes, AttributeName.TEACHER);
        description = toAttributeValue(attributes, AttributeName.DESCRIPTION);
        final int number = Integer.parseInt(toAttributeValue(attributes, AttributeName.NUMBER));
        final NumeratorName numeratorName = NumeratorName.fromString(toAttributeValue(attributes, AttributeName.NUMERATOR));

        return new Lesson(name, description, number, numeratorName, teacher);
    }

    public static String toAttributeValue(NamedNodeMap namedNodeMap, AttributeName attributeName) {
        Node namedItem = namedNodeMap.getNamedItem(attributeName.getName());
        if (namedItem == null) {
            LOGGER.warn("Filed toAttributeValue " + attributeName);
            return null;
        }
        return namedItem.getTextContent();
    }

    public static DayTime convertDayTime(Node item) {
        NamedNodeMap attributes = item.getAttributes();
        final String start1, start2, end1, end2;
        final int number;

        start1 = toAttributeValue(attributes, AttributeName.START1);
        start2 = toAttributeValue(attributes, AttributeName.START2);
        end1 = toAttributeValue(attributes, AttributeName.END1);
        end2 = toAttributeValue(attributes, AttributeName.END2);
        number = Integer.parseInt(toAttributeValue(attributes, AttributeName.NUMBER));

        return new DayTime(number, start1, start2, end1, end2);
    }
}
