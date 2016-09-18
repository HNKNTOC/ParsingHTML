package com.parsingHTML.logic.parser.grouplesson;

import com.parsingHTML.logic.element.ElementHelper;
import com.parsingHTML.logic.element.NumeratorName;
import com.parsingHTML.logic.parser.ParserHTMLAbstract;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Element;

import java.util.Arrays;

/**
 * Получаеть элемент Lesson из .html.
 */
public class ParserLesson extends ParserHTMLAbstract {
    private static final Logger LOGGER = LogManager.getLogger(ParserLesson.class);
    private static final String separator = "/-";
    /**
     * cssQuery - выбор дня.
     */
    private String cssQueryPara = ".para";


    @Override
    public Element parsing(Element element) {
        LOGGER.info("==== Parsing Element = " + element.nodeName() + " ====");

        String number = parsingNumber(element);
        String numerator = parsingNumerator(element);

        String[] split = divideString(ElementHelper.selectElement(element, cssQueryPara, 0));

        String nameLesson = split[0];
        String descriptionLesson = split[1];

        Element dayLesson = elementFactory.createLesson(number, nameLesson, descriptionLesson, "Name Teacher", numerator);
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
        LOGGER.debug("divideString .html = " + html);
        String replace = html.replace("<br>", separator);
        element.html(replace);
        String[] split = element.text().split(separator);
        if (split.length != 2) {
            LOGGER.warn("divideString failed to divide string!! split.length = " + split.length);
            return new String[]{"", ""};
        }
        LOGGER.debug("divideString return " + Arrays.toString(split));
        return split;
    }

    /**
     * Получение NumeratorName.
     * Ищет строку из переменной numeratorTrue и numeratorFalse если находит то возвращает её.
     *
     * @param element элемент у которого нужно получить NumeratorName.
     * @return null если получить не удалось.
     */
    private String parsingNumerator(Element element) {
        String text = element.text();
        final String numerator = NumeratorName.NUMERATOR.getName();
        final String denominator = NumeratorName.DENOMINATOR.getName();
        LOGGER.debug("parsingNumerator text = " + text);
        if (text.contains(numerator)) {
            LOGGER.debug("parsingNumerator return " + numerator);
            return numerator;
        }

        if (text.contains(denominator)) {
            LOGGER.debug("parsingNumerator return " + denominator);
            return denominator;
        }
        LOGGER.debug("parsingNumerator return null");
        return NumeratorName.EMPTY.getName();
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
