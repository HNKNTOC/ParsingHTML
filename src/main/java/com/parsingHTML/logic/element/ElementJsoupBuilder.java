package com.parsingHTML.logic.element;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;

/**
 * Строитель для {@link Element}
 */
public class ElementJsoupBuilder {
    private static final Logger LOGGER = LogManager.getLogger(ElementJsoupBuilder.class);
    private Element thisElement;

    public Element getThisElement() {
        LOGGER.debug("getThisElement " + thisElement);
        return thisElement;
    }

    /**
     * Создаёт элемент и помещает его в thisElement.
     *
     * @param elementName имя элемента.
     */
    public ElementJsoupBuilder createElement(ElementName elementName) {
        LOGGER.debug("createElement name = " + elementName);
        thisElement = createElementCustomer(elementName);
        return this;
    }


    public ElementJsoupBuilder setText(String text) {
        LOGGER.debug("setText = " + text);
        thisElement.text(text);
        return this;
    }

    public ElementJsoupBuilder setAttr(AttributeName attributeName, String value) {
        LOGGER.debug("setAttr key = " + attributeName + " value = " + value);
        thisElement.attr(attributeName.getName(), value);
        return this;
    }

    public static Element createElementCustomer(ElementName elementName) {
        LOGGER.debug("createElementCustomer() ElementName = " + elementName);
        return new Element(Tag.valueOf(elementName.getName()), "");
    }

    public static Element createWrapper() {
        LOGGER.info("createWrapper()");
        return createElementCustomer(ElementName.WRAPPER);
    }

    public static Element createElementEmpty() {
        LOGGER.warn("createElementEmpty!!");
        Element element = createElementCustomer(ElementName.EMPTY);
        element.text("This element was created instead of null!");
        return element;
    }

}
