package com.parsingHTML.logic.parsing.tag;


import com.parsingHTML.logic.parsing.Parser;
import com.parsingHTML.logic.xml.ElementXML;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Element;

/**
 * Все парсеры расписания дожны записывать спарсенную информацию в Schedule.
 */
public abstract class ParserHTMLAbstract implements Parser<Element,ElementXML> {
    private static final Logger LOGGER = LogManager.getLogger(ParserHTMLAbstract.class);


    /**
     * Создание ParserHTMLAbstract
     */
    public ParserHTMLAbstract() {
        LOGGER.info("Create "+toString());
    }

    /**
     * Проверяет есть ли имя tagName() в nameParsingTagList.
     * @param element Element который хотим проверить.
     * @return true если может с парсить данный Element.
     */
    @Override
    public boolean isParsing(Element element) {
        return true;
    }
}
