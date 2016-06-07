package com.company.parsingHTML.logic.parsing.tag.daytime;

import com.company.parsingHTML.logic.parsing.tag.ParserHTMLAbstract;
import com.company.parsingHTML.logic.xml.ElementXML;
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

    public ParserDayTime() {
        super(new String[]{"table"});
    }

    @Override
    public ElementXML parsing(Element element) {
        LOGGER.info("parsing Tag name = " + element.nodeName());
        ElementXML weekTime = new ElementXML("weekTime");
        weekTime.addAttribute("numerator", "false");

        ElementXML dayTimeMonday = new ElementXML("dayTime");

        weekTime.addChildren(dayTimeMonday);

        ElementXML monday = newDayTime("monday", weekTime);
        addLessonTime(element, monday, cssQueryTimeMonday);

        newDayTimeOverride("tuesday", weekTime);
        newDayTimeOverride("wednesday", weekTime);
        newDayTimeOverride("thursday", weekTime);
        newDayTimeOverride("friday", weekTime);
        ElementXML saturday = newDayTime("saturday", weekTime);
        addLessonTime(element, saturday, cssQueryTimeSaturday);

        return weekTime;
    }

    private void addLessonTime(Element element, ElementXML dayTime, String cssSelect) {
        LOGGER.debug("addLessonTime dayTime = "+dayTime.getAttributes("dayName")+" cssSelect = "+cssSelect);
        Elements selectTimeLesson = element.select(cssSelect);

        Elements selectNumberLesson = element.select(cssQueryNumberLesson);
        for (int i = 0; i <7; i++) {
            selectTimeLesson.get(i).appendChild(selectNumberLesson.get(i));
        }

        if (checkTrs(selectTimeLesson)) {
            return;
        }

        ParserTr parserTr = new ParserTr();
        for (Element tr : selectTimeLesson) {
            ElementXML lessonTime = parserTr.parsing(tr);
            if (lessonTime != null) {
                dayTime.addChildren(lessonTime);
            }
        }
    }

    private ElementXML newDayTime(String s, ElementXML weekTime) {
        ElementXML dayTime = new ElementXML("dayTime");
        dayTime.addAttribute("dayName", s);
        weekTime.addChildren(dayTime);
        return dayTime;
    }

    private ElementXML newDayTimeOverride(String s, ElementXML weekTime) {
        ElementXML dayTime = newDayTime(s,weekTime);
        dayTime.addAttribute("override", "1");
        return dayTime;
    }

    private boolean checkTrs(Elements selectTr) {
        if (selectTr.size() == 0) {
            LOGGER.debug("Wrong structure selectTr.size =" + selectTr.size());
            return true;
        }
        return false;
    }
}
