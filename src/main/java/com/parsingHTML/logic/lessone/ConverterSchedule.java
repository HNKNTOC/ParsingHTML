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

    public static Lesson convert(Node node) {
        LOGGER.debug("convert = " + node);
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

}
