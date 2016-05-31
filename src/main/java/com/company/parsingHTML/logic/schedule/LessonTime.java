package com.company.parsingHTML.logic.schedule;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * В удобном виде хранит информацию о времени начала и конца урока.
 * В LessonTime указанно время начала и конца полу уроков.
 */
public class LessonTime implements DataXML {
    private static final Logger LOGGER = LogManager.getLogger(LessonTime.class);
    /**
     * Номер урока.
     */
    private final int number;
    /**
     * Время начала или конца полу урока.
     */
    private final String start1;
    private final String end1;
    private final String start2;
    private final String end2;

    public LessonTime(int number, String start1, String end1, String start2, String end2) {
        this.number = number;
        this.start1 = start1;
        this.end1 = end1;
        this.start2 = start2;
        this.end2 = end2;
        LOGGER.info("Create "+toString());
    }

    public int getNumber() {
        return number;
    }

    public String getStart1() {
        return start1;
    }

    public String getEnd1() {
        return end1;
    }

    public String getStart2() {
        return start2;
    }

    public String getEnd2() {
        return end2;
    }

    @Override
    public String toXML() {
        return "<lessonTime number=\""+number+"\" start1=\""+start1+"\" end1=\""+end1+"\" start2=\""+start2+"\" end2=\""+end2+"\"/>";
    }

    @Override
    public String toString() {
        return "LessonTime{" +
                "number=" + number +
                ", start1='" + start1 + '\'' +
                ", end1='" + end1 + '\'' +
                ", start2='" + start2 + '\'' +
                ", end2='" + end2 + '\'' +
                '}';
    }
}
