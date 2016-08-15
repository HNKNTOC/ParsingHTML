package com.parsingHTML.logic.lessone;

import com.parsingHTML.logic.element.AttributeName;
import com.parsingHTML.logic.element.DayName;
import com.parsingHTML.logic.element.ElementName;

/**
 * Заранее подготовленный выражение для XPath.
 */
public final class XPathExpression {

    /**
     * Выражение для выбора уроков в конкретный день.
     *
     * @param dayName имя дня.
     * @return выражение.
     */
    public static String selectLesson(final DayName dayName) {
        return String.format("%s/%s/%s/%s[@%s='%s']/%s",
                ElementName.SCHEDULE, ElementName.UNIVERSITY, ElementName.GROUP_LESSON,
                ElementName.DAY_LESSON, AttributeName.NAME, dayName.getNameRu(), ElementName.LESSON);
    }
}
