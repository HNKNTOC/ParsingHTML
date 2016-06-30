package com.parsingHTML.logic.parsing.tag.daytime;

import com.parsingHTML.logic.parsing.tag.ParserElementTest;
import com.parsingHTML.logic.xml.ElementName;
import org.jsoup.nodes.Element;
import org.junit.Test;

/**
 * Тестирование ParserDayTime.
 */
public class ParserDayTimeTest {

    @Test
    public void parsing() throws Exception {
        Element elementResults = ParserElementTest.parsingElement(new ParserDayTime(), "DayTime.html");
        ParserElementTest.checkName(elementResults, ElementName.WEEK_TIME);
        ParserElementTest.checkElementSize(elementResults, "lessonTime", 14);
        ParserElementTest.checkElementSize(elementResults, "dayTime", 6);
    }
}