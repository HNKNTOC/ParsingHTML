package com.parsingHTML.logic.parser;

import com.parsingHTML.logic.parser.daytime.ParserDayTime;
import com.parsingHTML.logic.parser.daytime.ParserLessonTime;
import com.parsingHTML.logic.parser.daytime.ParserWeekTime;
import com.parsingHTML.logic.parser.grouplesson.ParserDayLesson;
import com.parsingHTML.logic.parser.grouplesson.ParserGroupLesson;
import com.parsingHTML.logic.parser.grouplesson.ParserLesson;

/**
 * Фабрика создающая Parser унаследовванный от ParserHTMLAbstract.
 */
public class ParserHTMLFactory {

    public static ParserWeekTime createParserWeekTime() {
        return new ParserWeekTime(new ParserDayTime(null));
    }

    public static ParserHTMLAbstract createParserDayTime() {
        return new ParserDayTime(null);
    }

    public static ParserLessonTime createParserLessonTime() {
        return new ParserLessonTime(null);
    }


    public static ParserLesson createParserLesson() {
        return new ParserLesson();
    }

    public static ParserGroupLesson createParserGroupLesson() {
        return new ParserGroupLesson(null);
    }

    public static ParserDayLesson createParserDayLesson() {
        return new ParserDayLesson();
    }

}
