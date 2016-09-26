package com.parsingHTML.logic.parser.daytime;

import com.parsingHTML.logic.element.DayName;
import com.parsingHTML.logic.element.ElementHelper;
import com.parsingHTML.logic.element.ElementJsoupBuilder;
import com.parsingHTML.logic.element.ElementName;
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
     * Для получение таблицы с временем.
     */
    public static final String cssSelectTableTime = "td[rowspan=\"2\"]";
    /**
     * cssQuery - для получения номера пары.
     */
    public static final String cssSelectNumberLesson = "td.n_para";
    /**
     * cssQuery - для получения времени Понедельник-Пятница.
     */
    public static final String cssSelectTime = "td.time";

    public ParserDayTime(ParserHTMLAbstract nextParser) {
        super(ElementName.DAY_TIME.getName(), nextParser, true);
    }

    @Override
    protected Element processingElement(Elements elements) {
        Elements returnElements = new Elements();

        Elements numberLessons = ElementHelper.selectElements(elements, cssSelectNumberLesson);

        Element monday = elementFactory.createDayTime(DayName.MONDAY);
        monday.insertChildren(0, parseTime(numberLessons, 0));
        returnElements.add(monday);

        returnElements.add(elementFactory.createDayTime(DayName.TUESDAY, DayName.MONDAY));
        returnElements.add(elementFactory.createDayTime(DayName.WEDNESDAY, DayName.MONDAY));
        returnElements.add(elementFactory.createDayTime(DayName.THURSDAY, DayName.MONDAY));
        returnElements.add(elementFactory.createDayTime(DayName.FRIDAY, DayName.MONDAY));

        Element saturday = elementFactory.createDayTime(DayName.SATURDAY);
        saturday.insertChildren(0, parseTime(numberLessons, 1));
        returnElements.add(saturday);

        return wrapperForElements(returnElements);
    }

    //TODO METHOD NO TEST.
    //TODO TWO TIMES calculated the time of their XTML.
    private Elements parseTime(Elements numberLessons, int index) {
        ParserLessonTime parserHTML = ParserHTMLFactory.createParserLessonTime();
        Elements select = new Elements();
        for (Element numberLesson : numberLessons) {
            Element wrapper = ElementJsoupBuilder.createWrapper();
            Element parent = numberLesson.parent();
            wrapper.appendChild(numberLesson);
            wrapper.appendChild(ElementHelper.selectElement(parent, cssSelectTime, index));
            LOGGER.debug("parseTime() wrapper = " + wrapper);
            select.add(parserHTML.parsing(wrapper));
        }
        return select;
    }

    @Override
    public Elements selectElement(Element elementHTML) {
        return elementHTML.select(cssSelectTableTime);
    }
}
