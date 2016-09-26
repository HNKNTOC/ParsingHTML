package com.parsingHTML.logic.parser.daytime;

import com.parsingHTML.logic.element.AttributeName;
import com.parsingHTML.logic.element.ElementHelper;
import com.parsingHTML.logic.element.ElementName;
import com.parsingHTML.logic.parsing.html.ParserXMLCheck;
import org.jsoup.nodes.Element;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Тестирование ParserDayTime.
 */
public class ParserDayTimeTest {

    @Test
    public void parsing() throws Exception {
        Element elementResults = ParserXMLCheck.parsingElement(new ParserDayTime(null), "DayTime.html");
        assertTrue(ElementHelper.checkElementsSize(elementResults.children(), ElementName.DAY_TIME.getName(), 6));
        int dayTimeNumber = 2;
        for (Element element : elementResults.children()) {
            assertTrue(ElementHelper.checkTagName(element, ElementName.DAY_TIME.getName()));
            assertTrue(ElementHelper.checkElementAttribute(
                    element, AttributeName.DAY_TIME_NUMBER.getName(), String.valueOf(dayTimeNumber)));
            if (dayTimeNumber == 3 || dayTimeNumber == 4 || dayTimeNumber == 5 || dayTimeNumber == 6) {
                assertTrue(ElementHelper.checkElementAttribute(
                        element, AttributeName.OVERRIDE.getName(), "2"));
            }
            dayTimeNumber++;
        }
    }
}