package com.parsingHTML.logic;

import com.parsingHTML.logic.parser.ParserHTMLAbstract;
import com.parsingHTML.logic.parsing.html.ParserXMLCheck;
import org.jsoup.nodes.Element;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Тест для ParsingHTML.
 */
public class ParsingHTMLTest {
    @Test
    public void checkSchedulesTrue() throws Exception {
        Element elementTrue = ParserXMLCheck.parsingElement(new ForTestParserHTMLAbstract(), "GroupLesson.html");
        assertTrue(ParsingHTML.checkSchedules(elementTrue));
    }

    @Test
    public void checkSchedulesFalse() throws Exception {
        Element elementFalse = ParserXMLCheck.parsingElement(new ForTestParserHTMLAbstract(), "WeekTime.html");
        assertFalse(ParsingHTML.checkSchedules(elementFalse));
    }

    @Test
    public void checkSchedulesTimeTrue() throws Exception {
        Element elementTrue = ParserXMLCheck.parsingElement(new ForTestParserHTMLAbstract(), "WeekTime.html");
        assertTrue(ParsingHTML.checkSchedulesTime(elementTrue));
    }

    @Test
    public void checkSchedulesTimeFalse() throws Exception {
        Element elementFalse = ParserXMLCheck.parsingElement(new ForTestParserHTMLAbstract(), "DayLesson.html");
        assertFalse(ParsingHTML.checkSchedulesTime(elementFalse));
    }

    private static class ForTestParserHTMLAbstract extends ParserHTMLAbstract {
        @Override
        public Element parsing(Element element) {
            return element;
        }
    }
}