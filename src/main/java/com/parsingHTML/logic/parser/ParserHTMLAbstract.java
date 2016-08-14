package com.parsingHTML.logic.parser;


import com.parsingHTML.logic.element.ElementFactory;
import com.parsingHTML.logic.element.ElementJsoupFactory;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Абстрактный класс для всех парсеров HTML.
 * Парсер XML элемента из HTML элементов.
 */
public abstract class ParserHTMLAbstract implements Parser<Element, Element> {
    private static final Logger LOGGER = LogManager.getLogger(ParserHTMLAbstract.class);
    protected final ElementFactory ElementFactory;
    protected final ParserHTMLFactory parserFactory;

    /**
     * Создание ParserHTMLAbstract.
     */
    public ParserHTMLAbstract() {
        this(new ElementJsoupFactory(), new ParserHTMLFactory());
    }

    /**
     * Создание ParserHTMLAbstract.
     *
     * @param ElementFactory Фабрика создающая XML элементы.
     * @param parserHTMLFactory фабрика создающая парсеры.
     */
    public ParserHTMLAbstract(ElementFactory ElementFactory, ParserHTMLFactory parserHTMLFactory) {
        this.ElementFactory = ElementFactory;
        this.parserFactory = parserHTMLFactory;
    }

    /**
     * Парсит каждый элемент из Elements и возвращает полученные элементы.
     *
     * @param elements Elements который нужно спарсить.
     * @return Elements полученные при парсинге.
     */
    public Elements parsingElements(Elements elements) {
        Elements returnElements = new Elements();
        for (Element element : elements) {
            Element parsing = parsing(element);
            if (parsing != null) {
                returnElements.add(parsing);
            }
        }
        LOGGER.debug("parsingElements return " + elements);
        return returnElements;
    }


    /**
     * Проверка может ли данный парсер с парсить element.
     *
     * @param element который нужно проверить.
     * @return false если парсер не может парсить данный элемент.
     */
    @Override
    //TODO DELETE
    public boolean isParsing(Element element) {
        return false;
    }
}
