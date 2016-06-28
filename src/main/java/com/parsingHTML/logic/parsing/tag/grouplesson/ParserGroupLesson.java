package com.parsingHTML.logic.parsing.tag.grouplesson;

import com.parsingHTML.logic.parsing.tag.ParserHTMLAbstract;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Присит элемент GroupLesson из html.
 */
public class ParserGroupLesson extends ParserHTMLAbstract {
    private static final Logger LOGGER = LogManager.getLogger(ParserGroupLesson.class);
    private final static String cssQuery = ".tbl_day";


    /**
     * Из переданого element выбирает элементы по cssQuery который нахлдится в перемннной cssQuery.
     * Полученный элементы отправляет parsingDayLesson().
     * @param element Элемент из которого будет парсится groupLesson.
     * @return groupLesson.
     */
    @Override
    public Element parsing(Element element) {
        LOGGER.debug("==== Parsing Element = " + element.tagName()+" ====");
        Element groupLesson = XMLFactory.createGroupLesson();
        Elements days = element.select(cssQuery);

        if(!checkElementSize(days,6)){
            return groupLesson;
        }

        parsingDayLesson(groupLesson, days);
        LOGGER.debug("====== return " + groupLesson);
        return groupLesson;
    }


    /**
     * Полученный дни парсит через ParserDayLesson.
     * @param groupLesson элемент в который будут добовлятся DayLesson.
     * @param days дни который нужно спарсить.
     */
    private void parsingDayLesson(Element groupLesson, Elements days) {
        ParserDayLesson parserDayLesson = new ParserDayLesson();
        Elements elements = parserDayLesson.parsingElements(days);
        for (Element element : elements) {
            groupLesson.appendChild(element);
        }
    }

}
