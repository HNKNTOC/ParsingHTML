package com.parsingHTML.logic.element;

import org.junit.Test;

import java.util.Calendar;
import java.util.Locale;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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

    @Test
    public void getNameShort() throws Exception {
        DayName.setLocale(Locale.ENGLISH);
        check(DayName.MONDAY.getNameShort(), "MON");
        check(DayName.TUESDAY.getNameShort(), "TUE");
        check(DayName.WEDNESDAY.getNameShort(), "WED");
        check(DayName.THURSDAY.getNameShort(), "THU");
        check(DayName.FRIDAY.getNameShort(), "FRI");
        check(DayName.SATURDAY.getNameShort(), "SAT");
        check(DayName.SUNDAY.getNameShort(), "SUN");
    }

    @Test
    public void getName() throws Exception {
        DayName.setLocale(Locale.ENGLISH);
        check(DayName.MONDAY.getName(), "MONDAY");
        check(DayName.TUESDAY.getName(), "TUESDAY");
        check(DayName.WEDNESDAY.getName(), "WEDNESDAY");
        check(DayName.THURSDAY.getName(), "THURSDAY");
        check(DayName.FRIDAY.getName(), "FRIDAY");
        check(DayName.SATURDAY.getName(), "SATURDAY");
        check(DayName.SUNDAY.getName(), "SUNDAY");
    }

    @Test
    public void getNameShortChangeLocale() throws Exception {
        DayName.setLocale(new Locale("ru"));
        check(DayName.MONDAY.getName(), "Понедельник");
        check(DayName.TUESDAY.getName(), "Вторник");
        check(DayName.WEDNESDAY.getName(), "Среда");
        check(DayName.THURSDAY.getName(), "Четверг");
        check(DayName.FRIDAY.getName(), "Пятница");
        check(DayName.SATURDAY.getName(), "Суббота");
        check(DayName.SUNDAY.getName(), "Воскресенье");
    }

    @Test
    public void getNameChangeLocale() throws Exception {
        DayName.setLocale(new Locale("ru"));
        check(DayName.MONDAY.getNameShort(), "Пн");
        check(DayName.TUESDAY.getNameShort(), "Вт");
        check(DayName.WEDNESDAY.getNameShort(), "Ср");
        check(DayName.THURSDAY.getNameShort(), "Чт");
        check(DayName.FRIDAY.getNameShort(), "Пт");
        check(DayName.SATURDAY.getNameShort(), "Сб");
        check(DayName.SUNDAY.getNameShort(), "Вс");
    }

    public void check(String s1, String s2) {
        assertTrue(s1, s1.equalsIgnoreCase(s2));
    }

}