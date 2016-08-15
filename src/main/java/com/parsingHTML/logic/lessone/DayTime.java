package com.parsingHTML.logic.lessone;

/**
 * Класс для удобной работы с временем урока.
 */
public class DayTime {
    private int number;
    private String start1;
    private String start2;
    private String end1;
    private String end2;
    private static final String SEPARATOR = "-";

    public DayTime(int number, String start1, String start2, String end1, String end2) {
        this.number = number;
        this.start1 = start1;
        this.start2 = start2;
        this.end1 = end1;
        this.end2 = end2;
    }

    public int getNumber() {
        return number;
    }

    public String getStart1() {
        return start1;
    }

    public String getStart2() {
        return start2;
    }

    public String getEnd1() {
        return end1;
    }

    public String getEnd2() {
        return end2;
    }

    public String getTimeFirstLesson(String separator) {
        return start1 + separator + end1;
    }

    public String getTimeFirstLesson() {
        return getTimeFirstLesson(SEPARATOR);
    }

    public String getTimeSecondLesson() {
        return getTimeSecondLesson(SEPARATOR);
    }

    public String getTimeSecondLesson(String separator) {
        return start2 + separator + end2;
    }

    @Override
    public String toString() {
        return "DayTime{" +
                "number=" + number +
                ", start1='" + start1 + '\'' +
                ", start2='" + start2 + '\'' +
                ", end1='" + end1 + '\'' +
                ", end2='" + end2 + '\'' +
                '}';
    }
}
