package com.company.parsingHTML.logic.parsing.tag.daytime;

import com.company.parsingHTML.logic.parsing.tag.ParserHTMLAbstract;
import com.company.parsingHTML.logic.xml.ElementXML;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Парсит lessonTime из tr.
 */
public class ParserLessonTime extends ParserHTMLAbstract {
    private static final Logger LOGGER = LogManager.getLogger(ParserLessonTime.class);
    private final static String cssQueryNumberLesson = "td.n_para";
    private final static String cssQueryTimeLesson = "td.time";


    @Override
    public ElementXML parsing(Element element) {
        LOGGER.info("======parsing Tag name = "+element.nodeName()+"======");
        ElementXML lessonTime = new ElementXML("lessonTime");

        if(!check(element.children())){
            LOGGER.debug("Failed parsing element!");
            return null;
        }

        Elements n_para = element.select(cssQueryNumberLesson);
        LOGGER.debug("select n_para return "+n_para);
        if(n_para.size()==0){
            LOGGER.warn("Not find n_para");
            return null;
        }
        if(n_para.text().length()!=6){
            LOGGER.warn("n_para.txt().length() !=6");
            return null;
        }

        Elements elementsTime = element.select(cssQueryTimeLesson);
        LOGGER.debug("select elementsTime return "+elementsTime);
        if(elementsTime.size()==0){
            LOGGER.warn("Not find time");
            return null;
        }
        if(elementsTime.get(0).text().length()==0){
            LOGGER.warn("time.txt().length() ==0");
            return null;
        }


        String[] time = getTime(elementsTime.get(0));

        lessonTime.addAttribute("number",n_para.text().substring(0, 1));
        lessonTime.addAttribute("start1",time[0]);
        lessonTime.addAttribute("end1",time[1]);
        lessonTime.addAttribute("start2",time[2]);
        lessonTime.addAttribute("end2",time[3]);
        LOGGER.debug("====== return ="+lessonTime);
        return lessonTime;
    }

    private String[] getTime(Element child) {
        return child.text().replace("–"," ").split(" ");
    }

    private boolean check(Elements elements) {
        if(elements.size()==0) {
            LOGGER.debug("Wrong structure elements elements.size = "+elements.size());
            return false;
        }
        return true;
    }

}
