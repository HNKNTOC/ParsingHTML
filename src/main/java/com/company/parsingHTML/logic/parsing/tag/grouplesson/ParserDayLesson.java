package com.company.parsingHTML.logic.parsing.tag.grouplesson;

import com.company.parsingHTML.logic.parsing.tag.ParserHTMLAbstract;
import com.company.parsingHTML.logic.xml.ElementXML;
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
    public ElementXML parsing(Element element) {
        LOGGER.info("parsing Tag name = " + element.nodeName());
        ElementXML dayLesson = new ElementXML("dayLesson");
        Elements dayName = element.select(".day");
        dayLesson.addAttribute("dayName",dayName.text());
        Elements select = element.select(".num_para");
        ParserLesson parserLesson = new ParserLesson();
        for (Element numberLesson : select) {
            ElementXML lesson = parserLesson.parsing(numberLesson);
            if (lesson != null) {
                dayLesson.addChildren(lesson);
            }
        }
        return dayLesson;
    }
}
