package com.parsingHTML.logic.parser;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Для тестирования пустышка.
 */
public class ParserMock extends ParserHTMLAbstract {

    private String cssQuery = "*";

    public ParserMock(String cssQuery, String parsingElementName, ParserHTMLAbstract nextParserHTMLAbstract) {
        super(parsingElementName, nextParserHTMLAbstract);
        this.cssQuery = cssQuery;
    }

    @Override
    public Elements selectElementProcessing(Element element) {
        return element.select(cssQuery);
    }

    public void addException(String message) {
        reportException(message);
    }

    public ParserMock() {
    }

}
