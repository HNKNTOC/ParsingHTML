package com.parsingHTML.logic.parser.daytime;

import com.parsingHTML.logic.element.ElementHelper;
import com.parsingHTML.logic.element.ElementName;
import com.parsingHTML.logic.parsing.html.ParserXMLCheck;
import org.jsoup.nodes.Element;
import org.junit.Test;

/**
 * Тестирование ParserLessonTime.
 */
public class ParserLessonTimeTest extends ParserXMLCheck {
    @Test
    public void parsing() throws Exception {
        //TODO Передулать
        Element elementResults = ParserXMLCheck.parsingElement(new ParserLessonTime(null), "LessonTime.html");
        ElementHelper.checkTagName(elementResults, ElementName.LESSON_TIME.getName());
        ElementHelper.checkElementAttribute(elementResults, "number", "1");
        ElementHelper.checkElementAttribute(elementResults, "start1", "08:30");
        ElementHelper.checkElementAttribute(elementResults, "end1", "09:15");
        ElementHelper.checkElementAttribute(elementResults, "start2", "09:20");
        ElementHelper.checkElementAttribute(elementResults, "end2", "10:05");
    }
}