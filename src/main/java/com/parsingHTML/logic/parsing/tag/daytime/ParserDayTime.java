package com.parsingHTML.logic.parsing.tag.daytime;

import com.parsingHTML.logic.parsing.tag.ParserHTMLAbstract;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Парсит тег table в котором находятся время для каждой пары.
 */
public class ParserDayTime extends ParserHTMLAbstract {
    private static final Logger LOGGER = LogManager.getLogger(ParserDayTime.class);

    /**
     * Разделитель для time.
     */
    private final static String cssQueryNumberLesson = "td.n_para";
    private static final String cssQueryTimeMonday = " td.n_para + td.time";
    private static final String cssQueryTimeSaturday = " td.time ~ td";


    @Override
    public Element parsing(Element element) {
        LOGGER.info("==== Parsing Element = " + element.tagName()+" ====");
        Element weekTime = XMLFactory.createWeekTime();

        Element monday = XMLFactory.createDayTime("Понедельник");
        weekTime.appendChild(monday);
        addLessonTime(element, monday, cssQueryTimeMonday);

        weekTime.appendChild(XMLFactory.createDayTime("Вторник", "1"));
        weekTime.appendChild(XMLFactory.createDayTime("Среда", "1"));
        weekTime.appendChild(XMLFactory.createDayTime("Четверг", "1"));
        weekTime.appendChild(XMLFactory.createDayTime("Пятница", "1"));
        Element saturday = XMLFactory.createDayTime("Суббота");
        weekTime.appendChild(saturday);
        addLessonTime(element, saturday, cssQueryTimeSaturday);
        LOGGER.info("==== Parsing return : " + weekTime.toString()+" ====");
        return weekTime;
    }

    /**
     * Создаёт LessonTime.
     * @param element элемент из которого нужно спарсить.
     * @param dayTime элемент в который нужно полодить полученый LessonTime.
     * @param cssSelect запрос который получает элементы с временем.
     */
    private void addLessonTime(Element element, Element dayTime, String cssSelect) {
        LOGGER.debug("addLessonTime dayTime = "+dayTime.attr("dayName")+" cssSelect = "+cssSelect);
        Elements selectTimeLesson = element.select(cssSelect);

        Elements selectNumberLesson = element.select(cssQueryNumberLesson);
        for (int i = 0; i <7; i++) {
            selectTimeLesson.get(i).appendChild(selectNumberLesson.get(i));
        }

        if (checkTrs(selectTimeLesson)) {
            return;
        }

        ParserLessonTime parserLessonTime = new ParserLessonTime();
        for (Element tr : selectTimeLesson) {
            Element lessonTime = parserLessonTime.parsing(tr);
            if (lessonTime != null) {
                LOGGER.debug("add "+lessonTime+toString());
                dayTime.appendChild(lessonTime);
            }
        }
    }

    private boolean checkTrs(Elements selectTr) {
        if (selectTr.size() == 0) {
            LOGGER.warn("Wrong structure selectTr.size =" + selectTr.size());
            return true;
        }
        return false;
    }
}
