package com.parsingHTML.logic.parser.daytime;

import com.parsingHTML.logic.element.AttributeName;
import com.parsingHTML.logic.element.DayName;
import com.parsingHTML.logic.element.ElementJsoupBuilder;
import com.parsingHTML.logic.element.ElementName;
import com.parsingHTML.logic.parser.ParserHTMLAbstract;
import com.parsingHTML.logic.parser.ParserHelper;
import com.parsingHTML.logic.parser.exception.ExceptionParser;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Получает из элемент <day_time>.
 */
public class ParserDayTime extends ParserHTMLAbstract {
    private static final Logger LOGGER = LogManager.getLogger(ParserDayTime.class);
    /**
     * Для получение таблицы с временем.
     */
    public static final String cssQueryTableTime = ".knock";
    /**
     * cssQuery - для получения номера пары.
     */
    public static final String cssQueryNumberLesson = "td.n_para";
    /**
     * cssQuery - для получения времени Понедельник-Пятница.
     */
    public static final String cssQueryTime = ".knock > tbody > tr > td.time:nth-child(2)";

    public ParserDayTime(ParserHTMLAbstract nextParser) {
        super(ElementName.DAY_TIME.getName(), nextParser);
    }

    /**
     * Создаёт <day_time> и получает значение для параметров.
     *
     * @param element Element из коророго будет получен
     * @return Element <day_time>.
     */
    @Override
    protected Element processingElement(Element element) {
        int day = selectDayNumber(element);
        DayName dayName = DayName.values()[day];
        if (0 < day && day < 5) {
            return elementFactory.createDayTime(dayName, DayName.MONDAY);
        }
        return elementFactory.createDayTime(dayName);
    }


    private int selectDayNumber(Element returnElements) {
        LOGGER.debug("selectDayNumber = " + returnElements);
        String attr = returnElements.attr(AttributeName.DAY_NUMBER.getName());
        int i = Integer.parseInt(attr) - 2;
        LOGGER.debug("selectDayNumber attr = " + attr + " return " + i);
        return i;
    }

    /**
     * Возвращает 6 элементов.
     * Каждый элемент соответствует дню с Понеделника до Субботы.
     *
     * @param element откуда нужно получить элементы.
     * @return Дни недели с Понеделника до Субботы.
     */
    @Override
    protected Elements selectElementProcessing(Element element) throws ExceptionParser {
        final Element tableTime = ParserHelper.selectElement(element, cssQueryTableTime, 0);
        final Elements elements = new Elements();
        final DayName[] values = DayName.values();
        elements.add(createDay(tableTime, DayName.MONDAY));
        for (int i = 1; i < 5; i++) {
            int dayNumber = values[i].getDayNumber();
            elements.add(createWrapperDay(dayNumber));
        }
        elements.add(createDay(tableTime, DayName.SATURDAY));

        return elements;
    }

    private Element createWrapperDay(int dayNumber) {
        final Element wrapper = ElementJsoupBuilder.createWrapper();
        wrapper.attr(AttributeName.DAY_NUMBER.getName(), String.valueOf(dayNumber));
        return wrapper;
    }

    private Element createDay(Element tableTime, DayName dayName) throws ExceptionParser {
        Element wrapperDay = createWrapperDay(dayName.getDayNumber());
        wrapperDay.insertChildren(0, ParserHelper.selectElements(tableTime, cssQueryTime));
        return wrapperDay;
    }

}
