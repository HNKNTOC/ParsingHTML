package com.parsingHTML.logic.element;

import org.jsoup.nodes.Element;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Тест для ElementJsoupBuilder.
 */
public class ElementJsoupBuilderTest {

    private ElementJsoupBuilder builder = new ElementJsoupBuilder();
    private ElementName elementName = ElementName.DAY_TIME;

    @Test
    public void getThisAndCreateElement() throws Exception {
        builder.createElement(elementName);
        assertEquals(builder.getThisElement().tagName(), elementName.getName());
    }

    @Test
    public void setText() throws Exception {
        String txt = "Text test";
        Element element = builder.createElement(elementName)
                .setText(txt)
                .getThisElement();
        assertEquals(element.text(), txt);
    }

    @Test
    public void setAttr() throws Exception {
        AttributeName attrName = AttributeName.DESCRIPTION;
        String value = "Value test";
        Element element = builder.createElement(elementName)
                .setAttr(attrName, value)
                .getThisElement();
        assertEquals(element.attr(attrName.getName()), value);
    }

    @Test
    public void createElementEmpty() throws Exception {
        Element elementEmpty = ElementJsoupBuilder.createElementEmpty();
        assertEquals(elementEmpty.tagName(), ElementName.EMPTY.getName());
    }

}