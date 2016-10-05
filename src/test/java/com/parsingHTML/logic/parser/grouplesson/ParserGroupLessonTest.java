package com.parsingHTML.logic.parser.grouplesson;

import com.parsingHTML.logic.element.ElementName;
import com.parsingHTML.logic.parser.ParserHTMLFactory;
import com.parsingHTML.logic.parser.ParsirHelper;
import com.parsingHTML.logic.parsing.html.ParserXMLCheck;
import org.jsoup.nodes.Element;
import org.junit.Test;

/**
 * Тест для ParserGroupLessonTest.
 */
public class ParserGroupLessonTest {
    @Test
    public void parsing() throws Exception {
        Element elementResults = ParserXMLCheck.parsingElement(ParserHTMLFactory.createParserGroupLesson(), "GroupLesson.html");
        ParsirHelper.checkElementSize(elementResults.children(), ElementName.DAY_LESSON.getName(), 6);
    }

}