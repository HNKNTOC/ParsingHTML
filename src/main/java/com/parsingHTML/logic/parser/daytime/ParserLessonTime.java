package com.parsingHTML.logic.parser.daytime;

import com.parsingHTML.logic.element.AttributeName;
import com.parsingHTML.logic.element.ElementHelper;
import com.parsingHTML.logic.element.ElementName;
import com.parsingHTML.logic.parser.ParserHTMLAbstract;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Получаеть элемент <lesson_time>.
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
     * @param element Element из коророго будет получен
     * @return Element <lesson_time>.
     */
    @Override
    protected Element processingElement(final Element element) {
        Element tr = ElementHelper.selectElement(element, CSS_QUERY_TIME_LESSON, 0);
        String[] time = getTime(tr);
        //TODO ADD ElementHELPER get Attr
        String attr = tr.attr(AttributeName.NUMBER.getName());
        return elementFactory.createLessonTime(attr, time[0], time[1], time[2], time[3]);
    }

    @Override
    public Elements selectElementProcessing(Element elementHTML) {
        Elements elements = ElementHelper.selectElements(elementHTML, "td");
        for (int i = 0; i < elements.size(); i++) {
            elements.get(i).attr(AttributeName.NUMBER.getName(), String.valueOf(i + 1));
        }
        return elements;
    }

    private String[] getTime(Element child) {
        String[] split = child.text().replace("–", " ").split(" ");
        if(split.length<4){
            LOGGER.warn("Failed getTime split.length = "+split.length);
            return new String[]{"","","",""};
        }
        return split;
    }

}
