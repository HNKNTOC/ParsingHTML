package com.parsingHTML.logic.parsing.tag.daytime;

import com.parsingHTML.logic.parsing.tag.ParserElementTest;
import com.parsingHTML.logic.xml.ElementName;
import org.jsoup.nodes.Element;
import org.junit.Test;

/**
 * Тестирование ParserLessonTime.
 */
public class ParserLessonTimeTest extends ParserElementTest {
    @Test
    public void parsing() throws Exception {
        Element elementResults = ParserElementTest.parsingElement(new ParserLessonTime(),"LessonTime.html");
        ParserElementTest.checkName(elementResults,ElementName.LESSON_TIME);
        ParserElementTest.checkElementAttribute(elementResults, "number", "3");
        ParserElementTest.checkElementAttribute(elementResults, "start1", "08:30");
        ParserElementTest.checkElementAttribute(elementResults, "end1", "09:15");
        ParserElementTest.checkElementAttribute(elementResults, "start2", "09:20");
        ParserElementTest.checkElementAttribute(elementResults, "end2", "10:05");
    }
}