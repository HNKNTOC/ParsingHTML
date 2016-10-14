package com.parsingHTML.logic.parser.grouplesson;

import com.parsingHTML.logic.element.AttributeName;
import com.parsingHTML.logic.parser.ParserHelper;
import com.parsingHTML.logic.parsing.html.ParserXMLCheck;
import org.jsoup.nodes.Element;
import org.junit.Test;


public class ParserLessonTest {
    @Test
    public void parsing() throws Exception {
        Element elementResults = ParserXMLCheck.parsingElement(new ParserLesson(null), "Lesson.html");
        ParserHelper.checkElementAttribute(elementResults,
                AttributeName.NUMBER.getName(), "3");
        ParserHelper.checkElementAttribute(elementResults,
                AttributeName.LESSON_NAME.getName(), "Естествознание");
        ParserHelper.checkElementAttribute(elementResults,
                AttributeName.TEACHER.getName(), "ст. преп. Абраменко Л.И.");
        ParserHelper.checkElementAttribute(elementResults,
                AttributeName.NUMERATOR.getName(), "Числ.");
        ParserHelper.checkElementAttribute(elementResults,
                AttributeName.DESCRIPTION.getName(), "Практическое занятие 405н");
    }

}