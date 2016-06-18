package com.parsingHTML.logic.parsing.tag;

import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Nikita on 18.06.2016.
 */
public class ParserHTMLAbstractTest {

    private final static String nameTegRoot = "nametegroot";
    private ParserHTMLAbstract parserHTMLAbstract;
    private int anInt = new Random().nextInt(100);
    private static String[] tagNameChildren;

    @Before
    public void setUp() throws Exception {
        tagNameChildren = new String[anInt];
        for (int i = 0; i < anInt; i++) {
            tagNameChildren[i] = "child" + i;
        }
        parserHTMLAbstract = new ParserHTMLAbstractDummy(nameTegRoot, tagNameChildren);
    }

    @Test
    public void isParsing() throws Exception {
        assertTrue(parserHTMLAbstract.isParsing(createTrueElement()));
    }

    @Test
    public void isParsingFalseTagName() throws Exception {
        Element falseElement = createTrueElement();
        falseElement.tagName("falseName");
        assertFalse(parserHTMLAbstract.isParsing(falseElement));
    }

    @Test
    public void isParsingFalseChildren() throws Exception {
        Element falseElement = createTrueElement();
        falseElement.empty();
        assertFalse(parserHTMLAbstract.isParsing(falseElement));
    }

    private Element createTrueElement() {
        Element element = new Element(Tag.valueOf(nameTegRoot), "");
        for (String tagNameChild : tagNameChildren) {
            element.appendChild(new Element(Tag.valueOf(tagNameChild), ""));
        }
        return element;
    }

    private Element createFalseElement() {
        Element element = new Element(Tag.valueOf("falseElement"), "");
        for (String tagNameChild : tagNameChildren) {
            element.appendChild(new Element(Tag.valueOf(tagNameChild), ""));
        }
        return element;
    }

    private class ParserHTMLAbstractDummy extends ParserHTMLAbstract {

        public ParserHTMLAbstractDummy(String tagName, String[] tagNameChildren) {
            super(tagName, tagNameChildren);
        }

        public ParserHTMLAbstractDummy(com.parsingHTML.logic.xml.factory.XMLFactory XMLFactory, String tagName, String[] tagNameChildren) {
            super(XMLFactory, tagName, tagNameChildren);
        }

        @Override
        public Element parsing(Element element) {
            return null;
        }
    }

}