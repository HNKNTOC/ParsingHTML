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

    /**
     * Проверка имени у элемента.
     * Проверяет равны ли element.tagName() и tagName.
     *
     * @param element Элемент имя которого нужно проверить.
     * @param tagName Имя которое должно быть у элемента.
     * @return false если имя не совпадает.
     */
    public static boolean checkTagName(Element element, final String tagName) {
        final String tagNameResults = element.tagName();
        if (tagName.equals(tagNameResults)) {
            LOGGER.warn(String.format("Tag Name not equal %s . Tag Name = %s", tagName, tagNameResults));
            return false;
        }
        return true;
    }

    /**
     * Проверка n количество элементов с заданным tagName.
     *
     * @param element     Элемент в котором нужно проверить.
     * @param tagName     tagName элементов которые нужно проверить.
     * @param elementSize Количество элементов должно быть в element.
     * @return false если в element не совпадает количество element с tagName.
     */
    public static boolean checkElementsSize(Elements element, final String tagName, final int elementSize) {
        Elements elements = element.select(tagName);
        if (elements.size() != elementSize) {
            String message = String.format("checkElementTagNameSize does not contain %d %s. %s size =  %d."
                    , elementSize, tagName, tagName, elements.size());
            LOGGER.warn(message);
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
            String message = String.format("checkElementsSize Element size not equals %d. Element size = %d", size, elements.size());
            LOGGER.warn(message);
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
            LOGGER.warn(message);
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
            String message = String.format("checkNotElementSize Element size not equals %d. Elements size = %d",
                    checkSize, elements.size());
            LOGGER.warn(message);
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
        String message = String.format("selectElement failed get Element. Element = %s , cssQuery = \"%s\" , index = %s",
                element, cssQuery, index);
        LOGGER.warn(message);
        return ElementJsoupFactory.createElementEmpty();
    }

    /**
     * Проверка значения атрибута.
     *
     * @param elementResults Элемент атрибут которого нужно проверить.
     * @param name           Имя атрибута.
     * @param value          Значение атрибута.
     * @return false если значения атрибута не совпало.
     */
    public static boolean checkElementAttribute(final Element elementResults, final String name, final String value) {
        final String valueResult = elementResults.attr(name);
        if (!Objects.equals(value, valueResult)) {
            final String message = String.format("Value attribute %s does not equal %s.Value %s = %s",
                    name, value, name, valueResult);
            LOGGER.warn(message);
            return false;
        }
        return true;
    }

}
