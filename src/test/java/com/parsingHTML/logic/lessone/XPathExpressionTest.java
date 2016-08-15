package com.parsingHTML.logic.lessone;

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
        assertEquals(s, "schedule/university/group_lesson/day_lesson[@name='Понедельник']/lesson");
        String s2 = XPathExpression.selectLesson(DayName.WEDNESDAY);
        assertEquals(s2, "schedule/university/group_lesson/day_lesson[@name='Среда']/lesson");
    }

    @Test
    public void selectLessonTime() throws Exception {
        String s = XPathExpression.selectLessonTime(DayName.FRIDAY, 3);
        assertEquals(s, "schedule/university/week_time/day_time[@name='Понедельник']/lesson_time[@number='3']");
        String s2 = XPathExpression.selectLessonTime(DayName.SATURDAY, 1);
        assertEquals(s2, "schedule/university/week_time/day_time[@name='Суббота']/lesson_time[@number='1']");
    }

}