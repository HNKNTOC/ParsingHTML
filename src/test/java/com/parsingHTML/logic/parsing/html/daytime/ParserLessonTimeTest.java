package com.parsingHTML.logic.parsing.html.daytime;

import com.parsingHTML.logic.parsing.html.ParserXMLCheck;
import com.parsingHTML.logic.xml.ElementName;
import org.jsoup.nodes.Element;
import org.junit.Test;

/**
 * Тестирование ParserLessonTime.
 */
public class ParserLessonTimeTest extends ParserXMLCheck {
    @Test
    public void parsing() throws Exception {
        Element elementResults = ParserXMLCheck.parsingElement(new ParserLessonTime(), "LessonTime.html");
        ParserXMLCheck.checkName(elementResults, ElementName.LESSON_TIME);
        ParserXMLCheck.checkElementAttribute(elementResults, "number", "3");
        ParserXMLCheck.checkElementAttribute(elementResults, "start1", "08:30");
        ParserXMLCheck.checkElementAttribute(elementResults, "end1", "09:15");
        ParserXMLCheck.checkElementAttribute(elementResults, "start2", "09:20");
        ParserXMLCheck.checkElementAttribute(elementResults, "end2", "10:05");
    }
}