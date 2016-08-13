package com.parsingHTML.logic.parsing.html.grouplesson;

import com.parsingHTML.logic.parsing.ElementHelper;
import com.parsingHTML.logic.parsing.html.ParserHTMLAbstract;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Парсит элемент DayLesson из .html.
 */
public class ParserDayLesson extends ParserHTMLAbstract {
    private static final Logger LOGGER = LogManager.getLogger(ParserDayLesson.class);
    private final static String cssQueryNumPara = ".num_para";
    private final static String cssQueryDay = ".day";


    /**
     * Из element выбирает элемент через cssQuery в cssQueryNumPara.
     *
     * @param element
     * @return
     */
    @Override
    public Element parsing(Element element) {
        LOGGER.debug("==== Parsing Element = " + element.nodeName() + " ====");

        Element dayLesson = XMLFactory.createDayLesson(parsingDay(element));

        Elements select = ElementHelper.selectElements(element, cssQueryNumPara);
        parsingLesson(select, dayLesson);
        LOGGER.debug("====== return " + dayLesson);
        return dayLesson;
    }

    /**
     * Парсит из select Lesson с помощью ParserLesson и добавляет в dayLesson.
     * @param select Elements из которых нужно спарсить Lesson.
     * @param dayLesson Element в который нужно добавить Lesson.
     */
    private void parsingLesson(Elements select, Element dayLesson) {
        ParserHTMLAbstract parserLesson = parserFactory.createParserLesson();
        for (Element element : select) {
            Element lesson = parserLesson.parsing(element.parent());
            if (lesson != null) {
                dayLesson.appendChild(lesson);
            }
        }
    }

    /**
     * Из element выбирает элемент через cssQuery в cssQueryDay.
     * Получает из выбранного элемента text.
     * @param element элемент из которого нужно спарсить день.
     * @return Имя дня.
     */
    private String parsingDay(Element element) {
        Elements select = element.select(cssQueryDay);
        String dayName = "null";
        if (ElementHelper.checkElementsSize(select, 1)) {
            dayName = select.text();
        }
        LOGGER.debug("parsingDay return "+dayName);
        return dayName;
    }
}
