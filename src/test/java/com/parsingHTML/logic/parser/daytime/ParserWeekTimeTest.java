package com.parsingHTML.logic.parser.daytime;

import com.parsingHTML.logic.element.AttributeName;
import com.parsingHTML.logic.element.ElementHelper;
import com.parsingHTML.logic.element.ElementName;
import com.parsingHTML.logic.element.NumeratorName;
import com.parsingHTML.logic.parsing.html.ParserXMLCheck;
import org.jsoup.nodes.Element;
import org.junit.Test;

import static org.junit.Assert.assertTrue;


public class ParserWeekTimeTest extends ParserXMLCheck {
    @Test
    public void resultTest() throws Exception {
        Element elementResults = ParserXMLCheck.parsingElement(
                new ParserWeekTime(null), "WeekTime.html");

        assertTrue(ElementHelper.checkTagName(elementResults, ElementName.WEEK_TIME));

        assertTrue(ElementHelper.checkElementAttribute(elementResults,
                AttributeName.NUMERATOR.getName(), NumeratorName.NUMERATOR.getName()));
    }
}