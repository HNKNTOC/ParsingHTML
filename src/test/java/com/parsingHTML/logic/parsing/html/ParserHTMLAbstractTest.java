package com.parsingHTML.logic.parsing.html;

import com.parsingHTML.logic.element.AttributeName;
import com.parsingHTML.logic.element.ElementHelper;
import com.parsingHTML.logic.element.ElementJsoupBuilder;
import com.parsingHTML.logic.element.ElementName;
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
 * Тест для ParserHTMLAbstractTest.
 */
public class ParserHTMLAbstractTest {
    private static final Logger LOGGER = LogManager.getLogger(ParserHTMLAbstractTest.class);
    private ParserHTMLAbstract parserHTMLAbstract;
    private Elements elements = new Elements();
    private final int size = new Random().nextInt(20);
    private ElementJsoupBuilder builder = new ElementJsoupBuilder();

    @Before
    public void setUp() throws Exception {
        parserHTMLAbstract = new ParserHTMLAbstractDummy();
        LOGGER.debug("size = " + size);
        for (int i = 0; i < size; i++) {
            elements.add(ElementJsoupBuilder.createElementEmpty());
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
        ElementName nameTest1 = ElementName.DAY_LESSON;
        ElementName nameTest2 = ElementName.DAY_TIME;

        Element test1 = builder.createElement(nameTest1).getThisElement();
        Element test2 = builder.createElement(nameTest2)
                .setAttr(AttributeName.NAME, "name1")
                .getThisElement();

        test1.appendChild(test2);

        Element element = ElementHelper.selectElement(test1, "[" + AttributeName.NAME + "=name1]", 0);
        ElementHelper.checkTagName(element, nameTest2);
    }

    @Test
    public void selectElements() throws Exception {
        ElementName nameTest1 = ElementName.DAY_LESSON;
        ElementName nameTest2 = ElementName.DAY_TIME;
        ElementName nameTest3 = ElementName.GROUP_LESSON;

        Element test1 = builder.createElement(nameTest1).getThisElement();
        Element test2 = builder.createElement(nameTest2).setAttr(AttributeName.NAME, "name1").getThisElement();
        Element test3 = builder.createElement(nameTest3).setAttr(AttributeName.NAME, "name1").getThisElement();

        test1.appendChild(test2);
        test1.appendChild(test3);
        Elements element = ElementHelper.selectElements(test1, "[" + AttributeName.NAME + "=name1]");
        assertTrue(element.size() == 2);
    }


    private class ParserHTMLAbstractDummy extends ParserHTMLAbstract {
        @Override
        public Element parsing(Element element) {
            return ElementJsoupBuilder.createElementEmpty();
        }
    }

}