package com.parsingHTML.logic.parsing.html.grouplesson;

import com.parsingHTML.logic.parsing.check.ParserXMLCheck;
import org.jsoup.nodes.Element;
import org.junit.Test;

/**
 * Created by Nikita on 27.06.2016.
 */
public class ParserLessonTest {
    @Test
    public void parsing() throws Exception {
        Element elementResults = ParserXMLCheck.parsingElement(new ParserLesson(), "Lesson.html");
        ParserXMLCheck.checkElementAttribute(elementResults, "number", "3");
        ParserXMLCheck.checkElementAttribute(elementResults, "lessonName", "Естествознание");
        ParserXMLCheck.checkElementAttribute(elementResults, "audience", "Практическое занятие 405н");
        ParserXMLCheck.checkElementAttribute(elementResults, "teacher", "Name Teacher");
        ParserXMLCheck.checkElementAttribute(elementResults, "numerator", "Числ.");
    }

}