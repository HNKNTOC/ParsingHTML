package com.company.parsingHTML.logic.parsing.tag;

import com.company.parsingHTML.logic.parsing.ParserAbstract;
import com.company.parsingHTML.logic.schedule.Schedule;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.htmlcleaner.TagNode;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Все парсеры расписания дожны записывать спарсенную информацию в Schedule.
 */
public abstract class ParserTagAbstract extends ParserAbstract<Schedule,TagNode> {
    private static final Logger LOGGER = LogManager.getLogger(ParserTagAbstract.class);
    /**
     * Список имен TagNode которые обрабатывает данный парсер.
     */
    private final List<String> nameParsingTagList;

    /**
     * Создание ParserTagAbstract
     * @param nameParsingTag в моссиве перечеслет список имён TagNode
     *                       которые он может парсить.
     */
    public ParserTagAbstract(String[] nameParsingTag) {
        nameParsingTagList = Arrays.asList(nameParsingTag);
        LOGGER.info("Create "+toString());
    }

    /**
     * Проверяет есть ли имя tagNodeName в nameParsingTagList.
     * @param tagNode TagNode который хотим проверить.
     * @return true если может с парсить данный TagNode.
     */
    @Override
    public boolean isParsing(TagNode tagNode) {
        String nameTag = tagNode.getName();
        LOGGER.debug("isParsing nameTag = "+nameTag);
        for (String s : nameParsingTagList) {
            if(Objects.equals(nameTag, s)){
                LOGGER.debug(nameTag+" equals "+s);
                return true;
            }else {
                LOGGER.debug(nameTag+" not equals "+s);
            }
        }
        return false;
    }
}
