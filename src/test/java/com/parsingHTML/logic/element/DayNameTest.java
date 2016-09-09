package com.parsingHTML.logic.element;

import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.assertEquals;

/**
 * Тест для DayName.
 */
public class DayNameTest {

    @Test
    public void getDayNumber() throws Exception {
        assertEquals(DayName.MONDAY.getDayNumber(), Calendar.MONDAY);
        assertEquals(DayName.TUESDAY.getDayNumber(), Calendar.TUESDAY);
        assertEquals(DayName.WEDNESDAY.getDayNumber(), Calendar.WEDNESDAY);
        assertEquals(DayName.THURSDAY.getDayNumber(), Calendar.THURSDAY);
        assertEquals(DayName.FRIDAY.getDayNumber(), Calendar.FRIDAY);
        assertEquals(DayName.SATURDAY.getDayNumber(), Calendar.SATURDAY);
        assertEquals(DayName.SUNDAY.getDayNumber(), Calendar.SUNDAY);
    }

}