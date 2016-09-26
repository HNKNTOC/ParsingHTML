package com.parsingHTML.logic.parser.daytime;

import com.parsingHTML.logic.element.ElementHelper;
import com.parsingHTML.logic.element.ElementName;
import com.parsingHTML.logic.parsing.html.ParserXMLCheck;
import org.jsoup.nodes.Element;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Тестирование ParserLessonTime.
 */
public class ParserLessonTimeTest extends ParserXMLCheck {
    @Test
    public void parsing() throws Exception {
        //TODO Передулать
        Element elementResults = ParserXMLCheck.parsingElement(new ParserLessonTime(null), "LessonTime.html");
        assertTrue(ElementHelper.checkTagName(elementResults, ElementName.LESSON_TIME.getName()));
        assertTrue(ElementHelper.checkElementAttribute(elementResults, "number", "3"));
        assertTrue(ElementHelper.checkElementAttribute(elementResults, "start1", "08:30"));
        assertTrue(ElementHelper.checkElementAttribute(elementResults, "end1", "09:15"));
        assertTrue(ElementHelper.checkElementAttribute(elementResults, "start2", "09:20"));
        assertTrue(ElementHelper.checkElementAttribute(elementResults, "end2", "10:05"));
    }
}