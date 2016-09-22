package com.parsingHTML.logic.element;

import org.jsoup.nodes.Element;

/**
 * Интерфейс для фабрики создаюшей XML елемент.
 */
public interface ElementFactory {
    Element createWeekTime(NumeratorName numeratorName);

    Element createDayTime(DayName dayName);

    Element createDayTime(DayName dayName, DayName override);

    Element createLessonTime(String number, String start1, String end1, String start2, String end2);

    Element createDayLesson(DayName dayName);

    Element createLesson(String number, String nameLesson, String descriptionLesson, String teacher, String numerator);

    Element createGroupLesson();

    Element createSchedule();

    Element createParsingTime();

    Element createUniversity();
}
