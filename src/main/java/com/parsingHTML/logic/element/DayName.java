package com.parsingHTML.logic.element;

/**
 * Дни недели.
 */
public enum DayName {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY;

    String getNameRU(DayName dayName) {
        switch (dayName) {
            case MONDAY:
                return "Понедельник";
            case TUESDAY:
                return "Вторник";
            case WEDNESDAY:
                return "Среда";
            case THURSDAY:
                return "Четверг";
            case FRIDAY:
                return "Пятница";
            case SATURDAY:
                return "Суббота";
        }
        return null;
    }

}

