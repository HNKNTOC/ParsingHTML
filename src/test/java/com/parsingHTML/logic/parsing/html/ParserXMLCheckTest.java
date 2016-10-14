package com.parsingHTML.logic.parsing.html;

import com.parsingHTML.logic.parser.ParserHelper;
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
    public void parsingElementTest() throws Exception {
        Elements elements = ParserHelper.selectElements(element, "td");
        assertTrue(ParserHelper.checkElementSize(elements, 1));
    }
}