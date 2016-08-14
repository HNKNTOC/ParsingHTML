package com.parsingHTML.logic.element;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Element;

import java.util.Date;

/**
 * Фабрика для Jsoup Element.
 */
public class ElementJsoupFactory implements ElementFactory {
    private static final Logger LOGGER = LogManager.getLogger(ElementJsoupFactory.class);
    private ElementJsoupBuilder builder = new ElementJsoupBuilder();

    @Override
    public Element createWeekTime() {
        LOGGER.debug("createWeekTime");
        builder.createElement(ElementName.WEEK_TIME);
        builder.setAttr("numerator", "false");
        return builder.getThisElement();
    }

    @Override
    public Element createDayTime(DayName dayName) {
        LOGGER.debug("createDayTime");
        builder.createElement(ElementName.DAY_TIME);
        builder.setAttr("dayName", dayName.getNameRu());
        return builder.getThisElement();
    }

    @Override
    public Element createDayTime(DayName dayName, String override) {
        LOGGER.debug("createDayTime");
        Element element = createDayTime(dayName);
        builder.setAttr("override", override);
        return element;
    }

    @Override
    public Element createLessonTime(String number, String start1, String end1, String start2, String end2) {
        LOGGER.debug("createLessonTime");
        builder.createElement(ElementName.LESSON_TIME);
        builder.setAttr("number", number);
        builder.setAttr("start1", start1);
        builder.setAttr("end1", end1);
        builder.setAttr("start2", start2);
        builder.setAttr("end2", end2);
        return builder.getThisElement();
    }

    @Override
    public Element createDayLesson(String dayName) {
        LOGGER.debug("createDayLesson");
        builder.createElement(ElementName.DAY_LESSON);
        builder.setAttr("dayName", dayName);
        return builder.getThisElement();
    }

    @Override
    public Element createLesson(String number, String nameLesson, String descriptionLesson, String teacher) {
        LOGGER.debug("createLesson");
        builder.createElement(ElementName.LESSON);
        builder.setAttr("number", number);
        builder.setAttr("lessonName", nameLesson);
        builder.setAttr("audience", descriptionLesson);
        builder.setAttr("teacher", teacher);
        return builder.getThisElement();
    }

    @Override
    public Element createLesson(String number, String nameLesson, String descriptionLesson, String teacher, String numerator) {
        LOGGER.debug("createLesson");
        createLesson(number, nameLesson, descriptionLesson, teacher);
        builder.setAttr("numerator", numerator);
        return builder.getThisElement();
    }

    @Override
    public Element createGroupLesson() {
        LOGGER.debug("createGroupLesson");
        builder.createElement(ElementName.GROUP_LESSON);
        return builder.getThisElement();
    }

    @Override
    public Element createSchedule() {
        LOGGER.debug("createSchedule");
        builder.createElement(ElementName.SCHEDULE);
        return builder.getThisElement();
    }

    @Override
    public Element createParsingTime() {
        LOGGER.debug("createParsingTime");
        builder.createElement(ElementName.UPDATE_TIME);
        builder.setText(new Date().toString());
        return builder.getThisElement();
    }

    @Override
    public Element createUniversity() {
        LOGGER.debug("createUniversity");
        builder.createElement(ElementName.UNIVERSITY);
        builder.setAttr("universityName", "БУКЭП");
        return builder.getThisElement();
    }
}
