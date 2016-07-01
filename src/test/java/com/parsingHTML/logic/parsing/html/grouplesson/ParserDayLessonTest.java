package com.parsingHTML.logic.parsing.html.grouplesson;

import com.parsingHTML.logic.parsing.check.ParserXMLCheck;
import org.jsoup.nodes.Element;
import org.junit.Test;

/**
 * Created by Nikita on 27.06.2016.
 */
public class ParserDayLessonTest {
    @Test
    public void parsing() throws Exception {
        Element elementResults = ParserXMLCheck.parsingElement(new ParserDayLesson(), "DayLesson.html");
        ParserXMLCheck.checkElementSize(elementResults, "lesson", 5);
        ParserXMLCheck.checkElementAttribute(elementResults, "dayName", "Понедельник");
    }

}