package com.parsingHTML.logic.parser.daytime;

import com.parsingHTML.logic.element.DayName;
import com.parsingHTML.logic.parser.ParserHTMLAbstract;
import com.parsingHTML.logic.parser.ParserHTMLFactory;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Получает из элемент LessonTime из .html.
 */
public class ParserDayTime extends ParserHTMLAbstract {
    private static final Logger LOGGER = LogManager.getLogger(ParserDayTime.class);
    /**
     * cssQuery - для получения номера пары.
     */
    public static final String cssQueryNumberLesson = "td.n_para";
    /**
     * cssQuery - для получения времени Понедельник-Пятница.
     */
    public static final String cssQueryTimeMonday = "td.n_para + td.time";
    /**
     * cssQuery - для получения времени Субботы.
     */
    public static final String cssQueryTimeSaturday = "td.time ~ td";

    @Override
    public Element parsing(Element element) {
        LOGGER.info("==== Parsing Element = " + element.nodeName() + " ====");
        Element weekTime = elementFactory.createWeekTime(null);

        Element monday = elementFactory.createDayTime(DayName.MONDAY);
        weekTime.appendChild(monday);
        addLessonTime(element, monday, cssQueryTimeMonday);

        weekTime.appendChild(elementFactory.createDayTime(DayName.TUESDAY, DayName.MONDAY));
        weekTime.appendChild(elementFactory.createDayTime(DayName.WEDNESDAY, DayName.MONDAY));
        weekTime.appendChild(elementFactory.createDayTime(DayName.THURSDAY, DayName.MONDAY));
        weekTime.appendChild(elementFactory.createDayTime(DayName.FRIDAY, DayName.MONDAY));
        Element saturday = elementFactory.createDayTime(DayName.SATURDAY);
        weekTime.appendChild(saturday);
        addLessonTime(element, saturday, cssQueryTimeSaturday);
        LOGGER.debug("====== return " + weekTime);
        return weekTime;
    }

    /**
     * Создаёт LessonTime.
     *
     * @param element  элемент из которого нужно спарсить.
     * @param dayTime  элемент в который нужно положить полученный LessonTime.
     * @param cssQuery запрос который получает элементы с временем.
     */
    private void addLessonTime(Element element, Element dayTime, String cssQuery) {
        LOGGER.debug("addLessonTime dayTime = " + dayTime.attr("dayName") + " cssQuery = " + cssQuery);
        Elements selectTimeLesson = element.select(cssQuery);

        Elements selectNumberLesson = element.select(cssQueryNumberLesson);

        for (int i = 0; i < selectNumberLesson.size(); i++) {
            selectTimeLesson.get(i).appendChild(selectNumberLesson.get(i));
        }

        if (checkTrs(selectTimeLesson)) {
            return;
        }

        ParserHTMLAbstract parserLessonTime = ParserHTMLFactory.createParserLessonTime();
        for (Element tr : selectTimeLesson) {
            Element lessonTime = parserLessonTime.parsing(tr);
            if (lessonTime != null) {
                LOGGER.debug("add " + lessonTime + toString());
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
