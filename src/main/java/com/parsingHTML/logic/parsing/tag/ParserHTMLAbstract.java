package com.parsingHTML.logic.parsing.tag;


import com.parsingHTML.logic.factory.ElementJsoupFactory;
import com.parsingHTML.logic.parsing.Parser;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Element;

/**
 * Все парсеры расписания дожны записывать спарсенную информацию в Schedule.
 */
public abstract class ParserHTMLAbstract implements Parser<Element,Element> {
    private static final Logger LOGGER = LogManager.getLogger(ParserHTMLAbstract.class);
    protected final ElementJsoupFactory elementFactory;

    /**
     * Создание ParserHTMLAbstract
     */
    public ParserHTMLAbstract() {
        elementFactory = new ElementJsoupFactory();
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
