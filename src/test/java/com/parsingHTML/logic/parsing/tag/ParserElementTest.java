package com.parsingHTML.logic.parsing.tag;

import com.parsingHTML.logic.file.FileManager;
import com.parsingHTML.logic.file.FileManagerDefault;
import com.parsingHTML.logic.xml.factory.ElementJsoupFactory;
import junit.framework.TestCase;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Класс нужен для упрощения тестирования наследников ParserHTMLAbstract.
 * Использование:
 * 1) Передать в конструктор тестируемы парсер;
 * 2) Передать в конструктор имя тестового файла который будем парсить;
 * 3) Реализовать метод check();
 */
public abstract class ParserElementTest {
    private static final Logger LOGGER = LogManager.getLogger(ParserElementTest.class);
    private final static FileManager fileManager = new FileManagerDefault("src\\test\\resources\\html");
    /**
     * Элемент который был получен вовремя парсинга.
     */
    protected final Element elementResults;

    /**
     *  @param parserHTMLAbstract Парсер который нужно проверить.
     * @param fileName Имя тестового файла который будем парсить.
     */
    public ParserElementTest(ParserHTMLAbstract parserHTMLAbstract, String fileName) {
        LOGGER.debug("ParserElementTest "+parserHTMLAbstract+" fileName = "+fileName);
        this.elementResults = parserHTMLAbstract.parsing(createElementHTML(fileName));
    }

    private Element createElementHTML(String fileName) {
        try {
            return Jsoup.parse(fileManager.getFile(fileName), null);
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.warn("Failed get file "+fileName,e);
            return ElementJsoupFactory.createElementEmpty();
        }
    }

    /**
     * Проверка имени у элемента.
     * @param element Элемент имя которого нужно проверить.
     * @param tagName Имя которое должно быть у элемента.
     */
    protected void checkName(Element element,final String tagName){
        final String tagNameResults = element.tagName();
        assertTrue("Tag Name not equal "+tagName+". Tag Name = "+tagNameResults,
                tagName.equals(tagNameResults));
    }

    /**
     * Проверка количества элементов в элементе.
     * @param element Элемент в котором нужно проверить.
     * @param tagName Имя элемента.
     * @param elementSize Количество которое должно быть в elementResults.
     */
    public void checkElementSize(Element element,final String tagName, final int elementSize){
        Elements elements = element.select(tagName);
        String message = String.format("ElementResults does not contain %d %s. %s size =  %d."
                ,elementSize,tagName,tagName,elements.size());
        TestCase.assertTrue(message, elements.size() == elementSize);
    }

    /**
     * Проверка значения атрибута.
     *
     * @param elementResults Элемент атрибут которого нужно проверить.
     * @param name           Имя атрибута.
     * @param value          Значение атрибута.
     */
    protected void checkElementAttribute(final Element elementResults, final String name, final String value) {
        final String valueResult = elementResults.attr(name);
        final String message = String.format("Value attribute %s does not equal %s.Value %s equal %s",
                name, value, name, valueResult);
        assertEquals(message, value, valueResult);
    }

    /**
     * Метод проверяет правильно ли был спарсен elementResults.
     */
    public abstract void checkElementResults();
}
