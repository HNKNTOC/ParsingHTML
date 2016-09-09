package com.parsingHTML.logic.element;

import java.util.Calendar;

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

    DayName(int dayNumber) {
        this.dayNumber = dayNumber;
    }

    public int getDayNumber() {
        return dayNumber;
    }

    @Override
    public String toString() {
        return dayNumber + "";
    }
}

