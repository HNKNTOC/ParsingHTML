package com.parsingHTML.logic.parsing.tag.grouplesson;

import com.parsingHTML.logic.parsing.tag.ParserHTMLAbstract;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by Nikita on 31.05.2016.
 */
public class ParserGroupLesson extends ParserHTMLAbstract {
    private static final Logger LOGGER = LogManager.getLogger(ParserGroupLesson.class);
    private final static String cssQuery = ".day_in_table";

    @Override
    public Element parsing(Element element) {
        LOGGER.debug("==== Parsing Element = " + element.tagName()+" ====");
        Element groupLesson = XMLFactory.createGroupLesson();
        Elements days = element.select(cssQuery);
        if(days.size()!=6){
            LOGGER.warn("Wrong structure dayTable.size() = "+days.size());
            return groupLesson;
        }
        parsingDayLesson(groupLesson, days);
        LOGGER.debug("====== return ="+groupLesson);
        return groupLesson;
    }

    private void parsingDayLesson(Element groupLesson, Elements days) {
        ParserDayLesson parserDayLesson = new ParserDayLesson();
        for (Element day : days) {
            Element dayLesson = parserDayLesson.parsing(day);
            if(dayLesson!=null){
                groupLesson.appendChild(dayLesson);
            }
        }
    }

}
