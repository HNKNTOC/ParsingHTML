package com.company.parsingHTML.logic.xml;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Nikita on 05.06.2016.
 */
public class ConstructorDOM implements ConstructorXML<Document> {
    private static final Logger LOGGER = LogManager.getLogger(ConstructorDOM.class);
    private DocumentBuilder documentBuilder;
    private Document document;

    public ConstructorDOM() {
        try {
            documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            document = documentBuilder.newDocument();
        } catch (ParserConfigurationException e) {
            LOGGER.warn(e);
            e.printStackTrace();
        }
        LOGGER.info("Create "+toString());
    }

    @Override
    public boolean newElement(ElementXML elementxml) {
        LOGGER.debug("newElement "+elementxml.getName());
        document.appendChild(createElement(elementxml));
        return false;
    }

    private Element createElement(ElementXML elementxml) {
        LOGGER.debug("====== createElement "+elementxml.getName()+" ======");
        Element element = document.createElement(elementxml.getName());
        addAttributes(element,elementxml.getAttributes());
        setText(element,elementxml.getText());
        addChildren(element,elementxml.getChildren());
        LOGGER.debug("============");
        return element;
    }

    private void addChildren(Element element, List<ElementXML> childrens) {
        for (ElementXML child : childrens) {
            Element children = createElement(child);
            LOGGER.debug("addChildren "+children.getTagName());
            element.appendChild(children);
        }
    }

    private void setText(Element element, String text) {
        LOGGER.debug("addText = "+text);
        element.setTextContent(text);
    }

    private void addAttributes(Element element, HashMap<String, String> attributes) {
        for (String s : attributes.keySet()) {
            String value = attributes.get(s);
            LOGGER.debug("Add attribute "+s+" = "+value);
            element.setAttribute(s, value);
        }
    }

    @Override
    public Document getXML() {
        return document;
    }

    @Override
    public void clear() {
        document = documentBuilder.newDocument();
    }
}
