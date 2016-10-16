package com.parsingHTML.logic.parser.grouplesson;

import com.parsingHTML.logic.element.ElementHelper;
import com.parsingHTML.logic.element.ElementName;
import com.parsingHTML.logic.parser.ParserHTMLAbstract;
import com.parsingHTML.logic.parser.exception.ExceptionParser;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Получает элемент GroupLesson из .html.
 */
public class ParserGroupLesson extends ParserHTMLAbstract {
    public final static String cssQueryTableDay = ".tbl_day";

    public ParserGroupLesson(ParserHTMLAbstract nextParser) {
        super(ElementName.GROUP_LESSON.getName(), nextParser);
    }

    /**
     * Из переданного element выбирает элементы по cssQuery который находится в переменной cssQuery.
     * Полученный элементы отправляет parsingDayLesson().
     * @param element Элементы из которого будет получен groupLesson.
     * @return groupLesson.
     */
    @Override
    protected Element processingElement(Element element) throws ExceptionParser {
        return elementFactory.createGroupLesson();

    }

    @Override
    protected Elements selectElementProcessing(Element element) throws ExceptionParser {
        return ElementHelper.selectElements(element, cssQueryTableDay);
    }
}
