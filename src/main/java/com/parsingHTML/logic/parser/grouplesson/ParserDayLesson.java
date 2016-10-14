package com.parsingHTML.logic.parser.grouplesson;

import com.parsingHTML.logic.element.DayName;
import com.parsingHTML.logic.element.ElementName;
import com.parsingHTML.logic.parser.ParserHTMLAbstract;
import com.parsingHTML.logic.parser.ParserHelper;
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
     * cssQuery - выбор дня.
     */
    private final static String CSS_QUERY_DAY_NAME = ".day > td";

    /**
     * cssQuery - выбор дня.
     */
    private final static String CSS_QUERY_TABLE_DAY = ".tbl_day";
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

    public ParserDayLesson(ParserHTMLAbstract nextParser) {
        super(ElementName.DAY_LESSON.getName(), nextParser);
    }

    private static DayName toDayOfString(String dayName) {
        return DAY_NAME_OF_SCHEDULES.get(dayName);
    }

    /**
     * Из element выбирает элемент через cssQuery в cssQueryNumPara.
     */
    @Override
    protected Element processingElement(Element element) throws ExceptionParser {
        return elementFactory.createDayLesson(parsingDay(element));
    }

    /**
     * Получение дня из элемента.
     *
     * @param element элемент из которого нужно спарсить день.
     * @return Имя дня.
     */
    private DayName parsingDay(Element element) throws ExceptionParser {
        Element dayNameElement = ParserHelper.selectElement(element, CSS_QUERY_DAY_NAME, 0);
        String dayName = dayNameElement.text();
        LOGGER.debug("parsingDay return " + dayName);
        return toDayOfString(dayName);
    }

    @Override
    protected Elements selectElementProcessing(Element element) throws ExceptionParser {
        return ParserHelper.selectElements(element, CSS_QUERY_TABLE_DAY);
    }
}
