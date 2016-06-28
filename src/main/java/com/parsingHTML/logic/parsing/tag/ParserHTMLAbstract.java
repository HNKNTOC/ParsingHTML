package com.parsingHTML.logic.parsing.tag;


import com.parsingHTML.logic.parsing.Parser;
import com.parsingHTML.logic.xml.factory.ElementJsoupFactory;
import com.parsingHTML.logic.xml.factory.XMLFactory;
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
    protected final XMLFactory XMLFactory;

    /**
     * Создание ParserHTMLAbstract.
     */
    public ParserHTMLAbstract() {
        this(new ElementJsoupFactory());
    }

    /**
     * Создание ParserHTMLAbstract.
     *
     * @param XMLFactory Фабрика создающая XML элементы.
     */
    public ParserHTMLAbstract(XMLFactory XMLFactory) {
        this.XMLFactory = XMLFactory;
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
        checkElementSize(returnElements, 0);
        return returnElements;
    }

    /**
     * Проверяет равны ли Elements.size() и size.
     *
     * @param elements Elements размер которого нужно проверить.
     * @param size     размер который должен быть у Elements.
     * @return true если размеры Elements.size() и size совпадают.
     */
    public boolean checkElementSize(Elements elements, int size) {
        if (elements.size() != size) {
            LOGGER.warn("checkElementSize Element size not equals " + size + " Element size = " + elements.size());
            return false;
        }
        return true;
    }

    /**
     * Проверяет не равны ли Elements.size() и size.
     *
     * @param elements Elements размер которого нужно проверить.
     * @param size     размер который должен быть у Elements.
     * @return true если размеры Elements.size() и size не совпадают.
     */
    public boolean checkNotElementSize(Elements elements, int size) {
        if (elements.size() == size) {
            LOGGER.warn("checkNotElementSize Element size equals " + size);
            return false;
        }
        return true;
    }

    /**
     * cssQuery получает элементы и возвращает их.
     *
     * @param element  Element из которому получим Elements.
     * @param cssQuery cssQuery который нужно использовать.
     * @return Elements полученные от cssQuery.
     */
    public Elements selectElements(Element element, String cssQuery) {
        Elements select = element.select(cssQuery);
        if (!checkNotElementSize(select, 0)) {
            String message = String.format("cssQuery %s return 0 element!", cssQuery);
            LOGGER.warn(message);
        }
        return select;
    }


    /**
     * cssQuery получает элементы и возвращает один из них.
     *
     * @param element  Element из которому получим Elements.
     * @param cssQuery cssQuery который нужно использовать.
     * @param index    Индекс Элемента который нужно вернуть.
     * @return Element полученные от cssQuery.
     */
    public Element selectElement(Element element, String cssQuery, int index) {
        Elements select = element.select(cssQuery);
        if (select.size() >= index + 1) {
            Element returnElement = select.get(index);
            LOGGER.debug("selectElement return " + returnElement);
            return returnElement;
        }
        LOGGER.debug("selectElement return ElementEmpty! select.size = " + select.size());
        return ElementJsoupFactory.createElementEmpty();
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
}
