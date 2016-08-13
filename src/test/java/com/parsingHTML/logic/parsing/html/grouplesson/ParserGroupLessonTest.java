package com.parsingHTML.logic.parsing.html.grouplesson;

import com.parsingHTML.logic.parsing.ElementHelper;
import com.parsingHTML.logic.parsing.html.ParserXMLCheck;
import org.jsoup.nodes.Element;
import org.junit.Test;

/**
 * Created by Nikita on 29.06.2016.
 */
public class ParserGroupLessonTest {
    @Test
    public void parsing() throws Exception {
        Element elementResults = ParserXMLCheck.parsingElement(new ParserGroupLesson(), "GroupLesson.com.parsingHTML.logic.html");
        ElementHelper.checkElementsSize(elementResults.children(), "dayLesson", 6);
    }

}