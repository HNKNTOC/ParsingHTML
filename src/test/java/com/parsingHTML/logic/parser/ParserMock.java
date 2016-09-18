package com.parsingHTML.logic.parser;

/**
 * Для тестирования пустышка.
 */
public class ParserMock extends ParserHTMLAbstract {

    public ParserMock(String cssSelect, String parsingElementName, ParserHTMLAbstract nextParserHTMLAbstract) {
        super(cssSelect, parsingElementName, nextParserHTMLAbstract);
    }

    public void addException(String message) {
        reportException(message);
    }

    public ParserMock() {
    }

}
