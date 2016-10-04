package com.parsingHTML.logic.parsing;

import com.parsingHTML.logic.element.ElementHelper;
import com.parsingHTML.logic.element.ElementJsoupBuilder;
import com.parsingHTML.logic.element.ElementName;
import com.parsingHTML.logic.parser.exception.ExceptionParser;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Тестирует ElementHelper.
 */
public class ElementHelperTest {

    private Element mainElement;
    private ElementName elementName = ElementName.LESSON;
    private ElementName childrenName = ElementName.LESSON_TIME;
    private final static ElementJsoupBuilder builder = new ElementJsoupBuilder();
    private final String cssQueryForTestSelectElement = childrenName + "[id=12G]";
    private final static int SIZE_ELEMENT = 5;

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
            mainElement.appendChild(builder.createElement(elementName).getThisElement());
        }
    }

    @Test
    public void checkTagName() throws Exception {
        ElementHelper.checkTagName(mainElement, elementName.getName());
    }

    @Test
    public void checkElementSizeWhitName() throws Exception {
        ElementHelper.checkElementsSize(mainElement.children(), childrenName.getName(), 0);
        addElement(childrenName, SIZE_ELEMENT);
        ElementHelper.checkElementsSize(mainElement.children(), childrenName.getName(), SIZE_ELEMENT);
    }

    @Test(expected = ExceptionParser.class)
    public void checkElementSizeWhitNameException() throws Exception {
        ElementHelper.checkElementsSize(mainElement.children(), childrenName.getName(), SIZE_ELEMENT);
    }

    @Test
    public void checkElementSizeWithoutName() throws Exception {
        ElementHelper.checkElementsSize(mainElement.children(), 0);
        addElement(childrenName, SIZE_ELEMENT);
        ElementHelper.checkElementsSize(mainElement.children(), SIZE_ELEMENT);
    }

    @Test(expected = ExceptionParser.class)
    public void checkElementSizeWithoutNameException() throws Exception {
        ElementHelper.checkElementsSize(mainElement.children(), SIZE_ELEMENT);
    }

    @Test
    public void checkElementSizeNotEqual() throws Exception {
        ElementHelper.checkElementSizeNotEqual(mainElement.children(), 1);
        addElement(childrenName, 10);
        ElementHelper.checkElementSizeNotEqual(mainElement.children(), 3);
    }

    @Test(expected = ExceptionParser.class)
    public void checkElementSizeNotEqualException() throws Exception {
        addElement(childrenName, SIZE_ELEMENT);
        ElementHelper.checkElementSizeNotEqual(mainElement.children(), SIZE_ELEMENT);
    }

    @Test
    public void selectElement() throws Exception {
        addElement(childrenName, 5);
        mainElement.child(0).attr("id", "12G");
        ElementHelper.selectElement(mainElement, cssQueryForTestSelectElement, 0);
    }

    @Test(expected = ExceptionParser.class)
    public void selectElementException() throws Exception {
        addElement(childrenName, 5);
        ElementHelper.selectElement(mainElement, cssQueryForTestSelectElement, 0);
    }

    private final String cssQueryForSelectElements = childrenName.getName();

    @Test
    public void selectElements() throws Exception {
        addElement(childrenName, SIZE_ELEMENT);
        addElement(ElementName.WEEK_TIME, 7);
        Elements result = ElementHelper.selectElements(mainElement, cssQueryForSelectElements);
        assertTrue(result.size() == SIZE_ELEMENT);
    }

    @Test(expected = ExceptionParser.class)
    public void selectElementsException() throws Exception {
        ElementHelper.selectElements(mainElement, cssQueryForSelectElements);
    }

    private final static String ATTR_KEY_ID = "id";
    private final static String ATTR_KEY_NAME = "Name";
    private final static String ATTR_VALUE_ID = "12";
    private final static String ATTR_VALUE_NAME = "Vasa";

    @Test
    public void checkElementAttribute() throws Exception {

        mainElement.attr(ATTR_KEY_ID, ATTR_VALUE_ID);
        mainElement.attr(ATTR_KEY_NAME, ATTR_VALUE_NAME);
        ElementHelper.checkElementAttribute(mainElement, ATTR_KEY_ID, ATTR_VALUE_ID);
        ElementHelper.checkElementAttribute(mainElement, ATTR_KEY_NAME, ATTR_VALUE_NAME);
    }

    @Test(expected = ExceptionParser.class)
    public void checkElementAttributeException() throws Exception {
        ElementHelper.checkElementAttribute(mainElement, ATTR_KEY_ID, ATTR_VALUE_ID);
        ElementHelper.checkElementAttribute(mainElement, ATTR_KEY_NAME, ATTR_VALUE_NAME);
    }

    @Test
    public void checkElementSize() throws Exception {
        addElement(elementName, SIZE_ELEMENT);
        ElementHelper.checkElementSize(mainElement, SIZE_ELEMENT + 1);
    }

    @Test(expected = ExceptionParser.class)
    public void checkElementSizeException() throws Exception {
        ElementHelper.checkElementSize(mainElement, 5);
    }
}