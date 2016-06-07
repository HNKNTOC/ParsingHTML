package com.company.parsingHTML.logic.xml;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import static org.junit.Assert.*;

/**
 * Created by Nikita on 05.06.2016.
 */
public class ConstructorDOMTest {

    private ConstructorDOM constructorDOM;

    @Before
    public void setUp() throws Exception {
        constructorDOM = new ConstructorDOM();
    }

    @Test
    public void newElement() throws Exception {
        ElementXML elementXML = new ElementXML("Root");
        addAtt(elementXML);
        constructorDOM.newElement(elementXML);
        Document xml = constructorDOM.getXML();
        Node item = xml.getChildNodes().item(0);
        assertTrue(item.getNodeName().equals("Root"));
        NamedNodeMap attributes = item.getAttributes();
        assertTrue(attributes.item(0).getTextContent().equals("1"));
        assertTrue(attributes.item(1).getTextContent().equals("2"));
        assertTrue(attributes.item(2).getTextContent().equals("3"));
        assertTrue(item.getTextContent().equals("Hello World!!"));
    }

    @Test
    public void getXML() throws Exception {

    }

    @Test
    public void clear() throws Exception {

    }

    private static void addAtt(ElementXML elementXML) {
        elementXML.addAttribute("Name",elementXML.getName());
        elementXML.addAttribute("Att_1","1");
        elementXML.addAttribute("Att_2","2");
        elementXML.addAttribute("Att_3","3");
        elementXML.setText("Hello World!!");
    }

}