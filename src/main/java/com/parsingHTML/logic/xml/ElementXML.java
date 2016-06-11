package com.parsingHTML.logic.xml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Для удобной передачи XML тегов.
 */
public class ElementXML {
    private String nameElement;
    private final List<ElementXML> children;
    private String text;
    private final HashMap<String,String> attributes;

    public ElementXML(String nameElement) {
        this.nameElement = nameElement;
        children = new ArrayList<>();
        attributes = new HashMap<>();
    }

    public void addChildren(ElementXML elementXML){
        children.add(elementXML);
    }

    public void setText(String text) {
        this.text = text;
    }

    public void addAttribute(String name, String value){
        attributes.put(name,value);
    }

    public String getAttributes(String name){
        return attributes.get(name);
    }

    public String getName() {
        return nameElement;
    }

    public String getText() {
        return text;
    }

    public List<ElementXML> getChildren() {
        return children;
    }

    public HashMap<String, String> getAttributes() {
        return attributes;
    }

    @Override
    public String toString() {
        return "ElementXML{" +
                "nameElement='" + nameElement + '\'' +
                ", children=" + children +
                ", text='" + text + '\'' +
                ", attributes=" + attributes +
                '}';
    }
}
