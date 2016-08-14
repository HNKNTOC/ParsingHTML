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

    public static Element createElementEmpty() {
        LOGGER.warn("createElementEmpty!!");
        Element element = new Element(Tag.valueOf("ElementEmpty"), "");
        element.text("This element was created instead of null!");
        return element;
    }

    /**
     * Создаёт элемент и помещает его в thisElement.
     *
     * @param tagName имя элемента.
     */
    public ElementJsoupBuilder createElement(String tagName) {
        LOGGER.debug("createElement name = " + tagName);
        thisElement = new Element(Tag.valueOf(tagName), "");
        return this;
    }

    public ElementJsoupBuilder setText(String text) {
        thisElement.text(text);
        return this;
    }

    public ElementJsoupBuilder setAttr(String attributeKey, String attributeValue) {
        thisElement.attr(attributeKey, attributeValue);
        return this;
    }
}
