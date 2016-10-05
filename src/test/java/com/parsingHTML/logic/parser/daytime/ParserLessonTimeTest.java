package com.parsingHTML.logic.parser.daytime;

import com.parsingHTML.logic.element.ElementName;
import com.parsingHTML.logic.parser.ParsirHelper;
import com.parsingHTML.logic.parser.exception.ExceptionParser;
import com.parsingHTML.logic.parsing.html.ParserXMLCheck;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

/**
 * Тестирование ParserLessonTime.
 */
public class ParserLessonTimeTest {
    @Test
    public void parsing() throws Exception {
        Element elementResults = ParserXMLCheck.parsingElement("LessonTime.html");
        ParserLessonTime parser = new ParserLessonTime(null);
        Elements elements = parser.selectElementProcessing(elementResults);

        Elements resultsParsing = new Elements();
        for (Element element : elements) {
            resultsParsing.add(parser.parsing(element));
        }

        ParsirHelper.checkElementSize(resultsParsing, 7);

        check(resultsParsing.get(0), "1", "08:30", "09:15", "09:20", "10:05");
        check(resultsParsing.get(1), "2", "10:15", "11:00", "11:05", "11:50");
        check(resultsParsing.get(2), "3", "12:25", "13:10", "13:15", "14:00");
        check(resultsParsing.get(3), "4", "14:35", "15:20", "15:25", "16:10");
        check(resultsParsing.get(4), "5", "16:20", "17:05", "17:10", "17:55");
        check(resultsParsing.get(5), "6", "18:05", "18:50", "18:55", "19:40");
        check(resultsParsing.get(6), "7", "19:50", "20:35", "20:40", "21:25");

    }

    private void check(Element elementResults, String number, String start1, String end1, String start2, String end2) throws ExceptionParser {
        ParsirHelper.checkTagName(elementResults, ElementName.LESSON_TIME.getName());
        ParsirHelper.checkElementAttribute(elementResults, "number", number);
        ParsirHelper.checkElementAttribute(elementResults, "start1", start1);
        ParsirHelper.checkElementAttribute(elementResults, "end1", end1);
        ParsirHelper.checkElementAttribute(elementResults, "start2", start2);
        ParsirHelper.checkElementAttribute(elementResults, "end2", end2);
    }


}