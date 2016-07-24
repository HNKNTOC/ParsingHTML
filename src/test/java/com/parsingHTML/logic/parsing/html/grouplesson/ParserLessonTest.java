package com.parsingHTML.logic.parsing.html.grouplesson;

import com.parsingHTML.logic.parsing.ElementHelper;
import com.parsingHTML.logic.parsing.html.ParserXMLCheck;
import org.jsoup.nodes.Element;
import org.junit.Test;

/**
 * Created by Nikita on 27.06.2016.
 */
public class ParserLessonTest {
    @Test
    public void parsing() throws Exception {
        Element elementResults = ParserXMLCheck.parsingElement(new ParserLesson(), "Lesson.html");
        ElementHelper.checkElementAttribute(elementResults, "number", "3");
        ElementHelper.checkElementAttribute(elementResults, "lessonName", "Естествознание");
        ElementHelper.checkElementAttribute(elementResults, "teacher", "Name Teacher");
        ElementHelper.checkElementAttribute(elementResults, "numerator", "Числ.");
        ElementHelper.checkElementAttribute(elementResults, "audience", "Практическое занятие 405н");
    }

}