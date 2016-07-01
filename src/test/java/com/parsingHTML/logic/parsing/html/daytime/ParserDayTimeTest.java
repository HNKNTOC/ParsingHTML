package com.parsingHTML.logic.parsing.html.daytime;

import com.parsingHTML.logic.parsing.check.ParserXMLCheck;
import com.parsingHTML.logic.xml.ElementName;
import org.jsoup.nodes.Element;
import org.junit.Test;

/**
 * Тестирование ParserDayTime.
 */
public class ParserDayTimeTest {

    @Test
    public void parsing() throws Exception {
        Element elementResults = ParserXMLCheck.parsingElement(new ParserDayTime(), "DayTime.html");
        ParserXMLCheck.checkName(elementResults, ElementName.WEEK_TIME);
        ParserXMLCheck.checkElementSize(elementResults, "lessonTime", 14);
        ParserXMLCheck.checkElementSize(elementResults, "dayTime", 6);
    }
}