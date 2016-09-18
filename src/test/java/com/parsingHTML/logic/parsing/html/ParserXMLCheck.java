package com.parsingHTML.logic.parsing.html;


import com.parsingHTML.logic.parser.ParserHTMLAbstract;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.IOException;

/**
 * Класс нужен для упрощения тестирования ParserHTMLAbstract.
 */
public class ParserXMLCheck {
    private static final Logger LOGGER = LogManager.getLogger(ParserXMLCheck.class);


    /**
     * Получает элемент из переданного файла с помощью переданного парсера.
     *
     * @param parserHTMLAbstract Парсер который будет парсить элемент.
     * @param fileName           Имя тестового файла который будем парсить.
     * @return полученный элемент.
     */
    public static Element parsingElement(ParserHTMLAbstract parserHTMLAbstract, String fileName) {
        LOGGER.debug("parsingElement " + parserHTMLAbstract + " fileName = " + fileName);
        return parserHTMLAbstract.parsing(createElementHTML(fileName));
    }

    /**
     * Получает элемент из переданного файла.
     *
     * @param fileName Имя тестового файла который будем парсить.
     * @return полученный элемент.
     */
    public static Element parsingElement(String fileName) {
        LOGGER.debug("parsingElement fileName = " + fileName);
        return createElementHTML(fileName);
    }

    private static Element createElementHTML(String fileName) {
        try {
            return Jsoup.parse(new File("src\\test\\resources\\test\\" + fileName), null);
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.warn("Failed get file " + fileName, e);
            return null;
        }
    }
}
