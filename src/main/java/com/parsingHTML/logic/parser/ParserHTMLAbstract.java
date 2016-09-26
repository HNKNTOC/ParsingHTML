package com.parsingHTML.logic.parser;


import com.parsingHTML.logic.element.ElementFactory;
import com.parsingHTML.logic.element.ElementJsoupBuilder;
import com.parsingHTML.logic.element.ElementJsoupFactory;
import com.parsingHTML.logic.parser.exception.ExceptionList;
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
     * Класс в котором хранятся ошибки произошедшие во время получения работы {@link ParserHTMLAbstract}.
     */
    private ExceptionList<ExceptionParser> exceptionList = new ExceptionList<>();
    /**
     * Возвращает ли данный парсер множество елементов.
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

    public ParserHTMLAbstract() {
        //TODO DELETE THIS!!
        elementFactory = new ElementJsoupFactory();
        nextParser = null;
        parsingElementName = "";
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


    @Override
    public ExceptionList getExceptionList() {
        return exceptionList;
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
     * Проверка есть ли ошибки у {@link ParserHTMLAbstract#nextParser}.
     */
    private void checkExceptionNextParser() {
        if (!nextParser.getExceptionList().isEmpty()) {
            reportException("NextParser end not Successful!!");
        }
    }

    protected void reportException(String message) {
        ExceptionParser exception = new ExceptionParser(message);
        LOGGER.warn("reportException() ", exception);
        exceptionList.add(exception);
    }

    /**
     * Получает каждый элемент из Elements и возвращает полученные элементы.
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

    @Override
    public Element parsing(Element elementHTML) {
        LOGGER.info("=== Parsing " + parsingElementName + " ===");

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("Element = " + elementHTML);
        }

        Elements elementsSelect = selectElement(elementHTML);

        Element returnElement = processingElement(elementsSelect);

        if (nextParser != null && elementsSelect != null) {
            parsingNextParser(elementHTML, returnElement);
            checkExceptionNextParser();
        } else {
            LOGGER.debug("nextParser = null!!");
        }

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("returnElement = " + returnElement);
        }
        LOGGER.info("=== End parsing " + parsingElementName + " ===");
        return returnElement;
    }


    private void parsingNextParser(Element elementHTML, Element returnElement) {
        Element parsing = nextParser.parsing(elementHTML);
        if (nextParser.isParseElements) {
            returnElement.insertChildren(0, parsing.children());
        } else {
            returnElement.appendChild(parsing);
        }
    }

    /**
     * Из elementHTML выберает те элементы которые может спарсить данный парсер.
     * @param elementHTML откуда нужно получить элементы.
     * @return элементы которые может обработать даный {@link ParserHTMLAbstract}.
     */
    public Elements selectElement(Element elementHTML) {
        return elementHTML.getAllElements();
    }


    /**
     * Получение информации из {@link Element} c HTML.
     *
     * @param elements {@link Element} который нужно спарсить.
     * @return Element c XML.
     */
    protected Element processingElement(Elements elements) {
        //TODO set Abstract.
        return ElementJsoupBuilder.createElementEmpty();
    }

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
