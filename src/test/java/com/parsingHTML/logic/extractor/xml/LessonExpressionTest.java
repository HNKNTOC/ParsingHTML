package com.parsingHTML.logic.extractor.xml;

import com.parsingHTML.logic.element.AttributeName;
import com.parsingHTML.logic.element.DayName;
import com.parsingHTML.logic.element.ElementName;
import com.parsingHTML.logic.element.NumeratorName;
import com.parsingHTML.logic.extractor.xml.XPathBuilder.XPathElement;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


/**
 * Created by Nikita on 19.10.2016.
 */
public class LessonExpressionTest {
    @Test
    public void addXPathElementInPath() throws Exception {
        XPathElement xPathLesson = new XPathElement(ElementName.LESSON);
        xPathLesson.addAttr(AttributeName.NUMERATOR, NumeratorName.EMPTY.getName());

        String xPathExpression = LessonExpression.createXPathForLesson(DayName.FRIDAY,
                xPathLesson);

        assertEquals(xPathExpression, "" +
                "schedule/university/group_lesson/day_lesson[@day-number='6']/lesson[@numerator='empty']");
    }

    @Test
    public void createXPathForTime() throws Exception {
        XPathElement xPathLesson = new XPathElement(ElementName.LESSON_TIME);
        xPathLesson.addAttr(AttributeName.NUMBER, 5);

        String xPathExpression = LessonExpression.createXPathForTime(DayName.FRIDAY,
                xPathLesson);

        assertEquals(xPathExpression, "" +
                "schedule/university/week_time/day_time[@day-time-number='2']/lesson_time[@number='5']");
    }

}