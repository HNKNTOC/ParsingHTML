package com.parsingHTML.logic.parser.daytime;

import com.parsingHTML.logic.element.ElementName;
import com.parsingHTML.logic.parser.ParserHTMLAbstract;
import com.parsingHTML.logic.parser.exception.ExceptionParser;
import org.jsoup.nodes.Element;

/**
 * Получаеть элемент <week_time>.
 */
public class ParserWeekTime extends ParserHTMLAbstract {

    public ParserWeekTime(ParserHTMLAbstract nextParserHTMLAbstract) {
        super(ElementName.WEEK_TIME.getName(), nextParserHTMLAbstract);
    }

    /**
     * Создаёт <week_time> и получает значение для параметров.
     *
     * @param element Element из коророго будет получен
     * @return Element <week_time>.
     */
    @Override
    public Element processingElement(Element element) throws ExceptionParser {
        return elementFactory.createWeekTime();
    }
}
