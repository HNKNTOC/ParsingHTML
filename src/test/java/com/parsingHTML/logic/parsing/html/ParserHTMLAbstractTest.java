package com.parsingHTML.logic.parsing.html;

import com.parsingHTML.logic.element.ElementHelper;
import com.parsingHTML.logic.element.ElementJsoupFactory;
import com.parsingHTML.logic.parser.ParserHTMLAbstract;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.jsoup.select.Elements;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Nikita on 18.06.2016.
 */
public class ParserHTMLAbstractTest {
    private static final Logger LOGGER = LogManager.getLogger(ParserHTMLAbstractTest.class);
    private ParserHTMLAbstract parserHTMLAbstract;
    private Elements elements = new Elements();
    private final int size = new Random().nextInt(20);

    @Before
    public void setUp() throws Exception {
        parserHTMLAbstract = new ParserHTMLAbstractDummy();
        LOGGER.debug("size = " + size);
        for (int i = 0; i < size; i++) {
            elements.add(ElementJsoupFactory.createElementEmpty());
        }

    }

    @Test
    public void isParsing() throws Exception {
        assertFalse(parserHTMLAbstract.isParsing(new Element(Tag.valueOf("nameElement"), "")));
    }

    @Test
    public void checkElementSize() throws Exception {
        assertTrue(ElementHelper.checkElementsSize(elements, size));
    }

    @Test
    public void checkElementSizeFalse() throws Exception {
        assertFalse(ElementHelper.checkElementsSize(new Elements(), 2));
    }

    @Test
    public void checkNotElementSize() throws Exception {
        assertTrue(ElementHelper.checkNotElementSize(new Elements(), 2));
    }

    @Test
    public void checkNotElementSizeFalse() throws Exception {
        assertFalse(ElementHelper.checkNotElementSize(new Elements(), 0));
    }

    @Test
    public void parsingElements() throws Exception {
        Elements results = parserHTMLAbstract.parsingElements(elements);
        assertTrue(results.size() == size);
    }

    @Test
    public void selectElement() throws Exception {
        Element test1 = ElementJsoupFactory.createElement("test1");
        Element test2 = ElementJsoupFactory.createElement("test2");
        test2.attr("id", "20");
        test1.appendChild(test2);
        Element element = ElementHelper.selectElement(test1, "[id=20]", 0);
        ElementHelper.checkTagName(element, "test2");
    }

    @Test
    public void selectElements() throws Exception {
        Element test1 = ElementJsoupFactory.createElement("test1");
        Element test2 = ElementJsoupFactory.createElement("test");
        Element test3 = ElementJsoupFactory.createElement("test");
        test2.attr("id", "20");
        test3.attr("id", "20");
        test1.appendChild(test2);
        test1.appendChild(test3);
        Elements element = ElementHelper.selectElements(test1, "[id=20]");
        assertTrue(element.size() == 2);
    }


    private class ParserHTMLAbstractDummy extends ParserHTMLAbstract {
        @Override
        public Element parsing(Element element) {
            return ElementJsoupFactory.createElementEmpty();
        }
    }

}