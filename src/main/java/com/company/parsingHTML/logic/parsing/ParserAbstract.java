package com.company.parsingHTML.logic.parsing;

import org.htmlcleaner.TagNode;

import java.util.ArrayList;

/**
 * Обстрактный класс для любого парсипа xml.
 */
public abstract class ParserAbstract implements ParserTagNode {
    private ArrayList<ParserTagNode> parserTagNodeList;

    public ParserAbstract() {
        parserTagNodeList = new ArrayList<>();
    }

    public void addTagHandler(ParserTagNode parserTagNode){
        parserTagNodeList.add(parserTagNode);
    }

    public ArrayList<ParserTagNode> getParserTagNodeList() {
        return parserTagNodeList;
    }

    /**
     * Оповешвет все ParserTagNode о новом TagNode.
     * @param tagNode
     */
    protected void newTagNode(TagNode tagNode){
        String name = tagNode.getName();
        for (ParserTagNode parserTagNode : parserTagNodeList) {
            if(parserTagNode.isParsing(name)){
                parserTagNode.parsing(tagNode);
            }
        }
    }

    @Override
    public void alertTagNode(TagNode tagNode) {
        if(isParsing(tagNode.getName())){
            parsing(tagNode);
        }
    }
}
