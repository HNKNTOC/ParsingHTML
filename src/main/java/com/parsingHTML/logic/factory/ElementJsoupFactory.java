package com.parsingHTML.logic.factory;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;

import java.util.Date;

/**
 * Фабрика для Jsoup Element.
 */
public class ElementJsoupFactory {
    private static final Logger LOGGER = LogManager.getLogger(ElementJsoupFactory.class);
    private  Element createElementEmpty(String name) {
        return new Element(Tag.valueOf(name),"");
    }

    public  Element createWeekTime(){
        Element element = createElementEmpty("weekTime");
        element.attr("numerator","false");
        return element;
    }

    public Element createDayTime(String dayName) {
        Element element = createElementEmpty("dayTime");
        element.attr("dayName",dayName);
        return element;
    }

    public Element createDayTime(String dayName,String override) {
        Element element = createDayTime(dayName);
        element.attr("override",override);
        return element;
    }

    public Element createLessonTime(String number, String start1, String end1, String start2, String end2) {
        Element lessonTime = createElementEmpty("lessonTime");
        lessonTime.attr("number",number);
        lessonTime.attr("start1",start1);
        lessonTime.attr("end1",end1);
        lessonTime.attr("start2",start2);
        lessonTime.attr("end2",end2);
        return lessonTime;
    }

    public Element createDayLesson(String dayName) {
        Element lessonTime = createElementEmpty("dayLesson");
        lessonTime.attr("dayName",dayName);
        return lessonTime;
    }

    public Element createLesson(String number, String nameLesson, String descriptionLesson, String teacher) {
        Element lesson = createElementEmpty("lesson");
        lesson.attr("number", number);
        lesson.attr("lessonName", nameLesson);
        lesson.attr("audience", descriptionLesson);
        lesson.attr("teacher", teacher);
        return lesson;
    }

    public Element createLesson(String number, String nameLesson, String descriptionLesson, String teacher, String numerator) {
        Element lesson = createLesson(number, nameLesson, descriptionLesson, teacher);
        lesson.attr("numerator",numerator);
        return lesson;
    }

    public Element createGroupLesson() {
        return createElementEmpty("groupLesson");
    }

    public Element createSchedule() {
        return createElementEmpty("schedule");
    }

    public Element createUpdateTime() {
        Element updateTime = createElementEmpty("updateTime");
        updateTime.text(new Date().toString());
        return updateTime;
    }

    public Element createUniversity() {
        Element university = createElementEmpty("university");
        university.attr("universityName","БУКЭП");
        return university;
    }
}
