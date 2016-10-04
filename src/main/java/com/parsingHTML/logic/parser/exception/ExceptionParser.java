package com.parsingHTML.logic.parser.exception;

import com.parsingHTML.logic.parser.Parser;
import org.jsoup.nodes.Element;

/**
 * Исключение происходит при неудачном получении данных при помощи {@link Parser}.
 */
public class ExceptionParser extends Exception {

    /**
     * Имя Element который не удолось спарсить.
     */
    private final String elementName;
    /**
     * Element который не удолось спарсить.
     */
    private final Element element;

    public ExceptionParser(String elementName, Element element) {
        this.elementName = elementName;
        this.element = element;
    }

    public ExceptionParser(String message, String elementName, Element element) {
        super(message);
        this.elementName = elementName;
        this.element = element;
    }
}
