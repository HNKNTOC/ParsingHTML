package com.company.parsingHTML.logic.parsing;

import org.htmlcleaner.TagNode;

/**
 * Интерфейс для парсеров тегов.
 */
public interface ParserTagNode {
    /**
     * Может ли данный парсер обработать TagNode.
     * @param tagNodeName имя TagNode.
     * @return true если может спарсить данный TagNode.
     */
    boolean isParsing(String tagNodeName);

    /**
     * Парсинг TagNode.
     * Перед парсингом лучше проверить с помощью isParsing().
     * @param tagNode который нужно парсить.
     */
    void parsing(TagNode tagNode);

    /**
     * Оповещает парсер о новом теге который нужно обработать.
     * Игнорирует если парсер не может обработать TagNode.
     */
    void alertTagNode(TagNode tagNode);
}