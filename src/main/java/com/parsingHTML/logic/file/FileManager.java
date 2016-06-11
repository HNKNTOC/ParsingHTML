package com.parsingHTML.logic.file;

import java.io.File;

/**
 * Интерфейс предоставляет функционал для создания получения и удаления файлов из директории.
 */
public interface FileManager {

    /**
     * Установить директорию для работы с ней.
     * @param pathDirectory путь до директории.
     */
    boolean setDirectory(String pathDirectory);

    /**
     * Создаёт директорию.
     * @param fileDirectory директория которую создать.
     * @return false если fileDirectory не удалось создать директорию.
     */
    boolean createDirectory(File fileDirectory);

    /**
     * Получить директорию с которой в данный момент ведётся работа.
     * @return путь до директории.
     */
    String getDirectory();

    /**
     * Создаёт файл в директории.
     * @param name имя файла.
     * @return true если файл был создан. false если создать не получилось.
     */
    boolean createFile(String name);

    /**
     * Получить файл из директории по имени.
     * @param name имя файла.
     * @return Найденный файл, null если файл был не найден.
     */
    File getFile(String name);

    /**
     * Возвращает все файлы из директории.
     * @return массив с файлами из директории.
     */
    File[] getAllFile();

    /**
     * Удаляет файл из директории.
     * @param name имя файла.
     * @return true если файл удален.
     */
    boolean deleteFile(String name);

    /**
     * Удаляет все файлы из директории.
     * @return true если файлы удалены.
     */
    boolean deleteAllFile();
}
