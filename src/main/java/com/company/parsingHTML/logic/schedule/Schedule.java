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
    private WeekTime weekTime;
    private GroupLesson groupLesson;

    public Schedule(String updateTime, String university) {
        this.updateTime = updateTime;
        this.university = university;
        LOGGER.info("Create "+toString());
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public String getUniversity() {
        return university;
    }

    public WeekTime getWeekTime() {
        return weekTime;
    }

    public void setWeekTime(WeekTime weekTime) {
        this.weekTime = weekTime;
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
                '}';
    }
}
