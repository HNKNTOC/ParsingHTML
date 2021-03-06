package com.parsingHTML.logic.parsing.html;


import com.parsingHTML.logic.parser.ParserHTMLAbstract;
import com.parsingHTML.logic.parser.exception.ExceptionParser;
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
    private static final String PATH_TEST_RES = "src\\test\\resources\\test\\";


    /**
     * Получает элемент из переданного файла с помощью переданного парсера.
     *
     * @param parserHTMLAbstract Парсер который будет парсить элемент.
     * @param fileName           Имя тестового файла который будем парсить.
     * @return полученный элемент.
     */
    public static Element parsingElement(ParserHTMLAbstract parserHTMLAbstract, String fileName) throws ExceptionParser {
        LOGGER.debug("parsingElement " + parserHTMLAbstract + " fileName = " + fileName);

        Element parsing = parserHTMLAbstract.parsing(createElementHTML(fileName));
        LOGGER.debug("parsingElement() return " + parsing);
        return parsing;
    }

    /**
     * Получает элемент из переданного файла.
     *
     * @param fileName Имя тестового файла который будем парсить.
     * @return полученный элемент.
     */
    public static Element parsingElement(String fileName) {
        LOGGER.debug("parsingElement fileName = " + fileName);
        Element elementHTML = createElementHTML(fileName);
        LOGGER.debug("parsingElement() return " + elementHTML);
        return elementHTML;
    }

    private static Element createElementHTML(String fileName) {
        try {
            return Jsoup.parse(new File(PATH_TEST_RES + fileName), null);
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.warn("Failed get file " + fileName, e);
            return null;
        }
    }
}
