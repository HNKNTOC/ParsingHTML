package com.company.parsingHTML.logic.parsing.tag.grouplesson;

import com.company.parsingHTML.logic.parsing.tag.ParserTagAbstract;
import com.company.parsingHTML.logic.schedule.Schedule;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.htmlcleaner.TagNode;

/**
 * Created by Nikita on 31.05.2016.
 */
public class ParserGroupLesson extends ParserTagAbstract {
    private static final Logger LOGGER = LogManager.getLogger(ParserGroupLesson.class);

    /**
     * Создание ParserTagAbstract
     *
     */
    public ParserGroupLesson() {
        super(new String[]{"table"});
    }

    @Override
    public void parsing(TagNode tagNode, Schedule schedule) {
        LOGGER.debug("parsing = "+tagNode.getName()+" id = "+tagNode.getAttributeByName("id"));
    }
}
