package com.parsingHTML.logic.parser.grouplesson;

import com.parsingHTML.logic.element.ElementHelper;
import com.parsingHTML.logic.parsing.html.ParserXMLCheck;
import org.jsoup.nodes.Element;
import org.junit.Test;

/**
 * Created by Nikita on 27.06.2016.
 */
public class ParserDayLessonTest {
    @Test
    public void parsing() throws Exception {
        Element elementResults = ParserXMLCheck.parsingElement(new ParserDayLesson(), "DayLesson.html");
        ElementHelper.checkElementsSize(elementResults.children(), "lesson", 5);
        ElementHelper.checkElementAttribute(elementResults, "dayName", "Понедельник");
    }

}