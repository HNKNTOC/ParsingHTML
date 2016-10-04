package com.parsingHTML.logic.parser;


import com.parsingHTML.logic.parser.exception.ExceptionParser;

/**
 * Интерфейс для парсеров.
 * @param <T> объект из которого будем парсить
 *           информацию и записывать её.
 * @param <S> объект в который будем записывать
 *           полученную информацию во время парсинга.
 */
public interface Parser<T, S> {

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
    S parsing(T t) throws ExceptionParser;
}