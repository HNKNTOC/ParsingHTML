package com.parsingHTML.logic.parser.grouplesson;

import com.parsingHTML.logic.parser.ParserHelper;
import com.parsingHTML.logic.parsing.html.ParserXMLCheck;
import org.jsoup.nodes.Element;
import org.junit.Test;


public class ParserLessonTest {
    @Test
    public void parsing() throws Exception {
        Element elementResults = ParserXMLCheck.parsingElement(new ParserLesson(null), "Lesson.html");
        ParserHelper.checkElementAttribute(elementResults, "number", "3");
        ParserHelper.checkElementAttribute(elementResults, "lessonName", "Естествознание");
        ParserHelper.checkElementAttribute(elementResults, "teacher", "Name Teacher");
        ParserHelper.checkElementAttribute(elementResults, "numerator", "Числ.");
        ParserHelper.checkElementAttribute(elementResults, "audience", "Практическое занятие 405н");
    }

}