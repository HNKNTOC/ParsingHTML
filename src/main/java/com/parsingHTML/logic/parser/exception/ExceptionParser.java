package com.parsingHTML.logic.parser.exception;

import com.parsingHTML.logic.parser.Parser;

/**
 * Исключение происходит при неудачном получении данных при помощи {@link Parser}.
 */
public class ExceptionParser extends Exception {

    public ExceptionParser() {
    }

    public ExceptionParser(String message) {
        super(message);
    }

    public ExceptionParser(String message, Throwable cause) {
        super(message, cause);
    }

    public ExceptionParser(Throwable cause) {
        super(cause);
    }

    public ExceptionParser(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
