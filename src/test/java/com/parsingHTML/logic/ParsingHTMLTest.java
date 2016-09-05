package com.parsingHTML.logic;

import com.parsingHTML.logic.parser.ParserHTMLAbstract;
import com.parsingHTML.logic.parsing.html.ParserXMLCheck;
import org.jsoup.nodes.Element;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Тест для ParsingHTML.
 */
public class ParsingHTMLTest {
    @Test
    public void checkSchedulesTrue() throws Exception {
        Element elementTrue = ParserXMLCheck.parsingElement(new ForTestParserHTMLAbstract(), "GroupLesson.html");
        assertTrue(ParsingHTML.checkSchedules(elementTrue.html()));
    }

    @Test
    public void checkSchedulesFalse() throws Exception {
        Element elementFalse = ParserXMLCheck.parsingElement(new ForTestParserHTMLAbstract(), "DayLesson.html");
        assertTrue(ParsingHTML.checkSchedules(elementFalse.html()));
    }

    private static class ForTestParserHTMLAbstract extends ParserHTMLAbstract {
        @Override
        public Element parsing(Element element) {
            return element;
        }
    }
}