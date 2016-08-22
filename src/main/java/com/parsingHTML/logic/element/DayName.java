package com.parsingHTML.logic.element;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * Дни недели.
 */
public enum DayName {
    MONDAY("Понедельник", "Пн"),
    TUESDAY("Вторник", "Вт"),
    WEDNESDAY("Среда", "Ср"),
    THURSDAY("Четверг", "Чт"),
    FRIDAY("Пятница", "Пт"),
    SATURDAY("Суббота", "Сб"),
    SUNDAY("Воскресенье", "Вс");

    private static final Logger LOGGER = LogManager.getLogger(DayName.class);
    /**
     * Нужен для получения DayName по соответствующему nameShort.
     */
    private static final Map<String, DayName> MAP_VALUE = new HashMap<>();
    private String name;
    private String nameShort;

    static {
        for (DayName dayName : DayName.values()) {
            MAP_VALUE.put(dayName.getNameShort().toLowerCase(), dayName);
        }
    }

    DayName(String name, String nameShort) {
        this.name = name;
        this.nameShort = nameShort;
    }

    public String getName() {
        return name;
    }

    public String getNameShort() {
        return nameShort;
    }

    /**
     * Получить DayName из сокращенного имени.
     *
     * @param nameShort сокращенное имя.
     * @return DayName который соответствует сокращенному имени.
     */
    public static DayName valueOfNameShort(String nameShort) {
        DayName dayName = MAP_VALUE.get(nameShort.toLowerCase());
        if (dayName == null) {
            LOGGER.warn("valueOfNameShort return null!");
        }
        return dayName;
    }

    @Override
    public String toString() {
        return name;
    }
}

