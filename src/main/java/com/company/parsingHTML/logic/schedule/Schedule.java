package com.company.parsingHTML.logic.schedule;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * В удобном виде хранит информацию о рассписании.
 */
public class Schedule {
    private static final Logger LOGGER = LogManager.getLogger(Schedule.class);
    private final String updateTime;
    private final String university;
    private WeekLesson weekLesson;
    private GroupLesson groupLesson;

    public Schedule(boolean numerator, String updateTime, String university) {
        this.weekLesson = new WeekLesson(numerator);
        this.updateTime = updateTime;
        this.university = university;
        this.groupLesson = new GroupLesson();
        LOGGER.info("Create "+toString());
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public String getUniversity() {
        return university;
    }

    public WeekLesson getWeekLesson() {
        return weekLesson;
    }

    public void setWeekLesson(WeekLesson weekLesson) {
        this.weekLesson = weekLesson;
    }

    public GroupLesson getGroupLesson() {
        return groupLesson;
    }

    public void setGroupLesson(GroupLesson groupLesson) {
        this.groupLesson = groupLesson;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "updateTime='" + updateTime + '\'' +
                ", university='" + university + '\'' +
                ", weekLesson=" + weekLesson +
                ", groupLesson=" + groupLesson +
                '}';
    }
}
