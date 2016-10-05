package com.parsingHTML.logic.parser.grouplesson;

import com.parsingHTML.logic.element.ElementName;
import com.parsingHTML.logic.parser.ParserHTMLAbstract;
import com.parsingHTML.logic.parser.ParserHTMLFactory;
import com.parsingHTML.logic.parser.ParsirHelper;
import com.parsingHTML.logic.parser.exception.ExceptionParser;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Получает элемент GroupLesson из .html.
 */
public class ParserGroupLesson extends ParserHTMLAbstract {
    private static final Logger LOGGER = LogManager.getLogger(ParserGroupLesson.class);
    public final static String cssQueryTableDay = ".tbl_day";

    public ParserGroupLesson(ParserHTMLAbstract nextParser) {
        super(ElementName.GROUP_LESSON.getName(), nextParser);
    }

    /**
     * Из переданного element выбирает элементы по cssQuery который находится в переменной cssQuery.
     * Полученный элементы отправляет parsingDayLesson().
     * @param elements Элементы из которого будет получен groupLesson.
     * @return groupLesson.
     */
    @Override
    protected Element processingElement(Elements elements) throws ExceptionParser {
        Element groupLesson = elementFactory.createGroupLesson();
        Elements days = ParsirHelper.selectElements(elements, cssQueryTableDay);
        groupLesson.insertChildren(0, parsingDayLesson(days));
        return groupLesson;

    }

    @Override
    protected Elements selectElementProcessing(Element element) throws ExceptionParser {
        return ParsirHelper.selectElements(element, cssQueryTableDay);
    }

    /**
     * Полученный дни парсит через ParserDayLesson.
     * @param days дни который нужно спарсить.
     */
    private Elements parsingDayLesson(Elements days) throws ExceptionParser {
        ParserHTMLAbstract parserDayLesson = ParserHTMLFactory.createParserDayLesson();
        return parserDayLesson.parsingElements(days);
    }

}
