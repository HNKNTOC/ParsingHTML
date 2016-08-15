package com.parsingHTML.logic.extractor.xml;


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
    private String time1;
    private String time2;


    public Lesson(String name, String description, int number, NumeratorName numeratorName, String teacher) {
        this.name = name;
        this.description = description;
        this.number = number;
        this.numeratorName = numeratorName;
        this.teacher = teacher;
        LOGGER.debug("Create " + toString());
    }

    public Lesson(String name, String description, int number, NumeratorName numeratorName, String teacher, String time1, String time2) {
        this.name = name;
        this.description = description;
        this.number = number;
        this.numeratorName = numeratorName;
        this.teacher = teacher;
        this.time1 = time1;
        this.time2 = time2;
        LOGGER.debug("Create " + toString());
    }

    public Lesson() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public NumeratorName getNumeratorName() {
        return numeratorName;
    }

    public void setNumeratorName(NumeratorName numeratorName) {
        this.numeratorName = numeratorName;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getTime1() {
        return time1;
    }

    public void setTime1(String time1) {
        this.time1 = time1;
    }

    public String getTime2() {
        return time2;
    }

    public void setTime2(String time2) {
        this.time2 = time2;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", number=" + number +
                ", numeratorName=" + numeratorName +
                ", teacher='" + teacher + '\'' +
                ", time1='" + time1 + '\'' +
                ", time2='" + time2 + '\'' +
                '}';
    }
}
