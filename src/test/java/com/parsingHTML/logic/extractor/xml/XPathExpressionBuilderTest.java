package com.parsingHTML.logic.extractor.xml;

import com.parsingHTML.logic.element.AttributeName;
import com.parsingHTML.logic.element.ElementName;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Тест для XPathExpressionBuilder.
 */
public class XPathExpressionBuilderTest {

    private XPathExpressionBuilder builder = new XPathExpressionBuilder();
    private static final Logger LOGGER = LogManager.getLogger(XPathExpressionBuilderTest.class);


    @Test
    public void addNodeName() throws Exception {
        builder.addNodeName(ElementName.DAY_TIME)
                .addNodeName(ElementName.WEEK_TIME);
        String result = builder.getExpression();
        LOGGER.debug("result = " + builder.toString());
        assertEquals(result, String.format("%s/%s",
                ElementName.DAY_TIME, ElementName.WEEK_TIME));
    }

    @Test
    public void addAttributes() throws Exception {
        builder.addNodeName(ElementName.DAY_TIME)
                .addNodeName(ElementName.WEEK_TIME)
                .addAttributes(AttributeName.DAY_NUMBER, "4")
                .addNodeName(ElementName.LESSON);

        String result = builder.getExpression();
        LOGGER.debug("result = " + builder.toString());
        assertEquals(result, String.format("%s/%s[@%s='%s']/%s",
                ElementName.DAY_TIME, ElementName.WEEK_TIME,
                AttributeName.DAY_NUMBER, "4", ElementName.LESSON));
    }

    @Test
    public void clear() throws Exception {
        builder.addNodeName(ElementName.LESSON);
        builder.clear();
        assertTrue(builder.getExpression().length() == 0);
        builder.addNodeName(ElementName.LESSON);
        builder.clear();
        assertTrue(builder.getExpression().length() == 0);
    }

}