package com.parsingHTML.logic.parsing.html;

import com.parsingHTML.logic.element.ElementHelper;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Тестит ParserXMLCheck.
 */
public class ParserXMLCheckTest {

    private Element element = ParserXMLCheck.parsingElement("LessonTime.html");

    @Test
    public void elementNotNull() throws Exception {
        assertNotNull(element);
    }

    @Test
    public void checkElementName() throws Exception {
        Elements elements = ElementHelper.selectElements(element, "tr");
        assertTrue(ElementHelper.checkElementsSize(elements, 1));
        assertTrue(ElementHelper.checkElementsSize(elements, "td", 3));
    }
}