package com.parsingHTML.logic.exception;

import com.parsingHTML.logic.parser.ExceptionParser;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс для работы с списком ошибок.
 */
public class ExceptionList<T extends Exception> {
    private static final Logger LOGGER = LogManager.getLogger(ExceptionList.class);
    /**
     * Лист с ошибками.
     */
    private List<T> exceptions = new ArrayList<>();

    /**
     * Возвращает количество ошибок.
     */
    public int count() {
        return exceptions.size();
    }

    public List<T> getAll() {
        return exceptions;
    }

    /**
     * Сообщить о произошедшей ошибки.
     * Добавляет {@link ExceptionParser} в listExceptionParser.
     *
     * @param exception {@link ExceptionParser} который произошел вовремя получения данных.
     */
    public void add(T exception) {
        LOGGER.warn("reportExceptionParser() ", exception);
        exceptions.add(exception);
    }

    /**
     * Очистить список ExceptionParser.
     */
    public void clear() {
        LOGGER.debug("clearExceptionParser()");
        exceptions.clear();
    }

    /**
     * Есть ли ошибки.
     */
    public boolean isEmpty() {
        return exceptions.isEmpty();
    }
}
