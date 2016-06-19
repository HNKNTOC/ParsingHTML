package com.parsingHTML.logic.parsing.tag.grouplesson;

import com.parsingHTML.logic.parsing.tag.ParserHTMLAbstract;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by Nikita on 08.06.2016.
 */
public class ParserDayLesson extends ParserHTMLAbstract {
    private static final Logger LOGGER = LogManager.getLogger(ParserDayLesson.class);


    @Override
    public Element parsing(Element element) {
        LOGGER.debug("==== Parsing Element = " + element.tagName()+" ====");
        String dayName = element.select(".day").text();
        LOGGER.debug("select dayName = "+dayName);

        Elements select = element.select(".num_para");
        LOGGER.debug("select .num_para return "+select);
        ParserLesson parserLesson = new ParserLesson();

        Element dayLesson = XMLFactory.createDayLesson(dayName);

        for (Element numberLesson : select) {
            Element lesson = parserLesson.parsing(numberLesson);
            if (lesson != null) {
                dayLesson.appendChild(lesson);
            }
        }
        LOGGER.debug("====== return " + dayLesson);
        return dayLesson;
    }
}
