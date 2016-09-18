package com.parsingHTML.logic.parser;


import com.parsingHTML.logic.element.ElementFactory;
import com.parsingHTML.logic.element.ElementHelper;
import com.parsingHTML.logic.element.ElementJsoupBuilder;
import com.parsingHTML.logic.element.ElementJsoupFactory;
import com.parsingHTML.logic.parser.exception.ExceptionList;
import com.parsingHTML.logic.parser.exception.ExceptionParser;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;

/**
 * Абстрактный класс для всех парсеров HTML.
 * Получает {@link Element} c XML данные из {@link Element} с HTML.
 */
public abstract class ParserHTMLAbstract implements Parser<Element, Element> {

    /**
     * CSS Select для выбора Element
     * из которого будет получена информация.
     */
    protected static final String cssSelectMainDefault = "*";
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
     * CSS Select для выбора Element
     * из которого будет получена информация.
     */
    protected final String cssSelectMain;
    /**
     * Имя элемента который получит данный ParserHTMLAbstract.
     */
    private final String parsingElementName;
    /**
     * Класс в котором хранятся ошибки произошедшие во время получения работы {@link ParserHTMLAbstract}.
     */
    private ExceptionList<ExceptionParser> exceptionList = new ExceptionList<>();

    /**
     * Создание ParserHTMLAbstract.
     *
     * @param cssSelectMain      CSS Select для выбора Element.
     * @param parsingElementName Имя получаемого элемента.
     */
    public ParserHTMLAbstract(String cssSelectMain, String parsingElementName, ParserHTMLAbstract nextParser) {
        this(new ElementJsoupFactory(), nextParser, cssSelectMain, parsingElementName);
    }

    public ParserHTMLAbstract() {
        //TODO DELETE THIS!!
        elementFactory = new ElementJsoupFactory();
        nextParser = null;
        cssSelectMain = "";
        parsingElementName = "";
    }

    /**
     * Создание ParserHTMLAbstract.
     *
     * @param ElementFactory     Фабрика создающая XML элементы.
     * @param nextParser         Следующий парсер.
     * @param cssSelectMain      CSS Select для выбора Element.
     * @param parsingElementName Имя получаемого элемента.
     */
    public ParserHTMLAbstract(ElementFactory ElementFactory, ParserHTMLAbstract nextParser, String cssSelectMain, String parsingElementName) {
        this.elementFactory = ElementFactory;
        this.nextParser = nextParser;
        this.parsingElementName = parsingElementName;

        if (cssSelectMain != null) {
            this.cssSelectMain = cssSelectMain;
        } else {
            LOGGER.debug("CssSelectMain = null! CssSelectMain = " + cssSelectMainDefault);
            this.cssSelectMain = cssSelectMainDefault;
        }
    }

    public static String getCssSelectMainDefault() {
        return cssSelectMainDefault;
    }

    public ParserHTMLAbstract getNextParser() {
        return nextParser;
    }

    public String getCssSelectMain() {
        return cssSelectMain;
    }

    public String getParsingElementName() {
        return parsingElementName;
    }


    @Override
    public Element parsing(Element element) {
        LOGGER.info("=== Parsing " + parsingElementName + " ===");

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Element = " + element);
        }

        Elements elementsSelect = selectMainElement(element);

        Element returnElement = processingElement(elementsSelect);

        if (nextParser != null && elementsSelect != null) {
            returnElement.appendChild(getResultNextParser(element));
            checkExceptionNextParser();
        } else {
            LOGGER.debug("nextParser = null!!");
        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("returnElement = " + returnElement);
        }
        LOGGER.info("=== End parsing " + parsingElementName + " ===");
        return returnElement;
    }

    @Override
    public List<ExceptionParser> getExceptions() {
        return exceptionList.getAll();
    }

    @Override
    public void clearExceptions() {
        LOGGER.debug("clearExceptions()");
        exceptionList.clear();
    }

    @Override
    public boolean isSuccessful() {
        return exceptionList.isEmpty();
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

    private Element getResultNextParser(Element element) {
        return nextParser.parsing(element);
    }

    /**
     * Проверка есть ли ошибки у {@link ParserHTMLAbstract#nextParser}.
     */
    private void checkExceptionNextParser() {
        if (!nextParser.isSuccessful()) {
            reportException("NextParser end not Successful!!");
        }
    }

    /**
     * Выбрать из {@link Element} элементы с помощью {@link ParserHTMLAbstract#cssSelectMain}
     *
     * @param element из которого будут выбираться {@link Element}.
     * @return {@link Element}  полученные от {@link ParserHTMLAbstract#cssSelectMain}.
     */
    private Elements selectMainElement(Element element) {
        Elements elementsSelect = ElementHelper.selectElements(element, cssSelectMain);

        if (elementsSelect.size() == 0) {
            reportException("Css Select Main return 0 element!!");
        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("selectMainElement() return " + elementsSelect);
        }
        return elementsSelect;
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


    /**
     * Получение информации из {@link Element} c HTML.
     *
     * @param elements {@link Element} который нужно спарсить.
     * @return Element c XML.
     */
    protected Element processingElement(Elements elements) {
        //TODO DELETE THIS!
        return ElementJsoupBuilder.createElementEmpty();
    }
}
