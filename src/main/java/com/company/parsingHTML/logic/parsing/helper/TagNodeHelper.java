package com.company.parsingHTML.logic.parsing.helper;

import org.htmlcleaner.TagNode;

/**
 * Парсит TagNode из T.
 */
public interface TagNodeHelper<T> {
    /**
     * Парсит TagNode из T.
     * @param t изчего будет парсится TagNode.
     * @return спарсенный TagNode.
     */
    TagNode parsing(T t);
}
