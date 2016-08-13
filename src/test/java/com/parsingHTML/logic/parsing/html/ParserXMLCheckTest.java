package com.parsingHTML.logic.parsing.html;

import com.parsingHTML.logic.parsing.html.daytime.ParserDayTime;
import org.jsoup.nodes.Element;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * Тестит ParserXMLCheck.
 */
public class ParserXMLCheckTest {

    @Test
    public void parsingElement() throws Exception {
        Element element = ParserXMLCheck.parsingElement(new ParserDayTime(), ".DayTime.xml");
        assertNotNull(element);
    }

}