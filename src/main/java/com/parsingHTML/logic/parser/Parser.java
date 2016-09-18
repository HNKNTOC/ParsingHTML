package com.parsingHTML.logic.parser;


import com.parsingHTML.logic.parser.exception.ExceptionParser;

import java.util.List;

/**
 * Интерфейс для парсеров.
 * @param <T> объект из которого будем парсить
 *           информацию и записывать её.
 * @param <S> объект в который будем записывать
 *           полученную информацию во время парсинга.
 */
public interface Parser<T,S> {

    /**
     * Возвращает ошибки которые произошли вовремя получения данных.
     *
     * @return Iterator с ExceptionParser которые произошли вовремя получения данных.
     */
    List<ExceptionParser> getExceptions();

    /**
     * Удоляет все ошибки.
     */
    void clearExceptions();

    /**
     * Было ли получение информации успешно.
     *
     * @return false если вовремя выполнения произошла ошибка.
     * true если получение данных было успешным.
     */
    boolean isSuccessful();

    /**
     * Может ли данный Parser обработать t.
     * @param t объект который мы хотим проверить.
     * @return true если Parser может обработать t.
     */
    boolean isParsing(T t);

    /**
     * Парсинг t.
     * Перед парсингом лучше проверить t с помощью isParsing().
     * @param t объект который нужно парсить.
     * @return объект в который записывается полученная информация.
     */
    S parsing(T t);
}