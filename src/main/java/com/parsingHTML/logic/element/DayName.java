package com.parsingHTML.logic.element;

/**
 * Дни недели.
 */
public enum DayName {
    MONDAY("Понедельник", "Пн"),
    TUESDAY("Вторник", "Вт"),
    WEDNESDAY("Среда", "Ср"),
    THURSDAY("Четверг", "Чт"),
    FRIDAY("Пятница", "Пт"),
    SATURDAY("Суббота", "Сб");

    private String nameRu;
    private String nameShort;

    DayName(String nameRu, String nameShort) {
        this.nameRu = nameRu;
        this.nameShort = nameShort;
    }

    public String getNameRu() {
        return nameRu;
    }

    public String getNameShort() {
        return nameShort;
    }

    @Override
    public String toString() {
        return "DayName{" +
                "nameRu='" + nameRu + '\'' +
                ", nameShort='" + nameShort + '\'' +
                '}';
    }
}

