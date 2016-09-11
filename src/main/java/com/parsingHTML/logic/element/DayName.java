package com.parsingHTML.logic.element;

import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Locale;

/**
 * Дни недели.
 */
public enum DayName {
    MONDAY(Calendar.MONDAY),
    TUESDAY(Calendar.TUESDAY),
    WEDNESDAY(Calendar.WEDNESDAY),
    THURSDAY(Calendar.THURSDAY),
    FRIDAY(Calendar.FRIDAY),
    SATURDAY(Calendar.SATURDAY),
    SUNDAY(Calendar.SUNDAY);

    /**
     * Номер дня взятый из Calendar.
     */
    private final int dayNumber;

    private static DateFormatSymbols symbols = new DateFormatSymbols();
    private static String[] shortWeekdays = symbols.getShortWeekdays();
    private static String[] weekdays = symbols.getWeekdays();

    DayName(int dayNumber) {
        this.dayNumber = dayNumber;
    }

    public static void setLocale(Locale locale) {
        symbols = new DateFormatSymbols(locale);
        updateDayName();
    }

    /**
     * Обновление shortWeekdays и weekdays.
     */
    private static void updateDayName() {
        shortWeekdays = symbols.getShortWeekdays();
        weekdays = symbols.getWeekdays();
    }

    public int getDayNumber() {
        return dayNumber;
    }

    public String getNameShort() {
        return shortWeekdays[dayNumber];
    }

    public String getName() {
        return weekdays[dayNumber];
    }

    @Override
    public String toString() {
        return dayNumber + "";
    }
}

