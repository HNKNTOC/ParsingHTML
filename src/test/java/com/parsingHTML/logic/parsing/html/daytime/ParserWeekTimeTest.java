package com.parsingHTML.logic.parsing.html.daytime;

import com.parsingHTML.logic.parsing.check.ParserXMLCheck;
import com.parsingHTML.logic.xml.ElementName;
import org.jsoup.nodes.Element;
import org.junit.Test;

/**
 * Тестирует реализацию ParserWeekTime.
 */
public class ParserWeekTimeTest extends ParserXMLCheck {
    @Test
    public void parsing() throws Exception {
        Element elementResults = ParserXMLCheck.parsingElement(new ParserWeekTime(), "WeekTime.html");
        ParserXMLCheck.checkName(elementResults, ElementName.WEEK_TIME);
        ParserXMLCheck.checkElementSize(elementResults, "dayTime", 6);
    }
}