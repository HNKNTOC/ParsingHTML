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

        checkName(ElementName.WEEK_TIME);

        Elements dayTimes = elementResults.select("dayTime");
        int sizeDayTimes = 6;
        assertTrue("ElementResults does not contain " + sizeDayTimes + " dayTime. dayTime = " + dayTimes.size(), dayTimes.size() == sizeDayTimes);

        /*Elements lessonTimes = elementResults.select("lessonTime");
        int sizeLessonTimes = 14;
        assertTrue("In elementResults should be " + sizeLessonTimes + " lessonTime. lessonTime = " + lessonTimes.size(),
                lessonTimes.size() == sizeLessonTimes);*/
    }
}