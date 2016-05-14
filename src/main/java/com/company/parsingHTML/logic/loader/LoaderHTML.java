package com.company.parsingHTML.logic.loader;


import java.io.File;

/**
 * Интерфейс для загрузчика HTML файла.
 */
public interface LoaderHTML {
    /**
     * Загружает HTML и возвращает его.
     * @param url HTML файла.
     * @return загруженный HTML. null если HTML не удалась загрузить.
     */
    File loadHTML(String url);
}
