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
    private String name = null;
    private String description = null;
    private int number = -1;
    private NumeratorName numeratorName = null;
    private String teacherNames = null;
    private String time1 = null;
    private String time2 = null;


    public Lesson(String name, String description, int number, NumeratorName numeratorName, String teacherNames) {
        this.name = name;
        this.description = description;
        this.number = number;
        this.numeratorName = numeratorName;
        this.teacherNames = teacherNames;
        LOGGER.debug("Create " + toString());
    }

    public Lesson(String name, String description, int number, NumeratorName numeratorName, String teacherNames, String time1, String time2) {
        this.name = name;
        this.description = description;
        this.number = number;
        this.numeratorName = numeratorName;
        this.teacherNames = teacherNames;
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

    public String getTeacherNames() {
        return teacherNames;
    }

    public void setTeacherNames(String teacherNames) {
        this.teacherNames = teacherNames;
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
        if (teacherNames != null ? !teacherNames.equals(lesson.teacherNames) : lesson.teacherNames != null)
            return false;
        if (time1 != null ? !time1.equals(lesson.time1) : lesson.time1 != null) return false;
        return time2 != null ? time2.equals(lesson.time2) : lesson.time2 == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + number;
        result = 31 * result + (numeratorName != null ? numeratorName.hashCode() : 0);
        result = 31 * result + (teacherNames != null ? teacherNames.hashCode() : 0);
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
                ", teacherNames='" + teacherNames + '\'' +
                ", time1='" + time1 + '\'' +
                ", time2='" + time2 + '\'' +
                '}';
    }
}
