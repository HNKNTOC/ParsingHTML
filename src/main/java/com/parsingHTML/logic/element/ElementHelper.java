package com.parsingHTML.logic.element;

import com.parsingHTML.logic.parser.exception.ExceptionParser;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Objects;

/**
 * Сюда вынесины часто повторяймые операции с Element.
 */
public final class ElementHelper {
    private static final Logger LOGGER = LogManager.getLogger(ElementHelper.class);

    private ElementHelper() {
    }


    /**
     * Проверка имени у элемента.
     * Проверяет равны ли element.tagName() и tagName.
     *
     * @param element     Элемент имя которого нужно проверить.
     * @param elementName Имя которое должно быть у элемента.
     * @throws ExceptionParser если проверка не пройдена.
     */
    public static void checkTagName(final Element element, final String elementName) throws ExceptionParser {
        LOGGER.debug("checkTagName() element=" + element + " elementName = " + elementName);
        final String tagNameResults = element.tagName();
        if (!elementName.equals(tagNameResults)) {
            fail(String.format("Tag Name not equal \"%s\" . Tag Name = \"%s\"",
                    elementName, tagNameResults));
        }
    }

    /**
     * Проверка n количество элементов с заданным tagName.
     *
     * @param element     Элемент в котором нужно проверить.
     * @param elementName tagName элементов которые нужно проверить.
     * @param elementSize Количество элементов должно быть в element.
     * @throws ExceptionParser если проверка не пройдена.
     */
    public static void checkElementSize(final Elements element, final String elementName, final int elementSize) throws ExceptionParser {
        LOGGER.debug("checkElementSize() element=" + element + " elementName = " + elementName + " elementSize = " + elementSize);
        Elements elements = element.select(elementName);
        if (elements.size() != elementSize) {
            fail(String.format("Element does not contain \"%d\" \"%s\". \"%s\" size =  \"%d\"."
                    , elementSize, elementName, elementName, elements.size()));
        }
    }

    /**
     * Проверка n количество элементов.
     * Проверяет равны ли elements.size() и size.
     *
     * @param elements Elements размер которого нужно проверить.
     * @param size     размер который должен быть у Elements.
     * @throws ExceptionParser если проверка не пройдена.
     */
    public static boolean checkElementSize(Elements elements, int size) throws ExceptionParser {
        if (elements.size() != size) {
            fail(String.format("Element size not equals \"%d\". Element size = \"%d\"",
                    size, elements.size()));
        }
        return false;
    }

    public static void checkElementSize(Element element, int size) throws ExceptionParser {
        checkElementSize(element.getAllElements(), size);
    }

    /**
     * Выбрать Elements из Elements с помощью cssQuery и вернуть.
     *
     * @param elements Element из которому получим Elements.
     * @param cssQuery Для получения Elements.
     * @return Elements полученные от cssQuery.
     * @throws ExceptionParser если не удолось получить Elements.
     */
    public static Elements selectElements(Elements elements, String cssQuery) throws ExceptionParser {
        LOGGER.debug("selectElements of Elements  cssQuery = " + cssQuery);
        Elements select = elements.select(cssQuery);
        try {
            checkElementSizeNotEqual(select, 0);
        } catch (ExceptionParser exceptionParser) {
            fail(String.format("Select Elements failed. CssQuery = \"%s\" return 0 element!!", cssQuery), exceptionParser);
        }
        LOGGER.debug("return " + select);
        return select;
    }

    /**
     * Выбрать Elements из Element с помощью cssQuery и вернуть.
     *
     * @param element  Element из которому получим Elements.
     * @param cssQuery Для получения Elements.
     * @return Elements полученные от cssQuery.
     * @throws ExceptionParser если не удолось получить Elements.
     */
    public static Elements selectElements(Element element, String cssQuery) throws ExceptionParser {
        return selectElements(element.getAllElements(), cssQuery);
    }

    /**
     * Проверяет не равны ли Elements.size() и size.
     * Противоположность checkElementTagNameSize();
     *
     * @param elements  Elements размер которого нужно проверить.
     * @param checkSize размер который должен быть у elements.
     * @throws ExceptionParser если проверка не пройдена.
     */
    public static void checkElementSizeNotEqual(Elements elements, int checkSize) throws ExceptionParser {
        if (elements.size() == checkSize) {
            fail(String.format("Element size must not be equal to \"%d\". Elements size = \"%d\"",
                    checkSize, elements.size()));
        }
    }

    /**
     * Выбрать Elements из Element с помощью cssQuery и вернуть один по index.
     *
     * @param element  Element в котором будем искать Elements.
     * @param cssQuery Для получения Elements.
     * @param index    Индекс Элемента который нужно вернуть.
     * @return Element полученные от cssQuery.
     * @throws ExceptionParser если не удолось получить Elements.
     */
    public static Element selectElement(Elements element, String cssQuery, int index) throws ExceptionParser {
        Elements select = element.select(cssQuery);
        if (select.size() >= index + 1) {
            Element returnElement = select.get(index);
            LOGGER.debug("selectElementForParsing return " + returnElement);
            return returnElement;
        }
        fail(String.format(
                "Failed get Element. Element = \"%s\" , cssQuery = \"%s\" , index = \"%s\"",
                element, cssQuery, index));
        return ElementJsoupBuilder.createElementEmpty();
    }


    public static Element selectElement(Element element, String cssQuery, int index) throws ExceptionParser {
        return selectElement(element.getAllElements(), cssQuery, index);
    }

    /**
     * Проверка значения атрибута.
     *
     * @param elementResults Элемент атрибут которого нужно проверить.
     * @param name           Имя атрибута.
     * @param value          Значение атрибута.
     * @throws ExceptionParser если проверка не пройдена.
     */
    public static void checkElementAttribute(final Element elementResults, String name, final String value) throws ExceptionParser {
        final String valueResult = elementResults.attr(name);
        if (!Objects.equals(value, valueResult)) {
            fail(String.format("Value attribute \"%s\" does not equal \"%s\". Value \"%s\" = \"%s\"",
                    name, value, name, valueResult));
        }
    }

    /**
     * Сообшить об ошибке слусившийся во время проверки Element.
     *
     * @param message текст ошибки.
     */
    public static void fail(String message) throws ExceptionParser {
        fail(message, null);
    }

    /**
     * Сообшить об ошибке слусившийся во время проверки Element.
     *
     * @param message текст ошибки.
     */
    public static void fail(String message, Exception e) throws ExceptionParser {
        ExceptionParser exceptionParser = null;
        if (e == null) {
            exceptionParser = new ExceptionParser(message);
        } else {
            exceptionParser = new ExceptionParser(message, e);
        }
        LOGGER.warn(message, exceptionParser);
        throw exceptionParser;
    }

}
