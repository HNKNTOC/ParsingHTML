package com.parsingHTML.logic.parser;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Для тестирования пустышка.
 */
public class ParserMock extends ParserHTMLAbstract {

    private String cssSelect;

    public ParserMock(String cssSelect, String parsingElementName, ParserHTMLAbstract nextParserHTMLAbstract) {
        super(parsingElementName, nextParserHTMLAbstract);
        this.cssSelect = cssSelect;
    }

    @Override
    public Elements selectElement(Element elementHTML) {
        return elementHTML.select(cssSelect);
    }

    public void addException(String message) {
        reportException(message);
    }

    public ParserMock() {
    }

}
