package com.parsingHTML.logic.extractor.xml;

import com.parsingHTML.logic.element.AttributeName;
import com.parsingHTML.logic.element.DayName;
import com.parsingHTML.logic.element.ElementName;
import com.parsingHTML.logic.element.NumeratorName;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.text.MessageFormat;

/**
 * Заранее подготовленный выражение для XPath.
 */
public final class XPathExpression {
    private static final Logger LOGGER = LogManager.getLogger(XPathExpression.class);
    private static XPathExpressionBuilder builder = new XPathExpressionBuilder();

    private static MessageFormat expressionLesson;
    private static MessageFormat expressionLessonTime;

    static {
        builder.addNodeName(ElementName.SCHEDULE)
                .addNodeName(ElementName.UNIVERSITY)
                .addNodeName(ElementName.GROUP_LESSON)
                .addNodeName(ElementName.DAY_LESSON)
                .addAttributes(AttributeName.DAY_NUMBER, "'{0}'")
                .addNodeName(ElementName.LESSON)
                //TODO Слишком много ' ' ' кавычек
                .addAttributes(AttributeName.NUMERATOR, "'{1}'' or @numerator=''empty'");
        expressionLesson = new MessageFormat(builder.getExpression());
        builder.clear();
        LOGGER.debug("expressionLesson = " + expressionLesson);

        builder.addNodeName(ElementName.SCHEDULE)
                .addNodeName(ElementName.UNIVERSITY)
                .addNodeName(ElementName.WEEK_TIME)
                .addNodeName(ElementName.DAY_TIME)
                .addAttributes(AttributeName.DAY_TIME_NUMBER, "'{0}'")
                .addNodeName(ElementName.LESSON_TIME)
                .addAttributes(AttributeName.NUMBER, "'{1}'");
        expressionLessonTime = new MessageFormat(builder.getExpression());
        LOGGER.debug("expressionLessonTime = " + expressionLessonTime);
        builder.clear();
    }

    /**
     * Выражение для выбора уроков в конкретный день.
     *
     * @param dayName имя дня.
     * @return выражение.
     */
    public static String selectLesson(final DayName dayName, final NumeratorName numerator) {
        LOGGER.debug("selectLesson() dayName = " + dayName + " numerator = " + numerator);

        return expressionLesson.format(new Object[]{dayName, numerator.getName()});
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
        return expressionLessonTime.format(new Object[]{selectDayName, number});
    }

}
