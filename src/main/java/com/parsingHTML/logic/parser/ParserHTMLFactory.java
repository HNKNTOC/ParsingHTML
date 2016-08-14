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

    public ParserHTMLAbstract createParserDayTime() {
        return new ParserDayTime();
    }

    public ParserHTMLAbstract createParserLessonTime() {
        return new ParserLessonTime();
    }

    public ParserHTMLAbstract createParserWeekTime() {
        return new ParserWeekTime();
    }


    public ParserHTMLAbstract createParserLesson() {
        return new ParserLesson();
    }

    public ParserHTMLAbstract createParserGroupLesson() {
        return new ParserGroupLesson();
    }

    public ParserHTMLAbstract createParserDayLesson() {
        return new ParserDayLesson();
    }

}
