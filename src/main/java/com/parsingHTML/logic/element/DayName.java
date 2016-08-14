package com.parsingHTML.logic.element;

/**
 * Дни недели.
 */
public enum DayName {
    MONDAY("Понедельник"), TUESDAY("Вторник"), WEDNESDAY("Среда"), THURSDAY("Четверг"), FRIDAY("Пятница"), SATURDAY("Суббота");

    private String nameRu;

    DayName(String nameRu) {
        this.nameRu = nameRu;
    }

    public String getNameRu() {
        return nameRu;
    }

}

