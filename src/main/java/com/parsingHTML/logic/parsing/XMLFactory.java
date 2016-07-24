package com.parsingHTML.logic.parsing;

import org.jsoup.nodes.Element;

/**
 * Интерфейс для фабрики создаюшей XML елемент.
 */
public interface XMLFactory {
    Element createWeekTime();

    Element createDayTime(String dayName);

    Element createDayTime(String dayName, String override);

    Element createLessonTime(String number, String start1, String end1, String start2, String end2);

    Element createDayLesson(String dayName);

    Element createLesson(String number, String nameLesson, String descriptionLesson, String teacher);

    Element createLesson(String number, String nameLesson, String descriptionLesson, String teacher, String numerator);

    Element createGroupLesson();

    Element createSchedule();

    Element createParsingTime();

    Element createUniversity();
}
