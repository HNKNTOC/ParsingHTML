package com.parsingHTML.logic.extractor.xml;

import com.parsingHTML.logic.element.DayName;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Тест для XPathExpression.
 */
public class XPathExpressionTest {
    @Test
    public void selectLesson() throws Exception {
        String s = XPathExpression.selectLesson(DayName.MONDAY);
        assertEquals(s, "schedule/university/group_lesson/day_lesson[@day-number='2']/lesson");
        String s2 = XPathExpression.selectLesson(DayName.WEDNESDAY);
        assertEquals(s2, "schedule/university/group_lesson/day_lesson[@day-number='4']/lesson");
    }

    @Test
    public void selectLessonTime() throws Exception {
        String s = XPathExpression.selectLessonTime(DayName.FRIDAY, 3);
        assertEquals(s, "schedule/university/week_time/day_time[@day-time-number='2']/lesson_time[@number='3']");
        String s2 = XPathExpression.selectLessonTime(DayName.SATURDAY, 1);
        assertEquals(s2, "schedule/university/week_time/day_time[@day-time-number='7']/lesson_time[@number='1']");
    }

}