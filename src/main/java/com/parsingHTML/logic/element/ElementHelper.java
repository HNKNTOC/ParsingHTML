package com.parsingHTML.logic.element;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Objects;

/**
 * Сюда вынесины часто повторяймые операции с Element.
 */
public class ElementHelper {
    private static final Logger LOGGER = LogManager.getLogger(ElementHelper.class);

    private ElementHelper() {
    }

    /**
     * Сообшить об ошибке слусившийся во время проверки Element.
     * @param message текст ошибки.
     */
    public static void fail(String message) {
        LOGGER.warn(message);
    }

    /**
     * Проверка имени у элемента.
     * Проверяет равны ли element.tagName() и tagName.
     *
     * @param element Элемент имя которого нужно проверить.
     * @param elementName Имя которое должно быть у элемента.
     * @return false если имя не совпадает.
     */
    public static boolean checkTagName(Element element, final ElementName elementName) {
        final String tagNameResults = element.tagName();
        if (elementName.getName().equals(tagNameResults)) {
            return true;
        }
        fail(String.format("Tag Name not equal %s . Tag Name = %s", elementName, tagNameResults));
        return false;
    }

    /**
     * Проверка n количество элементов с заданным tagName.
     *
     * @param element     Элемент в котором нужно проверить.
     * @param elementName     tagName элементов которые нужно проверить.
     * @param elementSize Количество элементов должно быть в element.
     * @return false если в element не совпадает количество element с tagName.
     */
    public static boolean checkElementsSize(Elements element, final ElementName elementName, final int elementSize) {
        Elements elements = element.select(elementName.getName());
        if (elements.size() != elementSize) {
            fail(String.format("checkElementTagNameSize does not contain %d %s. %s size =  %d."
                    , elementSize, elementName, elementName, elements.size()));
            return false;
        }
        return true;
    }

    /**
     * Проверка n количество элементов.
     * Проверяет равны ли elements.size() и size.
     *
     * @param elements Elements размер которого нужно проверить.
     * @param size     размер который должен быть у Elements.
     * @return true если размеры Elements.size() и size совпадают.
     */
    public static boolean checkElementsSize(Elements elements, int size) {
        if (elements.size() != size) {

            fail(String.format(
                    "checkElementsSize Element size not equals %d. Element size = %d", size, elements.size()));
            return false;
        }
        return true;
    }

    /**
     * Выбрать Elements из Element с помощью cssQuery и вернуть.
     *
     * @param element  Element из которому получим Elements.
     * @param cssQuery Для получения Elements.
     * @return Elements полученные от cssQuery.
     */
    public static Elements selectElements(Element element, String cssQuery) {
        LOGGER.debug("selectElements element=" + element.nodeName() + " cssQuery = " + cssQuery);
        Elements select = element.select(cssQuery);
        if (!checkNotElementSize(select, 0)) {
            String message = String.format("selectElements cssQuery = \"%s\" return 0 element!", cssQuery);
            fail(message);
        }
        return select;
    }

    /**
     * Проверяет не равны ли Elements.size() и size.
     * Противоположность checkElementTagNameSize();
     *
     * @param elements  Elements размер которого нужно проверить.
     * @param checkSize размер который должен быть у elements.
     * @return true если размеры Elements.size() и size не совпадают.
     */
    public static boolean checkNotElementSize(Elements elements, int checkSize) {
        if (elements.size() == checkSize) {
            fail(String.format("checkNotElementSize Element size not equals %d. Elements size = %d",
                    checkSize, elements.size()));
            return false;
        }
        return true;
    }

    /**
     * Выбрать Elements из Element с помощью cssQuery и вернуть один по index.
     *
     * @param element  Element в котором будем искать Elements.
     * @param cssQuery Для получения Elements.
     * @param index    Индекс Элемента который нужно вернуть.
     * @return Element полученные от cssQuery.
     */
    public static Element selectElement(Element element, String cssQuery, int index) {
        Elements select = element.select(cssQuery);
        if (select.size() >= index + 1) {
            Element returnElement = select.get(index);
            LOGGER.debug("selectElement return " + returnElement);
            return returnElement;
        }
        fail(String.format(
                "selectElement failed get Element. Element = %s , cssQuery = \"%s\" , index = %s",
                element, cssQuery, index));
        return ElementJsoupBuilder.createElementEmpty();
    }

    /**
     * Проверка значения атрибута.
     *
     * @param elementResults Элемент атрибут которого нужно проверить.
     * @param name           Имя атрибута.
     * @param value          Значение атрибута.
     * @return false если значения атрибута не совпало.
     */
    public static boolean checkElementAttribute(final Element elementResults, String name, final String value) {
        final String valueResult = elementResults.attr(name);
        if (!Objects.equals(value, valueResult)) {
            fail(String.format("Value attribute %s does not equal %s.Value %s = %s",
                    name, value, name, valueResult));
            return false;
        }
        return true;
    }

}
