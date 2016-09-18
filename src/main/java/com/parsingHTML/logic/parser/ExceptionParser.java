package com.parsingHTML.logic.parser;

/**
 * Исключение происходит при неудачном получении данных при помощи {@link Parser}.
 */
public class ExceptionParser extends Exception {

    private final String parserElementName;

    public ExceptionParser(ParserHTMLAbstract parserHTMLAbstract) {
        this(parserHTMLAbstract, "Failed parsing");
    }

    public ExceptionParser(ParserHTMLAbstract parserHTMLAbstract, String message) {
        super(message);
        this.parserElementName = parserHTMLAbstract.getParsingElementName();
    }

    public String getParserElementName() {
        return parserElementName;
    }
}
