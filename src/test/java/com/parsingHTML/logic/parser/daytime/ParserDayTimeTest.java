package com.parsingHTML.logic.parser.daytime;

import com.parsingHTML.logic.element.AttributeName;
import com.parsingHTML.logic.element.ElementHelper;
import com.parsingHTML.logic.element.ElementJsoupBuilder;
import com.parsingHTML.logic.element.ElementName;
import com.parsingHTML.logic.parser.ParserHTMLAbstract;
import com.parsingHTML.logic.parser.ParserHTMLFactory;
import com.parsingHTML.logic.parsing.html.ParserXMLCheck;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

/**
 * Тестирование ParserDayTime.
 */
public class ParserDayTimeTest {

    @Test
    public void parsing() throws Exception {
        Element elementResults = ParserXMLCheck.parsingElement("DayTime.html");
        ParserDayTime parserHTMLAbstract = new ParserDayTime(null);
        Element wrapper = ElementJsoupBuilder.createWrapper();
        for (Element element : parserHTMLAbstract.selectElementForParsing(elementResults)) {
            wrapper.appendChild(parserHTMLAbstract.parsing(element));
        }

        ElementHelper.checkElementSize(wrapper.children(), ElementName.DAY_TIME.getName(), 6);
        int dayTimeNumber = 2;
        for (Element element : wrapper.children()) {
            ElementHelper.checkTagName(element, ElementName.DAY_TIME.getName());
            ElementHelper.checkElementAttribute(
                    element, AttributeName.DAY_TIME_NUMBER.getName(), String.valueOf(dayTimeNumber));
            if (dayTimeNumber == 3 || dayTimeNumber == 4 || dayTimeNumber == 5 || dayTimeNumber == 6) {
                ElementHelper.checkElementAttribute(
                        element, AttributeName.OVERRIDE.getName(), "2");
            }
            dayTimeNumber++;
        }
    }

    @Test
    public void selectElementTest() throws Exception {
        Element elementResults = ParserXMLCheck.parsingElement("DayTime.html");
        ParserHTMLAbstract parser = ParserHTMLFactory.createParserDayTime();
        Elements elements = parser.selectElementForParsing(elementResults);
        ElementHelper.checkElementSize(elements, 6);
    }

}