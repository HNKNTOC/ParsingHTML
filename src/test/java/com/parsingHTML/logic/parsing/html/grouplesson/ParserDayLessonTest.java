package com.parsingHTML.logic.parsing.html.grouplesson;

import com.parsingHTML.logic.parsing.html.ParserElementTest;
import org.jsoup.nodes.Element;
import org.junit.Test;

/**
 * Created by Nikita on 27.06.2016.
 */
public class ParserDayLessonTest {
    @Test
    public void parsing() throws Exception {
        Element elementResults = ParserElementTest.parsingElement(new ParserDayLesson(),"DayLesson.html");
        ParserElementTest.checkElementSize(elementResults,"lesson",5);
        ParserElementTest.checkElementAttribute(elementResults,"dayName","Понедельник");
    }

}