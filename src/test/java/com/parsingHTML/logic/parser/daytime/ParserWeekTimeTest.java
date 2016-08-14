package com.parsingHTML.logic.parser.daytime;

import com.parsingHTML.logic.element.ElementHelper;
import com.parsingHTML.logic.element.ElementName;
import com.parsingHTML.logic.parsing.html.ParserXMLCheck;
import org.jsoup.nodes.Element;
import org.junit.Test;

/**
 * Тестирует реализацию ParserWeekTime.
 */
public class ParserWeekTimeTest extends ParserXMLCheck {
    @Test
    public void parsing() throws Exception {
        Element elementResults = ParserXMLCheck.parsingElement(new ParserWeekTime(), "WeekTime.html");
        ElementHelper.checkTagName(elementResults, ElementName.WEEK_TIME);
        ElementHelper.checkElementsSize(elementResults.children(), "dayTime", 6);
    }
}