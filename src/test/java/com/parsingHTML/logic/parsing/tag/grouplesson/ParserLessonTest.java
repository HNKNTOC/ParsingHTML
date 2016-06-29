package com.parsingHTML.logic.parsing.tag.grouplesson;

import com.parsingHTML.logic.parsing.tag.ParserElementTest;
import org.jsoup.nodes.Element;
import org.junit.Test;

/**
 * Created by Nikita on 27.06.2016.
 */
public class ParserLessonTest {
    @Test
    public void parsing() throws Exception {
        Element elementResults = ParserElementTest.parsingElement(new ParserLesson(),"Lesson.html");
        ParserElementTest.checkElementAttribute(elementResults, "number", "3");
        ParserElementTest.checkElementAttribute(elementResults, "lessonName", "Естествознание");
        ParserElementTest.checkElementAttribute(elementResults, "audience", "Практическое занятие 405н");
        ParserElementTest.checkElementAttribute(elementResults, "teacher", "Name Teacher");
        ParserElementTest.checkElementAttribute(elementResults, "numerator", "Числ.");
    }

}