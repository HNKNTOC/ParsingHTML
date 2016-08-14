package com.parsingHTML.logic.parser.grouplesson;

import com.parsingHTML.logic.element.ElementHelper;
import com.parsingHTML.logic.parsing.html.ParserXMLCheck;
import org.jsoup.nodes.Element;
import org.junit.Test;

/**
 * Created by Nikita on 29.06.2016.
 */
public class ParserGroupLessonTest {
    @Test
    public void parsing() throws Exception {
        Element elementResults = ParserXMLCheck.parsingElement(new ParserGroupLesson(), "GroupLesson.html");
        ElementHelper.checkElementsSize(elementResults.children(), "dayLesson", 6);
    }

}