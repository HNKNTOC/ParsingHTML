package com.parsingHTML.logic.loader;


import java.io.File;

/**
 * Интерфейс для загрузчика HTML файла.
 */
public interface LoaderHTML {
    //TODO Удолить так как LoaderHTML не входит в обязоности парсера.
    /**
     * Загружает HTML и возвращает его.
     * @param url HTML файла.
     * @return загруженный HTML. null если HTML не удалась загрузить.
     */
    File loadHTML(String url);
}
