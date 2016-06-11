package com.parsingHTML.logic.parsing.tag.daytime;

import com.parsingHTML.logic.parsing.tag.ParserHTMLAbstract;
import com.parsingHTML.logic.xml.ElementXML;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Начальный HTML парсер.
 */
public class ParserWeekTime extends ParserHTMLAbstract {
    private static final Logger LOGGER = LogManager.getLogger(ParserWeekTime.class);
    private static final String cssQueryTableTime = ".knock";


    @Override
    public ElementXML parsing(Element element) {
        LOGGER.info("parsing Tag name = "+element.nodeName());
        ElementXML elementXML;
        elementXML = parseDayTime(element);
        return elementXML;
    }

    private ElementXML parseDayTime(Element element) {
        Elements select = element.select(cssQueryTableTime);
        LOGGER.debug("select "+select.size());
        ElementXML weekTime = new ElementXML("weekTime");
        if(check(select)){
            LOGGER.debug("parseDayTime");
            ParserDayTime parserDayTime = new ParserDayTime();
            weekTime = parserDayTime.parsing(select.get(0));
        }else {
            LOGGER.debug("NOT parseDayTime");
        }
        return weekTime;
    }

    private boolean check(Elements elements) {
        if(elements.size() !=1) {
            LOGGER.debug("Wrong structure elements.size ="+elements.size());
            return false;
        }
        if(!elements.get(0).nodeName().equals("table")) {
            LOGGER.debug("Wrong structure elements nodeName = "+elements.get(0).nodeName());
            return false;
        }
        return true;
    }

}
