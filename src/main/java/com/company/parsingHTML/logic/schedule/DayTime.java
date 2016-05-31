package com.company.parsingHTML.logic.schedule;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * В удобном виде хранит информацию о расписании времени на дне.
 * В DayTime указывается все уроки которые пройдут в этот день и
 * имя дня к примеру monday,tuesday и ТД.
 */
public class DayTime implements DataXML {
    private static final Logger LOGGER = LogManager.getLogger(DayTime.class);

    /**
     * Возможные имена для дня.
     */
    public static final String MONDAY = "monday";
    public static final String TUESDAY = "tuesday";
    public static final String WEDNESDAY = "wednesday";
    public static final String THURSDAY = "thursday";
    public static final String FRIDAY = "friday";
    public static final String SATURDAY = "saturday";
    public static final String[] DAY_NAME_ALL =
            {MONDAY, THURSDAY, TUESDAY, WEDNESDAY, FRIDAY, SATURDAY};
    /**
     * Имя дня. Возможные варианты предоставлены в константах.
     */
    private final String dayName;
    /**
     * Наследник применяется в том случаи когда
     * У двух и более дней одинаковое количество
     * уроков и их расписание.
     * В таком случаи в override указывается ссылка на DayTime
     * у которого такое же расписание.
     */
    private final DayTime override;
    private final List<LessonTime> lessonTimeList;

    /**
     * Конструктор без наследника.
     *
     * @param dayName имя дня.
     */
    public DayTime(String dayName) {
        this(dayName, null);
    }

    /**
     * Конструктор c наследника.
     *
     * @param dayName  имя дня.
     * @param override ссылка на наследника.
     */
    public DayTime(String dayName, DayTime override) {
        checkDayName(dayName);
        this.dayName = dayName;
        this.override = override;
        LOGGER.info("Create "+toString());
        lessonTimeList = new ArrayList<>();
    }

    /**
     * Добавляет урок.
     * Но если override != null то добавление не происходит.
     * Потому что данный день наследуется от override.
     *
     * @param lessonTime урок.
     */
    public void addLessonTime(LessonTime lessonTime) {
        LOGGER.debug("AddLessonTime " + lessonTime.toString());
        if (override != null) {
            LOGGER.debug("AddLessonTime not add! (override != null)");
            return;
        }
        lessonTimeList.add(lessonTime);
    }

    public List<LessonTime> getLessonTimeList() {
        if (override != null) {
            LOGGER.debug("GetLessonTimeList override days.");
            return override.getLessonTimeList();
        }
        LOGGER.debug("GetLessonTimeList their days.");
        return lessonTimeList;
    }

    public String getDayName() {
        return dayName;
    }

    public DayTime getOverride() {
        return override;
    }

    /**
     * Проверка является ли строка названием дня.
     *
     * @param dayName имя которое нужно проверить.
     * @return true если имя совпало хоть с одним дней недели.
     */
    public boolean checkDayName(String dayName) {
        LOGGER.debug("checkDayName " + dayName);
        for (String s : DAY_NAME_ALL) {
            if (dayName.equals(s)) {
                LOGGER.debug(dayName + " == " + s);
                return true;
            }
        }
        LOGGER.warn(dayName + " do not match!");
        return false;
    }

    @Override
    public String toXML() {
        String xml = "<dayTime dayName=\""+dayName+"\">";
        for (LessonTime lessonTime : getLessonTimeList()) {
            xml = xml+"\n    "+lessonTime.toXML();
        }
        return xml+"\n</dayTime>";
    }

    @Override
    public String toString() {
        return "DayTime{" +
                "dayName='" + dayName + '\'' +
                ", override=" + override +
                '}';
    }
}
