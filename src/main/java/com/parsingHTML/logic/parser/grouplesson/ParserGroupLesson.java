package com.parsingHTML.logic.parser.grouplesson;

import com.parsingHTML.logic.element.ElementHelper;
import com.parsingHTML.logic.parser.ParserHTMLAbstract;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Получает элемент GroupLesson из .html.
 */
public class ParserGroupLesson extends ParserHTMLAbstract {
    private static final Logger LOGGER = LogManager.getLogger(ParserGroupLesson.class);
    public final static String cssQueryTableDay = ".tbl_day";


    /**
     * Из переданного element выбирает элементы по cssQuery который находится в переменной cssQuery.
     * Полученный элементы отправляет parsingDayLesson().
     * @param element Элемент из которого будет парсит groupLesson.
     * @return groupLesson.
     */
    @Override
    public Element parsing(Element element) {
        LOGGER.debug("==== Parsing Element = " + element.nodeName() + " ====");
        Element groupLesson = ElementFactory.createGroupLesson();
        Elements days = ElementHelper.selectElements(element, cssQueryTableDay);

        parsingDayLesson(groupLesson, days);
        LOGGER.debug("====== return " + groupLesson);
        return groupLesson;
    }


    /**
     * Полученный дни парсит через ParserDayLesson.
     * @param groupLesson элемент в который будут добавляться DayLesson.
     * @param days дни который нужно спарсить.
     */
    private void parsingDayLesson(Element groupLesson, Elements days) {
        ParserHTMLAbstract parserDayLesson = parserFactory.createParserDayLesson();
        Elements elements = parserDayLesson.parsingElements(days);
        for (Element element : elements) {
            groupLesson.appendChild(element);
        }
    }

}
