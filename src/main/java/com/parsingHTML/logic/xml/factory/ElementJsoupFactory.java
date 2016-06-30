package com.parsingHTML.logic.xml.factory;

import com.parsingHTML.logic.xml.ElementName;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;

import java.util.Date;

/**
 * Фабрика для Jsoup Element.
 */
public class ElementJsoupFactory implements XMLFactory {
    private static final Logger LOGGER = LogManager.getLogger(ElementJsoupFactory.class);

    public static Element createElement(String name) {
        LOGGER.debug("createElement name = "+name);
        return new Element(Tag.valueOf(name), "");
    }

    public static Element createElementEmpty() {
        LOGGER.warn("createElementEmpty!!");
        Element element = createElement("ElementEmpty");
        element.text("This element was created instead of null!");
        return element;
    }

    @Override
    public Element createWeekTime() {
        Element element = createElement(ElementName.WEEK_TIME);
        element.attr("numerator", "false");
        LOGGER.debug("createWeekTime "+element);
        return element;
    }

    @Override
    public Element createDayTime(String dayName) {
        Element element = createElement(ElementName.DAY_TIME);
        element.attr("dayName", dayName);
        LOGGER.debug("createDayTime "+element);
        return element;
    }

    @Override
    public Element createDayTime(String dayName, String override) {
        Element element = createDayTime(dayName);
        element.attr("override", override);
        LOGGER.debug("createDayTime override "+element);
        return element;
    }

    @Override
    public Element createLessonTime(String number, String start1, String end1, String start2, String end2) {
        Element element = createElement(ElementName.LESSON_TIME);
        element.attr("number", number);
        element.attr("start1", start1);
        element.attr("end1", end1);
        element.attr("start2", start2);
        element.attr("end2", end2);
        LOGGER.debug("createLessonTime "+element);
        return element;
    }

    @Override
    public Element createDayLesson(String dayName) {
        Element element = createElement(ElementName.DAY_LESSON);
        element.attr("dayName", dayName);
        LOGGER.debug("createDayLesson "+element);
        return element;
    }

    @Override
    public Element createLesson(String number, String nameLesson, String descriptionLesson, String teacher) {
        Element element = createElement(ElementName.LESSON);
        element.attr("number", number);
        element.attr("lessonName", nameLesson);
        element.attr("audience", descriptionLesson);
        element.attr("teacher", teacher);
        LOGGER.debug("createLesson "+element);
        return element;
    }

    @Override
    public Element createLesson(String number, String nameLesson, String descriptionLesson, String teacher, String numerator) {
        Element element = createLesson(number, nameLesson, descriptionLesson, teacher);
        element.attr("numerator", numerator);
        LOGGER.debug("createLesson "+element);
        return element;
    }

    @Override
    public Element createGroupLesson() {
        Element element = createElement(ElementName.GROUP_LESSON);
        LOGGER.debug("createGroupLesson "+element);
        return element;
    }

    @Override
    public Element createSchedule() {
        Element element = createElement(ElementName.SCHEDULE);
        LOGGER.debug("createSchedule "+element);
        return element;
    }

    @Override
    public Element createUpdateTime() {
        Element element = createElement(ElementName.UPDATE_TIME);
        element.text(new Date().toString());
        LOGGER.debug("createUpdateTime "+element);
        return element;
    }

    @Override
    public Element createUniversity() {
        Element element = createElement(ElementName.UNIVERSITY);
        element.attr("universityName", "БУКЭП");
        LOGGER.debug("createUniversity "+element);
        return element;
    }
}
