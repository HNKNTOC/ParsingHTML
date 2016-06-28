package com.parsingHTML.logic.parsing.tag.grouplesson;

import com.parsingHTML.logic.parsing.tag.ParserElementTest;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Nikita on 27.06.2016.
 */
public class ParserLessonTest {
    @Test
    public void parsing() throws Exception {
        Element elementResults = ParserElementTest.parsingElement(new ParserLesson(),"Lesson.html");
        Elements select = elementResults.select(".num_para");

    }

}