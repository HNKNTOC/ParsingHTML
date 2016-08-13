package com.parsingHTML.logic.parsing.html.daytime;

import com.parsingHTML.logic.parsing.ElementHelper;
import com.parsingHTML.logic.parsing.html.ParserHTMLAbstract;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Парсить элемент LessonTime из .html.
 */
public class ParserLessonTime extends ParserHTMLAbstract {
    private static final Logger LOGGER = LogManager.getLogger(ParserLessonTime.class);
    private final static String cssQueryNumberLesson = "td.n_para";
    private final static String cssQueryTimeLesson = "td.time";


    @Override
    public Element parsing(Element element) {
        LOGGER.debug("==== Parsing Element = " + element.nodeName() + " ====");

        Elements n_para = ElementHelper.selectElements(element, cssQueryNumberLesson);

        if (!checkNumber(n_para)) return null;

        Elements elementsTime = element.select(cssQueryTimeLesson);
        LOGGER.debug("select elementsTime return "+elementsTime);
        if (!checkElementsTime(elementsTime)) return null;


        String[] time = getTime(elementsTime.get(0));

        Element lessonTime = XMLFactory.createLessonTime(n_para.text().substring(0, 1)
                ,time[0],time[1],time[2],time[3]);
        LOGGER.debug("====== return " + lessonTime);
        return lessonTime;
    }

    private boolean checkElementsTime(Elements elementsTime) {
        if(elementsTime.size()==0){
            LOGGER.warn("Not find time");
            return false;
        }
        if(elementsTime.get(0).text().length()==0){
            LOGGER.warn("time.txt().length() ==0");
            return false;
        }
        return true;
    }

    private boolean checkNumber(Elements n_para) {
        if(n_para.size()==0){
            LOGGER.warn("Not find n_para");
            return false;
        }
        if(n_para.text().length()!=6){
            LOGGER.warn("n_para not form text \"* para\"");
            return false;
        }
        return true;
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
