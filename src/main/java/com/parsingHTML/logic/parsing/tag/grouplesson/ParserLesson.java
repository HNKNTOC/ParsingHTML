package com.parsingHTML.logic.parsing.tag.grouplesson;

import com.parsingHTML.logic.parsing.tag.ParserHTMLAbstract;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Element;

import java.util.Arrays;

/**
 * Парсить элемент Lesson из html.
 */
public class ParserLesson extends ParserHTMLAbstract {
    private static final Logger LOGGER = LogManager.getLogger(ParserLesson.class);
    private static final String separator = "/-";
    private static final String numeratorTrue = "Числ.";
    private static final String numeratorFalse = "Знам.";


    @Override
    public Element parsing(Element element) {
        LOGGER.info("==== Parsing Element = " + element.tagName() + " ====");

        String number = parsingNumber(element);
        String numerator = parsingNumerator(element);

        String[] split = divideString(selectElement(element,".para",0));

        String nameLesson = split[0];
        String descriptionLesson = split[1];

        Element dayLesson;
        if (numerator == null) {
            dayLesson = XMLFactory.createLesson(number, nameLesson, descriptionLesson, "Name Teacher");
        } else {
            dayLesson = XMLFactory.createLesson(number, nameLesson, descriptionLesson, "Name Teacher", numerator);
        }
        LOGGER.debug("====== return " + dayLesson);
        return dayLesson;
    }

    /**
     * Делит строку на пополам по первому dr элементу.
     *
     * @param element элемент текст которого нужно разделить.
     * @return массив с разделёнными строками.
     */
    private String[] divideString(Element element) {
        String html = element.html();
        LOGGER.debug("divideString html = "+html);
        String replace = html.replace("<br>", separator);
        element.html(replace);
        String[] split = element.text().split(separator);
        if (split.length != 2) {
            LOGGER.warn("divideString failed to divide string!! split.length = "+split.length);
            return new String[]{"", ""};
        }
        LOGGER.debug("divideString return "+ Arrays.toString(split));
        return split;
    }

    /**
     * Получение Numerator.
     * Ищет строку из переменной numeratorTrue и numeratorFalse если находит то возвращает её.
     * @param element элемент у которого нужно получить Numerator.
     * @return null если получить не удалось.
     */
    private String parsingNumerator(Element element) {
        String text = element.text();
        LOGGER.debug("parsingNumerator text = " + text);
        if (text.contains(numeratorTrue)) {
            LOGGER.debug("parsingNumerator return "+numeratorTrue);
            return numeratorTrue;
        }
        if (text.contains(numeratorFalse)) {
            LOGGER.debug("parsingNumerator return "+numeratorFalse);
            return numeratorFalse;
        }
        LOGGER.debug("parsingNumerator return null");
        return null;
    }

    /**
     * Получение номера.
     *
     * @param element элемент у которого нужно получить номер.
     * @return null если получить не удалось.
     */
    private String parsingNumber(Element element) {
        String text = element.text();
        LOGGER.debug("parsingNumber txt = " + text);
        if (text.length() != 1) {
            String number = text.split(" ")[0];
            LOGGER.debug("parsingNumber return " + number);
            return number;
        }
        LOGGER.warn("parsingNumber failed! return null");
        return "null";
    }
}
