package com.parsingHTML.logic.parsing.tag;

import com.parsingHTML.logic.parsing.tag.daytime.ParserDayTime;
import com.parsingHTML.logic.parsing.tag.daytime.ParserLessonTime;
import com.parsingHTML.logic.parsing.tag.daytime.ParserWeekTime;
import com.parsingHTML.logic.parsing.tag.grouplesson.ParserDayLesson;
import com.parsingHTML.logic.parsing.tag.grouplesson.ParserGroupLesson;
import com.parsingHTML.logic.parsing.tag.grouplesson.ParserLesson;

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
