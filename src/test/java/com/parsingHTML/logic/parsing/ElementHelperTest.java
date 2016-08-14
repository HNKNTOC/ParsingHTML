package com.parsingHTML.logic.parsing;

import com.parsingHTML.logic.element.ElementHelper;
import com.parsingHTML.logic.element.ElementJsoupFactory;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Тестирует ElementHelper.
 */
public class ElementHelperTest {


    Element mainElement;
    private String elementName = "MainElement";
    private String childrenName = "children";

    @Before
    public void setUp() throws Exception {
        mainElement = ElementJsoupFactory.createElement(elementName);
    }

    /**
     * Вспомагательный метод добовляет Element в mainElement.
     *
     * @param tagName имя Element.
     * @param number  количество.
     */
    private void addElement(String tagName, int number) {
        for (int j = 0; j < number; j++) {
            mainElement.appendChild(ElementJsoupFactory.createElement(tagName));
        }
    }

    @Test
    public void checkTagName() throws Exception {
        ElementHelper.checkTagName(mainElement, elementName);
    }

    @Test
    public void checkElementSize() throws Exception {
        assertTrue(ElementHelper.checkElementsSize(mainElement.children(), childrenName, 0));
        addElement(childrenName, 4);
        assertTrue(ElementHelper.checkElementsSize(mainElement.children(), childrenName, 4));
    }

    @Test
    public void checkElementSize1() throws Exception {
        assertTrue(ElementHelper.checkElementsSize(mainElement.children(), 0));
        addElement(childrenName, 10);
        assertTrue(ElementHelper.checkElementsSize(mainElement.children(), 10));
    }

    @Test
    public void checkNotElementSize() throws Exception {
        assertFalse(ElementHelper.checkNotElementSize(mainElement.children(), 0));
        addElement(childrenName, 10);
        assertFalse(ElementHelper.checkNotElementSize(mainElement.children(), 10));
    }

    @Test
    public void selectElement() throws Exception {
        String cssQuery = childrenName + "[id=12G]";
        addElement(childrenName, 10);
        Element children = ElementJsoupFactory.createElement(childrenName);
        children.attr("id", "12G");
        mainElement.appendChild(children);
        Element result = ElementHelper.selectElement(mainElement, cssQuery, 0);
        assertTrue(ElementHelper.checkElementAttribute(result, "id", "12G"));
    }

    @Test
    public void selectElements() throws Exception {
        String cssQuery = childrenName;
        addElement(childrenName, 13);
        addElement("myElement", 7);
        Elements result = ElementHelper.selectElements(mainElement, cssQuery);
        assertTrue(result.size() == 13);
    }

    @Test
    public void checkElementAttribute() throws Exception {
        mainElement.attr("id", "12");
        mainElement.attr("Name", "Vasa");
        assertTrue(ElementHelper.checkElementAttribute(mainElement, "id", "12"));
        assertTrue(ElementHelper.checkElementAttribute(mainElement, "Name", "Vasa"));
        assertFalse(ElementHelper.checkElementAttribute(mainElement, "Size", "10"));
    }

}