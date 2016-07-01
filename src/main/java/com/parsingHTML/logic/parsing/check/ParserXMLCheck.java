package com.parsingHTML.logic.parsing.check;

import com.parsingHTML.logic.file.FileManager;
import com.parsingHTML.logic.file.FileManagerDefault;
import com.parsingHTML.logic.parsing.html.ParserHTMLAbstract;
import com.parsingHTML.logic.xml.factory.ElementJsoupFactory;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Objects;

/**
 * Класс нужен для упрощения тестирования парсеров.
 */
public class ParserXMLCheck {
    private static final Logger LOGGER = LogManager.getLogger(ParserXMLCheck.class);
    private final static FileManager fileManager = new FileManagerDefault("src\\test\\resources\\html");


    /**
     * Парсит элемент из переданного файла с помощью переданного парсера.
     *
     * @param parserHTMLAbstract Парсер который будет парсить элемент.
     * @param fileName           Имя тестового файла который будем парсить.
     * @return полученный элемент.
     */
    public static Element parsingElement(ParserHTMLAbstract parserHTMLAbstract, String fileName) {
        LOGGER.debug("parsingElement " + parserHTMLAbstract + " fileName = " + fileName);
        return parserHTMLAbstract.parsing(createElementHTML(fileName));
    }

    private static Element createElementHTML(String fileName) {
        try {
            return Jsoup.parse(fileManager.getFile(fileName), null);
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.warn("Failed get file " + fileName, e);
            return ElementJsoupFactory.createElementEmpty();
        }
    }

    /**
     * Проверка имени у элемента.
     *
     * @param element Элемент имя которого нужно проверить.
     * @param tagName Имя которое должно быть у элемента.
     * @return false если имя не совпадает.
     */
    public static boolean checkName(Element element, final String tagName) {
        final String tagNameResults = element.tagName();
        boolean equals = tagName.equals(tagNameResults);
        if (!equals) {
            LOGGER.warn(String.format("Tag Name not equal %s . Tag Name = %s", tagName, tagNameResults));
        }
        return equals;
    }

    /**
     * Проверка количества элементов в элементе.
     *
     * @param element     Элемент в котором нужно проверить.
     * @param tagName     Имя элемента.
     * @param elementSize Количество которое должно быть в elementResults.
     * @return false если в element не совпадает количество element с tagName.
     */
    public static boolean checkElementSize(Element element, final String tagName, final int elementSize) {
        Elements elements = element.select(tagName);
        boolean b = elements.size() == elementSize;
        if (!b) {
            String message = String.format("ElementResults does not contain %d %s. %s size =  %d."
                    , elementSize, tagName, tagName, elements.size());
            LOGGER.warn(message);
        }
        return b;
    }

    /**
     * Проверка значения атрибута.
     *
     * @param elementResults Элемент атрибут которого нужно проверить.
     * @param name           Имя атрибута.
     * @param value          Значение атрибута.
     * @return false если значения атрибута не совпало.
     */
    public static boolean checkElementAttribute(final Element elementResults, final String name, final String value) {
        final String valueResult = elementResults.attr(name);
        boolean equals = Objects.equals(value, valueResult);
        if (!equals) {
            final String message = String.format("Value attribute %s does not equal %s.Value %s = %s",
                    name, value, name, valueResult);
            LOGGER.warn(message);
        }
        return equals;
    }
}
