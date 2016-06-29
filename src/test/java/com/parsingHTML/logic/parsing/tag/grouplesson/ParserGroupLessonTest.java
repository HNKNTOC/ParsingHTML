package com.parsingHTML.logic.parsing.tag.grouplesson;

import com.parsingHTML.logic.parsing.tag.ParserElementTest;
import org.jsoup.nodes.Element;
import org.junit.Test;

/**
 * Created by Nikita on 29.06.2016.
 */
public class ParserGroupLessonTest {
    @Test
    public void parsing() throws Exception {
        Element elementResults = ParserElementTest.parsingElement(new ParserGroupLesson(), "GroupLesson.html");
        ParserElementTest.checkElementSize(elementResults, "dayLesson", 6);
    }

}