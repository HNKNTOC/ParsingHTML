package com.parsingHTML.logic.xml.factory;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;

import java.util.Date;

/**
 * Фабрика для Jsoup Element.
 */
public class ElementFactoryJsoup implements XMLFactory {
    private static final Logger LOGGER = LogManager.getLogger(ElementFactoryJsoup.class);

    private Element createElement(String name) {
        return new Element(Tag.valueOf(name), "");
    }

    public static Element createElementEmpty() {
        Element element = new Element(Tag.valueOf("ElementEmpty"), "");
        element.text("This element was created instead of null!");
        return element;
    }

    @Override
    public Element createWeekTime() {
        Element element = createElement("weekTime");
        element.attr("numerator", "false");
        return element;
    }

    @Override
    public Element createDayTime(String dayName) {
        Element element = createElement("dayTime");
        element.attr("dayName", dayName);
        return element;
    }

    @Override
    public Element createDayTime(String dayName, String override) {
        Element element = createDayTime(dayName);
        element.attr("override", override);
        return element;
    }

    @Override
    public Element createLessonTime(String number, String start1, String end1, String start2, String end2) {
        Element lessonTime = createElement("lessonTime");
        lessonTime.attr("number", number);
        lessonTime.attr("start1", start1);
        lessonTime.attr("end1", end1);
        lessonTime.attr("start2", start2);
        lessonTime.attr("end2", end2);
        return lessonTime;
    }

    @Override
    public Element createDayLesson(String dayName) {
        Element lessonTime = createElement("dayLesson");
        lessonTime.attr("dayName", dayName);
        return lessonTime;
    }

    @Override
    public Element createLesson(String number, String nameLesson, String descriptionLesson, String teacher) {
        Element lesson = createElement("lesson");
        lesson.attr("number", number);
        lesson.attr("lessonName", nameLesson);
        lesson.attr("audience", descriptionLesson);
        lesson.attr("teacher", teacher);
        return lesson;
    }

    @Override
    public Element createLesson(String number, String nameLesson, String descriptionLesson, String teacher, String numerator) {
        Element lesson = createLesson(number, nameLesson, descriptionLesson, teacher);
        lesson.attr("numerator", numerator);
        return lesson;
    }

    @Override
    public Element createGroupLesson() {
        return createElement("groupLesson");
    }

    @Override
    public Element createSchedule() {
        return createElement("schedule");
    }

    @Override
    public Element createUpdateTime() {
        Element updateTime = createElement("updateTime");
        updateTime.text(new Date().toString());
        return updateTime;
    }

    @Override
    public Element createUniversity() {
        Element university = createElement("university");
        university.attr("universityName", "БУКЭП");
        return university;
    }
}
