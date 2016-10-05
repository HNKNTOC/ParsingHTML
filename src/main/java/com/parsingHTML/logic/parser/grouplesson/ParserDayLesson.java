package com.parsingHTML.logic.parser.grouplesson;

import com.parsingHTML.logic.element.DayName;
import com.parsingHTML.logic.parser.ParserHTMLAbstract;
import com.parsingHTML.logic.parser.ParserHTMLFactory;
import com.parsingHTML.logic.parser.ParsirHelper;
import com.parsingHTML.logic.parser.exception.ExceptionParser;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashMap;

/**
 * Получает элемент DayLesson из .html.
 */
public class ParserDayLesson extends ParserHTMLAbstract {
    private static final Logger LOGGER = LogManager.getLogger(ParserDayLesson.class);
    /**
     * cssQuery - выбор номер пары.
     */
    private final static String cssQueryNumPara = ".num_para";
    /**
     * cssQuery - выбор дня.
     */
    private final static String cssQueryDay = ".day";


    /**
     * Из element выбирает элемент через cssQuery в cssQueryNumPara.
     */
    @Override
    protected Element processingElement(Elements elements) throws ExceptionParser {

        Element dayLesson = elementFactory.createDayLesson(parsingDay(elements));

        Elements select = ParsirHelper.selectElements(elements, cssQueryNumPara);
        parsingLesson(select, dayLesson);
        return dayLesson;
    }

    /**
     * Получает из executeSelect Lesson с помощью ParserLesson и добавляет в dayLesson.
     * @param select Elements из которых нужно спарсить Lesson.
     * @param dayLesson Element в который нужно добавить Lesson.
     */
    private void parsingLesson(Elements select, Element dayLesson) throws ExceptionParser {
        ParserHTMLAbstract parserLesson = ParserHTMLFactory.createParserLesson();
        for (Element element : select) {
            Element lesson = parserLesson.parsing(element.parent());
            if (lesson != null) {
                dayLesson.appendChild(lesson);
            }
        }
    }

    /**
     * Получение дня из элемента.
     * @param element элемент из которого нужно спарсить день.
     * @return Имя дня.
     */
    private DayName parsingDay(Elements element) throws ExceptionParser {
        Elements select = element.select(cssQueryDay);
        String dayName = null;
        if (ParsirHelper.checkElementSize(select, 1)) {
            dayName = select.text();
        }
        LOGGER.debug("parsingDay return "+dayName);
        return toDayOfString(dayName);
    }

    private final static HashMap<String, DayName> DAY_NAME_OF_SCHEDULES = new HashMap<>();

    static {
        DAY_NAME_OF_SCHEDULES.put("Понедельник", DayName.MONDAY);
        DAY_NAME_OF_SCHEDULES.put("Вторник", DayName.TUESDAY);
        DAY_NAME_OF_SCHEDULES.put("Среда", DayName.WEDNESDAY);
        DAY_NAME_OF_SCHEDULES.put("Четверг", DayName.THURSDAY);
        DAY_NAME_OF_SCHEDULES.put("Пятница", DayName.FRIDAY);
        DAY_NAME_OF_SCHEDULES.put("Суббота", DayName.SATURDAY);
        DAY_NAME_OF_SCHEDULES.put("Воскресение", DayName.SUNDAY);
    }

    private static DayName toDayOfString(String dayName) {
        return DAY_NAME_OF_SCHEDULES.get(dayName);
    }
}
