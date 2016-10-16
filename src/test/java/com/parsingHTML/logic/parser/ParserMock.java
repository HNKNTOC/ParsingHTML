package com.parsingHTML.logic.parser;

import com.parsingHTML.logic.element.ElementJsoupBuilder;
import com.parsingHTML.logic.parser.exception.ExceptionParser;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Для тестирования пустышка.
 */
public class ParserMock extends ParserHTMLAbstract {

    private String cssQuery = "*";

    public ParserMock(String cssQuery, String parsingElementName, ParserHTMLAbstract nextParserHTMLAbstract) {
        super(parsingElementName, nextParserHTMLAbstract);
        this.cssQuery = cssQuery;
    }

    @Override
    protected Elements selectElementProcessing(Element element) {
        return element.select(cssQuery);
    }

    @Override
    protected Element processingElement(Element element) throws ExceptionParser {
        return ElementJsoupBuilder.createElementEmpty();
    }

}
