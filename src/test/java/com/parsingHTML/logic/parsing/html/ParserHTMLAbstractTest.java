package com.parsingHTML.logic.parsing.html;

import com.parsingHTML.logic.element.ElementJsoupBuilder;
import com.parsingHTML.logic.element.ElementName;
import com.parsingHTML.logic.parser.ParserMock;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Element;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * ParserHTMLAbstract тестится как ParserMock !!!
 */
public class ParserHTMLAbstractTest {
    private static final Logger LOGGER = LogManager.getLogger(ParserHTMLAbstractTest.class);
    private static final String CSS_SELECT = ElementName.EMPTY.getName();
    private static final String CSS_SELECT_ALL_ELEMENT = "*";

    private static final String parsingElementName = "elementName";
    private static final Element elementEmpty = ElementJsoupBuilder.createElementEmpty();
    private static ParserMock nextParser = null;
    private static ParserMock mainParser = null;

    @Before
    public void setUp() throws Exception {
        nextParser = new ParserMock(CSS_SELECT, parsingElementName, null);
        mainParser = new ParserMock(CSS_SELECT, parsingElementName, nextParser);
    }


    @Test
    public void exceptionParserInNextParser() throws Exception {
        mainParser.parsing(elementEmpty);
    }

    @Test
    public void exceptionParserInMainParser() throws Exception {
        mainParser.parsing(elementEmpty);
    }

    @Test
    public void notExceptionParserInMainParser() throws Exception {
        mainParser.parsing(elementEmpty);
    }

    @Test
    public void nextParserEqualsNull() throws Exception {
        mainParser = new ParserMock(CSS_SELECT, parsingElementName, null);
        Element parsing = mainParser.parsing(elementEmpty);
        assertEquals(parsing.select(CSS_SELECT_ALL_ELEMENT).size(), 1);
    }

    @Test
    public void nextParserEqualsNotNull() throws Exception {
        Element parsing = mainParser.parsing(elementEmpty);
        assertEquals(parsing.select(CSS_SELECT_ALL_ELEMENT).size(), 2);
    }

}