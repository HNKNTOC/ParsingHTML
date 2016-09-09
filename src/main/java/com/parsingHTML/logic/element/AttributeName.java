package com.parsingHTML.logic.element;

/**
 * Имя атрибута для {@link org.jsoup.nodes.Element}.
 */
public enum AttributeName {
    NUMERATOR("numerator"),
    NUMBER("number"),
    START1("start1"),
    START2("start2"),
    END1("end1"),
    END2("end2"),
    FACULTY("faculty"),
    SPECIALTY("specialty"),
    COURSE("course"),
    DESCRIPTION("description"),
    TEACHER("teacher"),
    OVERRIDE("override"),
    DAY_NUMBER("day-number"),
    DAY_TIME_NUMBER("day-time-number"),
    LESSON_NAME("lesson-name"),
    NAME_UNIVERSITY("name-university");


    private String name;

    AttributeName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
