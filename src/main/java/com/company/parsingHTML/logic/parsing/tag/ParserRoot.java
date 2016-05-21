package com.company.parsingHTML.logic.parsing.tag;

import com.company.parsingHTML.logic.schedule.Schedule;
import com.company.parsingHTML.logic.schedule.WeekTime;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.htmlcleaner.TagNode;

/**
 * Начальный HTML парсер.
 */
public class ParserRoot extends ParserTagAbstract {
    private static final Logger LOGGER = LogManager.getLogger(ParserRoot.class);

    public ParserRoot() {
        super(new String[]{"html"});
    }

    @Override
    public void parsing(TagNode tagNode, Schedule schedule) {
        System.out.println(tagNode.toString());
        TagNode[] scripts = tagNode.getElementsByAttValue("class","knock",true,true);
        schedule.setWeekTime(createWeekLesson(false));

        getObservableParing().notifyObserver(scripts[0],schedule);
    }

    private WeekTime createWeekLesson(boolean numerator) {
        return new WeekTime(numerator);
    }

}
