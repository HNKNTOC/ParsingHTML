package com.parsingHTML.logic.parsing.html.daytime;

import com.parsingHTML.logic.parsing.ElementHelper;
import com.parsingHTML.logic.parsing.ElementName;
import com.parsingHTML.logic.parsing.html.ParserXMLCheck;
import org.jsoup.nodes.Element;
import org.junit.Test;

/**
 * Тестирует реализацию ParserWeekTime.
 */
public class ParserWeekTimeTest extends ParserXMLCheck {
    @Test
    public void parsing() throws Exception {
        Element elementResults = ParserXMLCheck.parsingElement(new ParserWeekTime(), "WeekTime.com.parsingHTML.logic.html");
        ElementHelper.checkTagName(elementResults, ElementName.WEEK_TIME);
        ElementHelper.checkElementsSize(elementResults.children(), "dayTime", 6);
    }
}