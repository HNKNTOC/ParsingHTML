package com.parsingHTML.logic.parser.daytime;

import com.parsingHTML.logic.element.ElementHelper;
import com.parsingHTML.logic.parser.ParserHTMLAbstract;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Element;

/**
 * Парсить элемент WeekTime из .html.
 */
public class ParserWeekTime extends ParserHTMLAbstract {
    private static final Logger LOGGER = LogManager.getLogger(ParserWeekTime.class);
    private static final String cssQueryTableTime = ".knock";


    @Override
    public Element parsing(Element element) {
        LOGGER.debug("==== Parsing Element = " + element.nodeName() + " ====");

        Element select = ElementHelper.selectElement(element, cssQueryTableTime, 0);

        Element weekTime = parseDayTime(select);
        LOGGER.debug("====== return " + weekTime);
        return weekTime;
    }

    private Element parseDayTime(Element element) {
        LOGGER.debug("parseDayTime");
        ParserHTMLAbstract parserDayTime = parserFactory.createParserDayTime();
        Element parsing = parserDayTime.parsing(element);
        if (parsing != null) {
            return parsing;
        }
        LOGGER.warn("return empty WeekTime!");
        return ElementFactory.createWeekTime();
    }

}
