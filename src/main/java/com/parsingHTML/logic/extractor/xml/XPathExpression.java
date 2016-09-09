package com.parsingHTML.logic.extractor.xml;

import com.parsingHTML.logic.element.AttributeName;
import com.parsingHTML.logic.element.DayName;
import com.parsingHTML.logic.element.ElementName;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Заранее подготовленный выражение для XPath.
 */
public final class XPathExpression {
    private static final Logger LOGGER = LogManager.getLogger(XPathExpression.class);

    /**
     * Выражение для выбора уроков в конкретный день.
     *
     * @param dayName имя дня.
     * @return выражение.
     */
    public static String selectLesson(final DayName dayName) {
        LOGGER.debug("selectLesson() dayName = " + dayName);
        return String.format("%s/%s/%s/%s[@%s='%s']/%s",
                ElementName.SCHEDULE, ElementName.UNIVERSITY, ElementName.GROUP_LESSON,
                ElementName.DAY_LESSON, AttributeName.DAY_NUMBER, dayName, ElementName.LESSON);
    }

    /**
     * Выражение для выбора времени урока в конкретный день и номер урока.
     *
     * @param dayName имя для.
     * @param number  номер урока.
     * @return выражение.
     */
    public static String selectLessonTime(final DayName dayName, int number) {
        LOGGER.debug("selectLessonTime() dayName = " + dayName);
        // Для FRIDAY,THURSDAY,TUESDAY,WEDNESDAY выполняется тот же запрос что и для MONDAY.
        // Так как расписание времени у них одно и тоже.
        DayName selectDayName;
        if (dayName == DayName.SATURDAY) {
            selectDayName = DayName.SATURDAY;
        } else {
            selectDayName = DayName.MONDAY;
        }
        return String.format("%s/%s/%s/%s[@%s='%s']/%s[@%s='%d']",
                ElementName.SCHEDULE, ElementName.UNIVERSITY, ElementName.WEEK_TIME, ElementName.DAY_TIME,
                AttributeName.DAY_TIME_NUMBER, selectDayName, ElementName.LESSON_TIME, AttributeName.NUMBER, number);
    }

}
