package com.parsingHTML.logic.parser;


import com.parsingHTML.logic.element.ElementFactory;
import com.parsingHTML.logic.element.ElementJsoupBuilder;
import com.parsingHTML.logic.element.ElementJsoupFactory;
import com.parsingHTML.logic.parser.exception.ExceptionParser;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Абстрактный класс для всех парсеров HTML.
 * Получает {@link Element} c XML данные из {@link Element} с HTML.
 */
public abstract class ParserHTMLAbstract implements Parser<Element, Element> {

    private static final Logger LOGGER = LogManager.getLogger(ParserHTMLAbstract.class);
    /**
     * Фабрика создает Element для записи в них полученной информации.
     */
    protected final ElementFactory elementFactory;
    /**
     * Следующий парсер.
     */
    protected final ParserHTMLAbstract nextParser;
    /**
     * Имя элемента который получит данный ParserHTMLAbstract.
     */
    private final String parsingElementName;
    /**
     * Возвращает ли данный парсер множество элементов.
     */
    private boolean isParseElements = false;


    /**
     * Создание ParserHTMLAbstract.
     *
     * @param parsingElementName Имя получаемого элемента.
     */
    public ParserHTMLAbstract(String parsingElementName, ParserHTMLAbstract nextParser) {
        this(new ElementJsoupFactory(), nextParser, parsingElementName, false);
    }

    /**
     * Создание ParserHTMLAbstract.
     *
     * @param parsingElementName Имя получаемого элемента.
     */
    public ParserHTMLAbstract(String parsingElementName, ParserHTMLAbstract nextParser, boolean isParseElements) {
        this(new ElementJsoupFactory(), nextParser, parsingElementName, isParseElements);
    }

    /**
     * Создание ParserHTMLAbstract.
     *
     * @param ElementFactory     Фабрика создающая XML элементы.
     * @param nextParser         Следующий парсер.
     * @param parsingElementName Имя получаемого элемента.
     */
    public ParserHTMLAbstract(ElementFactory ElementFactory, ParserHTMLAbstract nextParser, String parsingElementName, boolean isParseElements) {
        this.elementFactory = ElementFactory;
        this.nextParser = nextParser;
        this.parsingElementName = parsingElementName;
        this.isParseElements = isParseElements;

    }

    public boolean isParseElements() {
        return isParseElements;
    }

    public ParserHTMLAbstract getNextParser() {
        return nextParser;
    }

    public String getParsingElementName() {
        return parsingElementName;
    }

    /**
     * Проверка может ли данный парсер с парсить element.
     *
     * @param element который нужно проверить.
     * @return false если парсер не может парсить данный элемент.
     */
    @Override
    public boolean isParsing(Element element) {
        return false;
    }

    /**
     * Получает каждый элемент из Elements и возвращает полученные элементы.
     *
     * @param elements Elements который нужно спарсить.
     * @return Elements полученные при парсинге.
     */
    public Elements parsingElements(Elements elements) throws ExceptionParser {
        LOGGER.debug("Parsing elements.");
        logElement("Parsing elements = " + elements);
        Elements returnElements = new Elements();
        for (Element element : elements) {
            Element parsing = parsing(element);
            if (parsing != null) {
                returnElements.add(parsing);
            }
        }
        LOGGER.debug("Parsing elements finished.");
        logElement("Parsing elements return " + elements);
        return returnElements;
    }

    //TODO Добавить selectElementForParsing() если вернул не один элемент обернуть!!
    @Override
    public Element parsing(Element element) throws ExceptionParser {
        LOGGER.info("=== Parsing " + parsingElementName + " ===");

        if (element == null) {
            throw new IllegalArgumentException("Parsing Element not equals null!!");
        }

        logElement("Element = " + element);

        Element returnElement = processingElement(element);

        if (nextParser != null) {
            Elements children = parsingNextParser(element);
            returnElement.insertChildren(0, children);
        } else {
            LOGGER.debug("nextParser = null!!");
        }

        LOGGER.info("=== End parsing " + parsingElementName + " ===");
        logElement("returnElement = " + returnElement);
        return returnElement;
    }

    //TODO CREATE LogHelper and add this method.
    private static void logElement(String message) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace(message);
        }
    }


    private Elements parsingNextParser(Element element) throws ExceptionParser {
        Elements elements = new Elements();
        for (Element nextElement : nextParser.selectElementForParsing(element)) {
            elements.add(nextParser.parsing(nextElement));
        }
        return elements;
    }

    /**
     * Из element выбирает те элементы которые может спарсить данный парсер.
     * Используется как обертка для selectElementProcessing(). Для логирования.
     * @param element откуда нужно получить элементы.
     * @return элементы которые может обработать данный {@link ParserHTMLAbstract}.
     */
    public Elements selectElementForParsing(Element element) throws ExceptionParser {
        LOGGER.debug("Select element for parsing " + parsingElementName);
        logElement("Select element = " + element);
        Elements elements = selectElementProcessing(element);
        LOGGER.debug("Select element finished for parsing " + parsingElementName);
        logElement("Select element return Element = " + elements);
        return elements;
    }

    /**
     * Тоже самое что и selectElementForParsing() только добавляет логирование.
     * Переопределять желательно этот метод заместо selectElementForParsing().
     *
     * @param element Element из которого нужно выбрать Elements для {@link ParserHTMLAbstract#parsing(Element)}.
     * @return элементы которые может обработать данный {@link ParserHTMLAbstract}.
     */
    protected Elements selectElementProcessing(Element element) throws ExceptionParser {
        return element.getAllElements();
    }


    /**
     * Получение информации из {@link Element}.
     *
     * @param element {@link Element} который нужно спарсить.
     * @return Element c XML.
     */
    protected abstract Element processingElement(Element element) throws ExceptionParser;

    /**
     * Оборачивает {@link Elements} в {@link Element}.
     *
     * @param elements {@link Elements} который нужно обернуть.
     * @return {@link Element} обёртка.
     */
    public static Element wrapperForElements(Elements elements) {
        return ElementJsoupBuilder.createWrapper().insertChildren(0, elements);
    }
}
