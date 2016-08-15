package com.parsingHTML.logic.lessone;


import com.parsingHTML.logic.element.NumeratorName;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Класс для удобной работы с уроком.
 */
public class Lesson {
    private static final Logger LOGGER = LogManager.getLogger(Lesson.class);
    private String name;
    private String description;
    private int number;
    private NumeratorName numeratorName;
    private String teacher;


    public Lesson(String name, String description, int number, NumeratorName numeratorName, String teacher) {
        this.name = name;
        this.description = description;
        this.number = number;
        this.numeratorName = numeratorName;
        this.teacher = teacher;
        LOGGER.debug("Create " + toString());
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", number=" + number +
                ", numeratorName=" + numeratorName +
                ", teacher='" + teacher + '\'' +
                '}';
    }
}
