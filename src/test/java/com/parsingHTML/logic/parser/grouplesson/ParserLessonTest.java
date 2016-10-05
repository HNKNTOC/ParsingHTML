package com.parsingHTML.logic.parser.grouplesson;

import com.parsingHTML.logic.parser.ParsirHelper;
import com.parsingHTML.logic.parsing.html.ParserXMLCheck;
import org.jsoup.nodes.Element;
import org.junit.Test;


public class ParserLessonTest {
    @Test
    public void parsing() throws Exception {
        Element elementResults = ParserXMLCheck.parsingElement(new ParserLesson(), "Lesson.html");
        ParsirHelper.checkElementAttribute(elementResults, "number", "3");
        ParsirHelper.checkElementAttribute(elementResults, "lessonName", "Естествознание");
        ParsirHelper.checkElementAttribute(elementResults, "teacher", "Name Teacher");
        ParsirHelper.checkElementAttribute(elementResults, "numerator", "Числ.");
        ParsirHelper.checkElementAttribute(elementResults, "audience", "Практическое занятие 405н");
    }

}