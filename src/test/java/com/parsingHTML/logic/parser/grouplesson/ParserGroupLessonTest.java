package com.parsingHTML.logic.parser.grouplesson;

import com.parsingHTML.logic.element.ElementName;
import com.parsingHTML.logic.parser.ParserHelper;
import com.parsingHTML.logic.parsing.html.ParserXMLCheck;
import org.jsoup.nodes.Element;
import org.junit.Test;

/**
 * Тест для ParserGroupLessonTest.
 */
public class ParserGroupLessonTest {
    @Test
    public void parsing() throws Exception {
        Element elementResults = ParserXMLCheck.parsingElement(new ParserGroupLesson(null), "GroupLesson.html");
        ParserHelper.checkTagName(elementResults, ElementName.GROUP_LESSON.getName());
    }

}