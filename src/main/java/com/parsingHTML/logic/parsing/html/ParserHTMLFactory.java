package com.parsingHTML.logic.parsing.html;

import com.parsingHTML.logic.parsing.html.daytime.ParserDayTime;
import com.parsingHTML.logic.parsing.html.daytime.ParserLessonTime;
import com.parsingHTML.logic.parsing.html.daytime.ParserWeekTime;
import com.parsingHTML.logic.parsing.html.grouplesson.ParserDayLesson;
import com.parsingHTML.logic.parsing.html.grouplesson.ParserGroupLesson;
import com.parsingHTML.logic.parsing.html.grouplesson.ParserLesson;

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
