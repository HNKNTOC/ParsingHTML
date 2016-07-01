package com.parsingHTML.logic.parsing.check;

import com.parsingHTML.logic.parsing.html.daytime.ParserDayTime;
import com.parsingHTML.logic.xml.factory.ElementJsoupFactory;
import org.jsoup.nodes.Element;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Nikita on 01.07.2016.
 */
public class ParserElementTestTest {

    private Element main;

    @Before
    public void setUp() throws Exception {
        main = ElementJsoupFactory.createElement("main");
        main.attr("name", "test");
        main.appendChild(ElementJsoupFactory.createElement("children"));
        main.appendChild(ElementJsoupFactory.createElement("children"));
    }

    @Test
    public void parsingElement() throws Exception {
        Element element = ParserXMLCheck.parsingElement(new ParserDayTime(), ".DayTimexml");
        assertNotNull(element);
    }

    @Test
    public void checkName() throws Exception {
        assertTrue(ParserXMLCheck.checkName(main, "main"));
        assertFalse(ParserXMLCheck.checkName(main, "test"));
    }

    @Test
    public void checkElementSize() throws Exception {
        assertTrue(ParserXMLCheck.checkElementSize(main, "children", 2));
        assertFalse(ParserXMLCheck.checkElementSize(main, "children", 3));
        assertFalse(ParserXMLCheck.checkElementSize(main, "children", 1));
    }

    @Test
    public void checkElementAttribute() throws Exception {
        assertTrue(ParserXMLCheck.checkElementAttribute(main, "name", "test"));
        assertFalse(ParserXMLCheck.checkElementAttribute(main, "name", "falseValue"));
        assertFalse(ParserXMLCheck.checkElementAttribute(main, "name2", "test"));
    }

}