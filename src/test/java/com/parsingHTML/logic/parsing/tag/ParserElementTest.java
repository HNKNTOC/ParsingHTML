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

import static org.junit.Assert.assertTrue;

/**
 * Класс нужен для упрошения тестирования наследников ParserHTMLAbstract.
 * Использование:
 * 1) Передать в конструктор тестируемы парсер;
 * 2) Передать в конструктор имя тестового файла который будем парсить;
 * 3) Реалезовать метод check();
 */
public abstract class ParserElementTest {
    private static final Logger LOGGER = LogManager.getLogger(ParserElementTest.class);
    private final static FileManager fileManager = new FileManagerDefault("src\\test\\resources\\html");
    /**
     * Елемент который был получен вовремя парсинга.
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

    protected void checkName(final String tagName){
        final String tagNameResults = elementResults.tagName();
        assertTrue("Tag Name not equal "+tagName+". Tag Name = "+tagNameResults,
                tagName.equals(tagNameResults));
    }

    /**
     * Проверка количества элементов в elementResults.
     * @param tagName Имя элемента.
     * @param elementSize Колличество которое должно быть в elementResults.
     */
    public void checkElementSize(final String tagName, final int elementSize){
        Elements elements = elementResults.select(tagName);
        String message = String.format("ElementResults does not contain %d %s. %s size =  %d."
                ,elementSize,tagName,tagName,elements.size());
        TestCase.assertTrue(message, elements.size() == elementSize);
    }

    /**
     * Метод проверяет правильно ли был спарсен elementResults.
     */
    public abstract void checkElementResults();
}
