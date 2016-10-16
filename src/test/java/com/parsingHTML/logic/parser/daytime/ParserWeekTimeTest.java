package com.parsingHTML.logic.parser.daytime;

import com.parsingHTML.logic.element.ElementHelper;
import com.parsingHTML.logic.element.ElementName;
import com.parsingHTML.logic.parsing.html.ParserXMLCheck;
import org.jsoup.nodes.Element;
import org.junit.Test;


public class ParserWeekTimeTest extends ParserXMLCheck {
    @Test
    public void resultTest() throws Exception {
        Element elementResults = ParserXMLCheck.parsingElement(
                new ParserWeekTime(null), "WeekTime.html");

        ElementHelper.checkTagName(elementResults, ElementName.WEEK_TIME.getName());
    }
}