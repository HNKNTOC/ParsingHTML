package com.parsingHTML.logic.parsing.tag.daytime;

import com.parsingHTML.logic.parsing.tag.ParserElementTest;
import com.parsingHTML.logic.parsing.tag.ParserHTMLAbstract;
import com.parsingHTML.logic.xml.factory.ElementName;
import org.jsoup.nodes.Element;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Nikita on 25.06.2016.
 */
public class ParserLessonTimeTest extends ParserElementTest {


    public ParserLessonTimeTest() {
        super(new ParserLessonTime(), "LessonTime.html");
    }

    @Test
    @Override
    public void checkElementResults() {
        checkName(elementResults,ElementName.LESSON_TIME);
        checkElementAttribute(elementResults, "number", "3");
        checkElementAttribute(elementResults, "start1", "08:30");
        checkElementAttribute(elementResults, "end1", "09:15");
        checkElementAttribute(elementResults, "start2", "09:20");
        checkElementAttribute(elementResults, "end2", "10:05");
    }

}