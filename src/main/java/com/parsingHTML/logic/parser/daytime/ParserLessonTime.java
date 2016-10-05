package com.parsingHTML.logic.parser.daytime;

import com.parsingHTML.logic.element.AttributeName;
import com.parsingHTML.logic.element.ElementName;
import com.parsingHTML.logic.parser.ParserHTMLAbstract;
import com.parsingHTML.logic.parser.ParsirHelper;
import com.parsingHTML.logic.parser.exception.ExceptionParser;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Получает элемент <lesson_time>.
 */
public class ParserLessonTime extends ParserHTMLAbstract {
    private static final Logger LOGGER = LogManager.getLogger(ParserLessonTime.class);
    /**
     * CSS Query для элемента с временем.
     */
    private static final String CSS_QUERY_TIME_LESSON = "td";

    public ParserLessonTime(ParserHTMLAbstract nextParser) {
        super(ElementName.LESSON_TIME.getName(), nextParser, true);
    }


    /**
     * Создаёт <lesson_time> и получает значение для параметров.
     *
     * @param element Element из которого будет получен
     * @return Element <lesson_time>.
     */
    @Override
    protected Element processingElement(final Element element) throws ExceptionParser {
        String[] time = getTime(element);
        //TODO ADD ElementHELPER get Attr
        String attr = element.attr(AttributeName.NUMBER.getName());
        return elementFactory.createLessonTime(attr, time[0], time[1], time[2], time[3]);
    }

    /**
     * Выбирает из переданного Element все Elements по {@link ParserLessonTime#CSS_QUERY_TIME_LESSON}.
     * Если не удалось выбрать не одного элемента возвращает пустой Elements.
     *
     * @param element Element из которого нужно выбрать Elements для {@link ParserHTMLAbstract#parsing(Element)}.
     * @return Пустой если не удалось получить Elements для {@link ParserHTMLAbstract#parsing(Element)}.
     */
    @Override
    protected Elements selectElementProcessing(Element element) throws ExceptionParser {
        if (element.children().size() == 0) {
            LOGGER.debug("SelectElementProcessing not find " + CSS_QUERY_TIME_LESSON);
            return new Elements();
        }

        Elements elements = ParsirHelper.selectElements(element, CSS_QUERY_TIME_LESSON);
        for (int i = 0; i < elements.size(); i++) {
            elements.get(i).attr(AttributeName.NUMBER.getName(), String.valueOf(i + 1));
        }
        return elements;
    }

    private String[] getTime(Element child) {
        String[] split = child.text().replace("–", " ").split(" ");
        if (split.length < 4) {
            //TODO ADD Exception
            LOGGER.warn("Failed getTime split.length = "+split.length);
            return new String[]{"","","",""};
        }
        return split;
    }

}
