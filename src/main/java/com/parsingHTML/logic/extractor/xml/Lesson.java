package com.parsingHTML.logic.extractor.xml;


import com.parsingHTML.logic.element.NumeratorName;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.Serializable;

/**
 * Класс для удобной работы с уроком.
 */
public class Lesson implements Serializable {
    transient private static final Logger LOGGER = LogManager.getLogger(Lesson.class);
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Lesson lesson = (Lesson) o;

        if (number != lesson.number) return false;
        if (name != null ? !name.equals(lesson.name) : lesson.name != null) return false;
        if (description != null ? !description.equals(lesson.description) : lesson.description != null) return false;
        if (numeratorName != lesson.numeratorName) return false;
        if (teacher != null ? !teacher.equals(lesson.teacher) : lesson.teacher != null) return false;
        if (time1 != null ? !time1.equals(lesson.time1) : lesson.time1 != null) return false;
        return time2 != null ? time2.equals(lesson.time2) : lesson.time2 == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + number;
        result = 31 * result + (numeratorName != null ? numeratorName.hashCode() : 0);
        result = 31 * result + (teacher != null ? teacher.hashCode() : 0);
        result = 31 * result + (time1 != null ? time1.hashCode() : 0);
        result = 31 * result + (time2 != null ? time2.hashCode() : 0);
        return result;
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
