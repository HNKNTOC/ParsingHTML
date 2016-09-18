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

    public static ParserHTMLAbstract createParserDayTime() {
        return new ParserDayTime();
    }

    public static ParserHTMLAbstract createParserLessonTime() {
        return new ParserLessonTime();
    }

    public static ParserHTMLAbstract createParserWeekTime() {
        return new ParserWeekTime(new ParserDayTime());
    }


    public static ParserHTMLAbstract createParserLesson() {
        return new ParserLesson();
    }

    public static ParserHTMLAbstract createParserGroupLesson() {
        return new ParserGroupLesson();
    }

    public static ParserHTMLAbstract createParserDayLesson() {
        return new ParserDayLesson();
    }

}
