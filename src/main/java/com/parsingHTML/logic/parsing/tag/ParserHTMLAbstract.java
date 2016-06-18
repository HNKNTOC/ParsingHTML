package com.parsingHTML.logic.parsing.tag;


import com.parsingHTML.logic.parsing.Parser;
import com.parsingHTML.logic.xml.factory.ElementFactoryJsoup;
import com.parsingHTML.logic.xml.factory.XMLFactory;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Objects;

/**
 * Абстрактный класс для всех парсеров HTML.
 * Парсер XML элемента из HTML элементов.
 */
public abstract class ParserHTMLAbstract implements Parser<Element,Element> {
    private static final Logger LOGGER = LogManager.getLogger(ParserHTMLAbstract.class);
    protected final XMLFactory XMLFactory;
    /**
     * Используется для проверки элемента в isParsing().
     * Имя тега переданного в isParsing().
     */
    private final String tagName;
    /**
     * Используется для проверки элемента в isParsing().
     * Имена тегов находящихся в основном элементе.
     */
    private final String[] tagNameChildren;
    private final static String failIsParsing = "IsParsing fail: ";

    /**
     * Создание ParserHTMLAbstract
     * @param tagName имя тега которое парсер может обработать.
     * @param tagNameChildren имена тегов которые должны быть у детей переданного элемента.
     */
    public ParserHTMLAbstract(String tagName, String[] tagNameChildren) {
        this(new ElementFactoryJsoup(), tagName, tagNameChildren);
    }

    public ParserHTMLAbstract() {
        this(new ElementFactoryJsoup(), "", new String[]{});
    }

    /**
     * Создание ParserHTMLAbstract.
     * @param XMLFactory фабрика создающая XML элементы.
     * @param tagName имя тега которое парсер может обработать.
     * @param tagNameChildren имена тегов которые должны быть у детей переданного элемента.
     */
    public ParserHTMLAbstract(XMLFactory XMLFactory, String tagName, String[] tagNameChildren) {
        this.XMLFactory = XMLFactory;
        this.tagName = tagName;
        this.tagNameChildren = tagNameChildren;
    }

    /**
     * Проверка может ли данный парсер с парсить element.
     *
     * @param element который нужно проверить.
     * @return false если парсер не может с парсить данный элемент.
     */
    @Override
    public boolean isParsing(Element element) {
        if (!Objects.equals(element.tagName(), tagName)) {
            LOGGER.warn(failIsParsing + "TagName mismatch - " + tagName + " TagName = " + element.tagName());
            return false;
        }
        return checkChildren(element.children());
    }

    /**
     * Проверка есть ли все tagNameChildren в elements.
     *
     * @param elements элементы в которых нужно проверить.
     * @return false если хотя бы одного tagName из tagNameChildren нет в elements.
     */
    private boolean checkChildren(Elements elements) {
        for (String tagNameChild : tagNameChildren) {
            if (!findTagName(elements, tagNameChild)) {
                LOGGER.warn(failIsParsing + "Not find tagName in children - " + tagNameChild);
                return false;
            }
        }
        return true;
    }

    /**
     * Поиск элемента с tagName равному tagNameChild.
     *
     * @param elements     элементы в которых нужно искать tagNameChild.
     * @param tagNameChild который нужно проверить.
     * @return false если tagNameChild не был найден.
     */
    private boolean findTagName(Elements elements, String tagNameChild) {
        for (Element child : elements) {
            if (child.tagName().equals(tagNameChild)) {
                return true;
            }
        }
        return false;
    }
}
