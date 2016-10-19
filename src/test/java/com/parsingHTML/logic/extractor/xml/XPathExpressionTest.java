package com.parsingHTML.logic.extractor.xml;

import com.parsingHTML.logic.element.DayName;
import com.parsingHTML.logic.element.NumeratorName;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Тест для XPathExpression.
 */
public class XPathExpressionTest {
    @Test
    public void selectLessonDENOMINATOR() throws Exception {
        String s = XPathExpression.selectLesson(DayName.WEDNESDAY, NumeratorName.DENOMINATOR);
        assertEquals(s, "schedule/university/group_lesson/day_lesson[@day-number='4']/lesson[@numerator='Знам.' or @numerator='empty']");
    }

    @Test
    public void selectLessonEMPTY() throws Exception {
        String s = XPathExpression.selectLesson(DayName.WEDNESDAY, NumeratorName.EMPTY);
        assertEquals(s, "schedule/university/group_lesson/day_lesson[@day-number='4']/lesson[@numerator='empty' or @numerator='empty']");
    }

    @Test
    public void selectLessonNUMERATOR() throws Exception {
        String s = XPathExpression.selectLesson(DayName.MONDAY, NumeratorName.NUMERATOR);
        assertEquals(s, "schedule/university/group_lesson/day_lesson[@day-number='2']/lesson[@numerator='Числ.' or @numerator='empty']");
    }

    @Test
    public void selectLessonTime() throws Exception {
        String s = XPathExpression.selectLessonTime(DayName.FRIDAY, 3);
        assertEquals(s, "schedule/university/week_time/day_time[@day-time-number='2']/lesson_time[@number='3']");
        String s2 = XPathExpression.selectLessonTime(DayName.SATURDAY, 1);
        assertEquals(s2, "schedule/university/week_time/day_time[@day-time-number='7']/lesson_time[@number='1']");
    }

}