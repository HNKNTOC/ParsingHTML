package com.company.parsingHTML.logic.schedule;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * В удобном виде хранит информацию о днях.
 * В WeekTime указанны все учебные дни на неделю.
 * Также указанно евляется ли неделя числителем.
 */
public class WeekTime implements DataXML {
    private static final Logger LOGGER = LogManager.getLogger(WeekTime.class);
    private final List<DayTime> dayTimeList;
    /**
     * Евляется ли неделя числителем.
     */
    private final boolean numerator;

    public WeekTime(boolean numerator) {
        this.dayTimeList = new ArrayList<>();
        this.numerator = numerator;
        LOGGER.info("Create "+toString());
    }

    public boolean isNumerator() {
        return numerator;
    }

    public List<DayTime> getDayTimeList() {
        return dayTimeList;
    }

    public void addDayTime(DayTime dayTime) {
        LOGGER.debug("addDayTime "+dayTime);
        this.dayTimeList.add(dayTime);
    }

    @Override
    public String toXML() {
        String xml = "<weekTime numerator=\""+numerator+"\">";
        for (DayTime dayTime : dayTimeList) {
            xml = xml+"\n"+dayTime.toXML();
        }
        return xml+"\n</weekTime>";
    }

    @Override
    public String toString() {
        return "WeekTime{" +
                "numerator=" + numerator +
                '}';
    }
}
