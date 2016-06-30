package com.parsingHTML.logic.parsing.html.daytime;

import com.parsingHTML.logic.parsing.html.ParserHTMLAbstract;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Парсить элемент WeekTime из html.
 */
public class ParserWeekTime extends ParserHTMLAbstract {
    private static final Logger LOGGER = LogManager.getLogger(ParserWeekTime.class);
    private static final String cssQueryTableTime = ".knock";


    @Override
    public Element parsing(Element element) {
        LOGGER.debug("==== Parsing Element = " + element.tagName()+" ====");

        Elements select = selectElements(element, cssQueryTableTime);

        Element weekTime = parseDayTime(select);
        LOGGER.debug("====== return " + weekTime);
        return weekTime;
    }

    private Element parseDayTime(Elements select) {
        if(check(select)){
            LOGGER.debug("parseDayTime");
            ParserHTMLAbstract parserDayTime = parserFactory.createParserDayTime();
            Element parsing = parserDayTime.parsing(select.get(0));
            if (parsing != null) {
                return parsing;
            }
        }
        return XMLFactory.createWeekTime();
    }

    private boolean check(Elements elements) {
        if(elements.size() !=1) {
            LOGGER.warn("Wrong structure elements.size ="+elements.size());
            return false;
        }
        return true;
    }

}
