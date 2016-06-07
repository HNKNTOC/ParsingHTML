package com.company.parsingHTML.logic.parsing.tag;


import com.company.parsingHTML.logic.parsing.Parser;
import com.company.parsingHTML.logic.xml.ElementXML;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Element;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Все парсеры расписания дожны записывать спарсенную информацию в Schedule.
 */
public abstract class ParserHTMLAbstract implements Parser<Element,ElementXML> {
    private static final Logger LOGGER = LogManager.getLogger(ParserHTMLAbstract.class);
    /**
     * Список имен TagNode которые обрабатывает данный парсер.
     */
    private final List<String> nameParsingTagList;

    /**
     * Создание ParserHTMLAbstract
     * @param nameParsingTag в моссиве перечеслет список имён TagNode
     *                       которые он может парсить.
     */
    public ParserHTMLAbstract(String[] nameParsingTag) {
        nameParsingTagList = Arrays.asList(nameParsingTag);
        LOGGER.info("Create "+toString());
    }

    /**
     * Проверяет есть ли имя tagName() в nameParsingTagList.
     * @param element Element который хотим проверить.
     * @return true если может с парсить данный Element.
     */
    @Override
    public boolean isParsing(Element element) {
        String nameTag = element.tagName();
        LOGGER.debug("isParsing tagName = "+nameTag);
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
