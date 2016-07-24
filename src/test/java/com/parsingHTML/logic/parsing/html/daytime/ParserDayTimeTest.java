package com.parsingHTML.logic.parsing.html.daytime;

import com.parsingHTML.logic.parsing.ElementHelper;
import com.parsingHTML.logic.parsing.ElementName;
import com.parsingHTML.logic.parsing.html.ParserXMLCheck;
import org.jsoup.nodes.Element;
import org.junit.Test;

/**
 * Тестирование ParserDayTime.
 */
public class ParserDayTimeTest {

    @Test
    public void parsing() throws Exception {
        Element elementResults = ParserXMLCheck.parsingElement(new ParserDayTime(), "DayTime.html");
        ElementHelper.checkTagName(elementResults, ElementName.WEEK_TIME);
        ElementHelper.checkElementsSize(elementResults.children(), "lessonTime", 14);
        ElementHelper.checkElementsSize(elementResults.children(), "dayTime", 6);
    }
}