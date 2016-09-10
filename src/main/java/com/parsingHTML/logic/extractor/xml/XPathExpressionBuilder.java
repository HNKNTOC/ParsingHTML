package com.parsingHTML.logic.extractor.xml;


import com.parsingHTML.logic.element.AttributeName;
import com.parsingHTML.logic.element.ElementName;

/**
 * Строитель для XPath выражений.
 */
public class XPathExpressionBuilder {
    /**
     * XPath выражение.
     */
    private StringBuilder thisExpression;

    public XPathExpressionBuilder() {
        thisExpression = new StringBuilder();
    }

    /**
     * Добавление NodeName в thisExpression.
     * Если thisExpression не пустой добавляет Slash.
     *
     * @param elementName NodeName который нужно добавить.
     * @return XPathExpressionBuilder для удобства.
     */
    public XPathExpressionBuilder addNodeName(ElementName elementName) {
        if (thisExpression.length() != 0) addSlash();
        thisExpression.append(elementName);
        return this;
    }

    /**
     * Добавление attributeName для текущего NodeName.
     *
     * @param attributeName attributeName который нужно добавить.
     * @param value         Значение для attributeName.
     * @return XPathExpressionBuilder для удобства.
     */
    public XPathExpressionBuilder addAttributes(AttributeName attributeName, String value) {
        thisExpression.append(String.format("[@%s='%s']", attributeName, value));
        return this;
    }

    private void addSlash() {
        thisExpression.append("/");
    }

    public String getExpression() {
        return thisExpression.toString();
    }

    public void clear() {
        thisExpression.setLength(0);
    }

}
