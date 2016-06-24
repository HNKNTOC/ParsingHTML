package com.parsingHTML.logic.parsing.tag;

import com.parsingHTML.logic.file.FileManager;
import com.parsingHTML.logic.file.FileManagerDefault;
import com.parsingHTML.logic.xml.factory.ElementFactoryJsoup;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

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
        this.elementResults = parserHTMLAbstract.parsing(createElementHTML(fileName));
    }

    private Element createElementHTML(String fileName) {
        try {
            return Jsoup.parse(fileManager.getFile(fileName), null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ElementFactoryJsoup.createElementEmpty();
    }

    protected void checkName(final String tagName){
        final String tagNameResults = elementResults.tagName();
        assertTrue("Tag Name not equal "+tagName+". Tag Name = "+tagNameResults,
                tagName.equals(tagNameResults));
    }

    /**
     * Метод проверяет правильно ли был спарсен elementResults.
     */
    public abstract void checkElementResults();

}
