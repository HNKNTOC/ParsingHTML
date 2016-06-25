package com.parsingHTML.logic.parsing.tag.daytime;

import com.parsingHTML.logic.parsing.tag.ParserElementTest;
import com.parsingHTML.logic.xml.factory.ElementName;
import org.jsoup.select.Elements;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

/**
 * Тестирует реализацию ParserWeekTime.
 */
public class ParserWeekTimeTest extends ParserElementTest {

    public ParserWeekTimeTest() {
        super(new ParserWeekTime(), "WeekTime.html");
    }

    @Before
    public void setUp() throws Exception {
    }

    @Test
    @Override
    public void checkElementResults() {
        checkName(elementResults,ElementName.WEEK_TIME);
        checkElementSize(elementResults,"dayTime",6);
    }
}