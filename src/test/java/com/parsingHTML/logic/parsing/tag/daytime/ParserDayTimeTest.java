package com.parsingHTML.logic.parsing.tag.daytime;

import com.parsingHTML.logic.parsing.tag.ParserElementTest;
import com.parsingHTML.logic.parsing.tag.ParserHTMLAbstract;
import com.parsingHTML.logic.xml.factory.ElementName;
import org.jsoup.select.Elements;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.*;

/**
 * Тестирование ParserDayTime.
 */
public class ParserDayTimeTest extends ParserElementTest {

    public ParserDayTimeTest() {
        super(new ParserDayTime(), "DayTime.html");
    }

    @Test
    @Override
    public void checkElementResults() {
        checkName(ElementName.WEEK_TIME);
        checkElementSize("lessonTime",14);
        checkElementSize("dayTime",6);
    }

}