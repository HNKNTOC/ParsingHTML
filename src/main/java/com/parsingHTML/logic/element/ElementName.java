package com.parsingHTML.logic.element;

/**
 * Имена для {@link org.jsoup.nodes.Element}
 */
public enum ElementName {
    SCHEDULE("schedule"),
    UPDATE_TIME("parsing_time"),
    UNIVERSITY("university"),
    WEEK_TIME("week_time"),
    DAY_TIME("day_time"),
    LESSON_TIME("lesson_time"),
    GROUP_LESSON("group_lesson"),
    DAY_LESSON("day_lesson"),
    LESSON("lesson");

    private String name;

    ElementName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
