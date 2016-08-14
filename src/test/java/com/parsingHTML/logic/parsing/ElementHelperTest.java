package com.parsingHTML.logic.parsing;

import com.parsingHTML.logic.element.ElementHelper;
import com.parsingHTML.logic.element.ElementJsoupBuilder;
import com.parsingHTML.logic.element.ElementName;
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


    private Element mainElement;
    /**
     * Случайное имя для mainElement.
     */
    private ElementName elementName = ElementName.LESSON;
    private ElementName childrenName = ElementName.LESSON_TIME;
    private ElementJsoupBuilder builder;

    @Before
    public void setUp() throws Exception {
        mainElement = new ElementJsoupBuilder().createElement(elementName).getThisElement();
    }

    /**
     * Вспомагательный метод добовляет Element в mainElement.
     *
     * @param elementName имя Element.
     * @param number  количество.
     */
    private void addElement(ElementName elementName, int number) {
        for (int j = 0; j < number; j++) {
            builder = new ElementJsoupBuilder();
            mainElement.appendChild(builder.createElement(elementName).getThisElement());
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
        Element children = builder.createElement(childrenName).getThisElement();
        children.attr("id", "12G");
        mainElement.appendChild(children);
        Element result = ElementHelper.selectElement(mainElement, cssQuery, 0);
        assertTrue(ElementHelper.checkElementAttribute(result, "id", "12G"));
    }

    @Test
    public void selectElements() throws Exception {
        String cssQuery = childrenName.getName();
        addElement(childrenName, 13);
        addElement(ElementName.WEEK_TIME, 7);
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