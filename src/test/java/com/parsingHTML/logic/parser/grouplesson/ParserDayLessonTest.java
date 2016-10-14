package com.parsingHTML.logic.parser.grouplesson;

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
        ParserHelper.checkElementSize(elementResults.children(), ElementName.LESSON.getName(), 5);
        ParserHelper.checkElementAttribute(elementResults, "dayName", "2");
    }

}