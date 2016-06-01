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
        LOGGER.info("parsing Tag name = "+tagNode.getName());
        schedule.setWeekTime(createWeekLesson(false));

        TagNode[] knock = tagNode.getElementsByAttValue("class","knock",true,true);
        TagNode[] table = tagNode.getElementsByAttValue("id","ctl00_head_TblAll",true,true);

        if(table.length != 0) {
            getObservableParing().notifyObserver(table[0], schedule);
        }

        if(knock.length != 0) {
            getObservableParing().notifyObserver(knock[0], schedule);
        }
    }

    private WeekTime createWeekLesson(boolean numerator) {
        return new WeekTime(numerator);
    }

}
