package com.parsingHTML.logic.element;

/**
 * Имя атрибута для {@link org.jsoup.nodes.Element}.
 */
public enum AttributeName {
    NUMERATOR("numerator"),
    NAME("name"),
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
    OVERRIDE("override");


    private String name;

    AttributeName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
