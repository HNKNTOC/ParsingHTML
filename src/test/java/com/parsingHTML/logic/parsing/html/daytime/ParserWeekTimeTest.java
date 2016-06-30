package com.parsingHTML.logic.parsing.html.daytime;

import com.parsingHTML.logic.parsing.html.ParserElementTest;
import com.parsingHTML.logic.xml.ElementName;
import org.jsoup.nodes.Element;
import org.junit.Test;

/**
 * Тестирует реализацию ParserWeekTime.
 */
public class ParserWeekTimeTest extends ParserElementTest {
    @Test
    public void parsing() throws Exception {
        Element elementResults = ParserElementTest.parsingElement(new ParserWeekTime(),"WeekTime.html");
        ParserElementTest.checkName(elementResults,ElementName.WEEK_TIME);
        ParserElementTest.checkElementSize(elementResults,"dayTime",6);
    }
}