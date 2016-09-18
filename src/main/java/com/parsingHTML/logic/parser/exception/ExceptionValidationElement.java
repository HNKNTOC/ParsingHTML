package com.parsingHTML.logic.parser.exception;

/**
 * Ошибка возникает при проверки {@link org.jsoup.nodes.Element}.
 */
public class ExceptionValidationElement extends ExceptionParser {
    public ExceptionValidationElement() {
    }

    public ExceptionValidationElement(String message) {
        super(message);
    }

    public ExceptionValidationElement(String message, Throwable cause) {
        super(message, cause);
    }

    public ExceptionValidationElement(Throwable cause) {
        super(cause);
    }

    public ExceptionValidationElement(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
