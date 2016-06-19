package com.parsingHTML.logic.parsing.tag;

import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;

/**
 * Created by Nikita on 18.06.2016.
 */
public class ParserHTMLAbstractTest {

    private ParserHTMLAbstract parserHTMLAbstract;

    @Before
    public void setUp() throws Exception {
        parserHTMLAbstract = new ParserHTMLAbstractDummy();
    }

    @Test
    public void isParsing() throws Exception {
        assertFalse(parserHTMLAbstract.isParsing(new Element(Tag.valueOf("nameElement"), "")));
    }

    private class ParserHTMLAbstractDummy extends ParserHTMLAbstract {
        @Override
        public Element parsing(Element element) {
            return null;
        }
    }

}