package com.parsingHTML.logic.parsing.tag;


import com.parsingHTML.logic.parsing.Parser;
import com.parsingHTML.logic.xml.factory.ElementFactoryJsoup;
import com.parsingHTML.logic.xml.factory.XMLFactory;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Element;

/**
 * Абстрактный класс для всех парсеров HTML.
 * Парсер XML элемента из HTML элементов.
 */
public abstract class ParserHTMLAbstract implements Parser<Element,Element> {
    private static final Logger LOGGER = LogManager.getLogger(ParserHTMLAbstract.class);
    protected final XMLFactory XMLFactory;

    /**
     * Создание ParserHTMLAbstract
     */
    public ParserHTMLAbstract() {
        this(new ElementFactoryJsoup());
    }

    /**
     * Создание ParserHTMLAbstract.
     * @param XMLFactory фабрика создаюшая XML элементы.
     */
    public ParserHTMLAbstract(XMLFactory XMLFactory) {
        this.XMLFactory = XMLFactory;
    }

    @Override
    public boolean isParsing(Element element) {
        return true;
    }
}
