package com.parsingHTML.logic.extractor.xml;


import com.parsingHTML.logic.element.AttributeName;
import com.parsingHTML.logic.element.ElementName;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Строитель для XPath выражений.
 */
public class XPathBuilder {
    private static final Logger LOGGER = LogManager.getLogger(XPathBuilder.class);
    /**
     * XPath выражение.
     */
    private StringBuilder thisExpression;

    public XPathBuilder() {
        thisExpression = new StringBuilder();
    }

    public XPathBuilder add(XPathElement element) {
        LOGGER.debug("add element = " + element);
        addNodeName(element.getElementName());
        List<Attribute> attributeNames = element.getAttributeNames();
        if (attributeNames.size() != 0) {
            LOGGER.debug("element attribute + " + attributeNames);
            addAttributes(attributeNames);
        }
        return this;
    }

    /**
     * Добавление NodeName в thisExpression.
     * Если thisExpression не пустой добавляет Slash.
     *
     * @param elementName NodeName который нужно добавить.
     */
    private void addNodeName(ElementName elementName) {
        if (thisExpression.length() != 0) addSlash();
        thisExpression.append(elementName);
    }

    private void addAttributes(List<Attribute> attributeNames) {
        LOGGER.debug("Add Attributes =  " + attributeNames);

        thisExpression.append("[");

        final int size = attributeNames.size();
        int i = 0;
        for (Attribute attr : attributeNames) {
            AttributeName attributeName = attr.getAttributeName();
            String value = attr.getValue();
            thisExpression.append(extractAttribute(attributeName, value));
            LOGGER.debug("Add attribute " + attributeNames + " = " + value);
            if (i >= size - 1) {
                LOGGER.debug("End add attributes.");
                thisExpression.append("]");
            } else {
                LOGGER.debug("Nex attribute.");
                thisExpression.append(" or ");
            }
            i++;
        }
    }

    /**
     * Получить из attributeName и value.
     *
     * @param attributeName AttributeName который нужно добавить.
     * @param value         Значение для attributeName.
     */
    private String extractAttribute(AttributeName attributeName, String value) {
        return String.format("@%s='%s'", attributeName, value);
    }

    private void addSlash() {
        thisExpression.append("/");
    }

    public String getExpression() {
        return thisExpression.toString();
    }

    public void clear() {
        thisExpression.setLength(0);
    }

    @Override
    public String toString() {
        return "XPathBuilder{" +
                "thisExpression=" + thisExpression +
                '}';
    }


    /**
     * Используется для построения XPath.
     * Имя элемента и список его атрибутов.
     * Атрибуты могут повторятся.
     */
    public static class XPathElement {
        private static final Logger LOGGER = LogManager.getLogger(XPathElement.class);
        /**
         * Имя элемента.
         */
        private final ElementName elementName;
        /**
         * Атрибуты элемента.
         */
        private List<Attribute> attributeNames = new ArrayList<>();

        public XPathElement(ElementName elementName) {
            this.elementName = elementName;
        }

        private void addAttrLesson(AttributeName attributeName, String value) {
            LOGGER.debug("addAttrLesson attributeName = " + attributeName + " value = " + value);
            if (value != null) {
                attributeNames.add(new Attribute(attributeName, value));
            }
        }

        public void addAttr(AttributeName attributeName, String value) {
            attributeNames.add(new Attribute(attributeName, value));
        }

        public void addAttr(AttributeName attributeName, int value) {
            addAttr(attributeName, String.valueOf(value));
        }

        public List<Attribute> getAttributeNames() {
            return attributeNames;
        }


        public ElementName getElementName() {
            return elementName;
        }

        @Override
        public String toString() {
            return "XPathElement{" +
                    "elementName=" + elementName +
                    ", attributeNames=" + attributeNames +
                    '}';
        }
    }

    /**
     * Используется для пары имя атрибута и его значения.
     * Не использую Map так как Ключ - Имя атрибута может повторятся.
     */
    private static class Attribute {
        private final AttributeName attributeName;
        private final String value;

        public Attribute(AttributeName attributeName, String value) {
            this.attributeName = attributeName;
            this.value = value;
        }

        public AttributeName getAttributeName() {
            return attributeName;
        }

        public String getValue() {
            return value;
        }
    }
}
