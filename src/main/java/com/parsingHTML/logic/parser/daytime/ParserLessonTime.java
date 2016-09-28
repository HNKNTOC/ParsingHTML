package com.parsingHTML.logic.parser.daytime;

import com.parsingHTML.logic.element.ElementHelper;
import com.parsingHTML.logic.element.ElementName;
import com.parsingHTML.logic.parser.ParserHTMLAbstract;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Получаеть элемент LessonTime из .html.
 */
public class ParserLessonTime extends ParserHTMLAbstract {
    private static final Logger LOGGER = LogManager.getLogger(ParserLessonTime.class);
    private final static String cssQueryNumberLesson = "td.n_para";
    private final static String cssQueryTimeLesson = "td.time";

    private int numberColumnTime;

    public void setNumberColumnTime(int numberColumnTime) {
        this.numberColumnTime = numberColumnTime;
    }

    public ParserLessonTime(ParserHTMLAbstract nextParser) {
        super(ElementName.LESSON_TIME.getName(), nextParser, true);
    }


    @Override
    protected Element processingElement(Elements elements) {

        final Elements n_para = ElementHelper.selectElements(elements, cssQueryNumberLesson);

        if (!ElementHelper.checkNotElementSize(n_para, 0)) return null;

        final Element elementsTime = ElementHelper.selectElement(elements, cssQueryTimeLesson, numberColumnTime);
        LOGGER.debug("executeSelect elementsTime return " + elementsTime);

        String[] time = getTime(elementsTime);

        return elementFactory.createLessonTime(n_para.text().substring(0, 1)
                ,time[0],time[1],time[2],time[3]);
    }

    @Override
    public Elements selectElement(Element elementHTML) {
        return super.selectElement(elementHTML);
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
