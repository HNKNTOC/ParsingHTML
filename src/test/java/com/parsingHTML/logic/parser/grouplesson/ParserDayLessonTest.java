package com.parsingHTML.logic.parser.grouplesson;

import com.parsingHTML.logic.element.AttributeName;
import com.parsingHTML.logic.element.ElementName;
import com.parsingHTML.logic.parser.ParserHelper;
import com.parsingHTML.logic.parsing.html.ParserXMLCheck;
import org.jsoup.nodes.Element;
import org.junit.Test;

/**
 * Тест для ParserDayLesson.
 */
public class ParserDayLessonTest {
    @Test
    public void parsing() throws Exception {
        Element elementResults = ParserXMLCheck.parsingElement(new ParserDayLesson(null), "DayLesson.html");
        ParserHelper.checkTagName(elementResults, ElementName.DAY_LESSON.getName());
        ParserHelper.checkElementAttribute(elementResults, AttributeName.DAY_NUMBER.getName(), "2");
    }

}