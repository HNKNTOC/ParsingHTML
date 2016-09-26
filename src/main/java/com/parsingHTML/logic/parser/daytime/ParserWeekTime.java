package com.parsingHTML.logic.parser.daytime;

import com.parsingHTML.logic.element.NumeratorName;
import com.parsingHTML.logic.parser.ParserHTMLAbstract;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Получаеть элемент WeekTime из .html.
 * Создаёт WeekTime и парсит знаменатель недели.
 */
public class ParserWeekTime extends ParserHTMLAbstract {
    private static final Logger LOGGER = LogManager.getLogger(ParserWeekTime.class);
    /**
     * cssSelect - для получения NumeratorName.
     */
    private static final String cssSelectNumerator = "html head script";

    public ParserWeekTime(ParserHTMLAbstract nextParserHTMLAbstract) {
        super("WeekTime", nextParserHTMLAbstract);
    }

    @Override
    public Element processingElement(Elements elements) {
        return elementFactory.createWeekTime(parsingNumeratorName(elements));
    }

    private NumeratorName parsingNumeratorName(Elements elements) {
        String html = elements.select(cssSelectNumerator).html();
        if (html.contains("Числитель")) {
            LOGGER.info("This week is NUMERATOR!");
            return NumeratorName.NUMERATOR;
        }
        if (html.contains("Знаменатель")) {
            LOGGER.info("This week is DENOMINATOR!");
            return NumeratorName.DENOMINATOR;
        }
        LOGGER.warn("This week is EMPTY!");
        reportException("Failed parsingNumeratorName()");
        return NumeratorName.EMPTY;
    }
}
