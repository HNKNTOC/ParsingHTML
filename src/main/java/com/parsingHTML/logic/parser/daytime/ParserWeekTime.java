package com.parsingHTML.logic.parser.daytime;

import com.parsingHTML.logic.element.ElementHelper;
import com.parsingHTML.logic.element.ElementName;
import com.parsingHTML.logic.element.NumeratorName;
import com.parsingHTML.logic.parser.ParserHTMLAbstract;
import com.parsingHTML.logic.parser.exception.ExceptionParser;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Element;

/**
 * Получаеть элемент <week_time>.
 */
public class ParserWeekTime extends ParserHTMLAbstract {
    private static final Logger LOGGER = LogManager.getLogger(ParserWeekTime.class);
    /**
     * cssQuery - для получения NumeratorName.
     */
    private static final String CSS_QUERY_NUMERATOR = "html head script";

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
        return elementFactory.createWeekTime(parsingNumeratorName(element));
    }

    private NumeratorName parsingNumeratorName(Element element) throws ExceptionParser {
        String html = ElementHelper.selectElements(element, CSS_QUERY_NUMERATOR).html();
        if (html.contains("Числитель")) {
            LOGGER.info("This week is NUMERATOR!");
            return NumeratorName.NUMERATOR;
        }
        if (html.contains("Знаменатель")) {
            LOGGER.info("This week is DENOMINATOR!");
            return NumeratorName.DENOMINATOR;
        }
        ExceptionParser e = new ExceptionParser("Failed parsingNumeratorName!!");
        LOGGER.warn(e);
        throw e;
    }
}
