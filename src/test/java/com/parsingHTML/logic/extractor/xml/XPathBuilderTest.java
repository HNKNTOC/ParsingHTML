package com.parsingHTML.logic.extractor.xml;

import com.parsingHTML.logic.element.AttributeName;
import com.parsingHTML.logic.element.ElementName;
import com.parsingHTML.logic.extractor.xml.XPathBuilder.XPathElement;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Тест для XPathBuilder.
 */
public class XPathBuilderTest {

    private static final Logger LOGGER = LogManager.getLogger(XPathBuilderTest.class);
    private XPathBuilder builder = new XPathBuilder();

    @Test
    public void addNodeName() throws Exception {
        builder.add(new XPathElement(ElementName.DAY_TIME))
                .add(new XPathElement(ElementName.WEEK_TIME));
        String result = builder.getExpression();
        LOGGER.debug("result = " + builder.toString());
        assertEquals(result, String.format("%s/%s",
                ElementName.DAY_TIME, ElementName.WEEK_TIME));
    }

    @Test
    public void addMultipleAttributes() throws Exception {
        builder.add(new XPathElement(ElementName.DAY_TIME))
                .add(new XPathElement(ElementName.WEEK_TIME)
                        .addAttr(AttributeName.DAY_NUMBER, 4)
                        .addAttr(AttributeName.DAY_NUMBER, 3)
                        .addAttr(AttributeName.DAY_NUMBER, 1))
                .add(new XPathElement(ElementName.LESSON));

        String result = builder.getExpression();
        LOGGER.debug("Result XPath = " + result);
        assertEquals(result, "day_time/week_time[@day-number='4' or @day-number='3' or @day-number='1']/lesson");
    }


    @Test
    public void addOneAttribute() throws Exception {
        builder.add(new XPathElement(ElementName.DAY_TIME))
                .add(new XPathElement(ElementName.WEEK_TIME)
                        .addAttr(AttributeName.DAY_NUMBER, 4))
                .add(new XPathElement(ElementName.LESSON));

        String result = builder.getExpression();
        LOGGER.debug("Result XPath = " + result);
        assertEquals(result, "day_time/week_time[@day-number='4']/lesson");
    }

    @Test
    public void checkClear() throws Exception {
        builder.add(new XPathElement(ElementName.LESSON));
        builder.clear();
        assertTrue(builder.getExpression().length() == 0);
        builder.add(new XPathElement(ElementName.LESSON));
        builder.clear();
        assertTrue(builder.getExpression().length() == 0);
    }

}