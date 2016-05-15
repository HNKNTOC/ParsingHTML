package com.company.parsingHTML.logic.parsing;

import org.htmlcleaner.TagNode;

/**
 * Начальный HTML парсер.
 */
public class ParserRoot extends ParserAbstract {
    @Override
    public boolean isParsing(String tagNodeName) {
        return false;
    }

    @Override
    public void parsing(TagNode tagNode) {

    }
}
