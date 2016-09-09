package com.parsingHTML.logic.parser.daytime;

import com.parsingHTML.logic.element.ElementHelper;
import com.parsingHTML.logic.element.ElementName;
import com.parsingHTML.logic.parsing.html.ParserXMLCheck;
import org.jsoup.nodes.Element;
import org.junit.Test;

/**
 * Тестирование ParserDayTime.
 */
public class ParserDayTimeTest {

    @Test
    public void parsing() throws Exception {
        Element elementResults = ParserXMLCheck.parsingElement(new ParserDayTime(), "LessonTime.html");
        ElementHelper.checkTagName(elementResults, ElementName.WEEK_TIME);
        ElementHelper.checkElementsSize(elementResults.children(), ElementName.LESSON_TIME, 14);
        ElementHelper.checkElementsSize(elementResults.children(), ElementName.DAY_TIME, 6);
    }
}