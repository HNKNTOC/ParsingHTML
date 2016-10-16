package com.parsingHTML.logic.parser.grouplesson;

import com.parsingHTML.logic.element.AttributeName;
import com.parsingHTML.logic.element.ElementHelper;
import com.parsingHTML.logic.parsing.html.ParserXMLCheck;
import org.jsoup.nodes.Element;
import org.junit.Test;


public class ParserLessonTest {
    @Test
    public void parsing() throws Exception {
        Element elementResults = ParserXMLCheck.parsingElement(new ParserLesson(null), "Lesson.html");
        ElementHelper.checkElementAttribute(elementResults,
                AttributeName.NUMBER.getName(), "3");
        ElementHelper.checkElementAttribute(elementResults,
                AttributeName.LESSON_NAME.getName(), "Естествознание");
        ElementHelper.checkElementAttribute(elementResults,
                AttributeName.TEACHER.getName(), "ст. преп. Абраменко Л.И.");
        ElementHelper.checkElementAttribute(elementResults,
                AttributeName.NUMERATOR.getName(), "Числ.");
        ElementHelper.checkElementAttribute(elementResults,
                AttributeName.DESCRIPTION.getName(), "Практическое занятие 405н");
    }

}