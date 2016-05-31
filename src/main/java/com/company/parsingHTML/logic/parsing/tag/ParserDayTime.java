package com.company.parsingHTML.logic.parsing.tag;

import com.company.parsingHTML.logic.schedule.DayTime;
import com.company.parsingHTML.logic.schedule.LessonTime;
import com.company.parsingHTML.logic.schedule.Schedule;
import com.company.parsingHTML.logic.schedule.WeekTime;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.htmlcleaner.ContentNode;
import org.htmlcleaner.TagNode;

import java.util.Arrays;

/**
 * Парсит тег table в котором находятся время для каждой пары.
 */
public class ParserDayTime extends ParserTagAbstract {
    private static final Logger LOGGER = LogManager.getLogger(ParserDayTime.class);

    /**
     * Разделитель для time.
     */
    private static final String replacement = "=";

    private Schedule schedule;
    private DayTime monday;
    private DayTime saturday;

    public ParserDayTime() {
        super(new String[]{"table"});
        monday = new DayTime(DayTime.MONDAY);
        saturday = new DayTime(DayTime.SATURDAY);
        LOGGER.info("Create " + toString());
    }

    @Override
    public void parsing(TagNode tagNode, Schedule schedule) {
        LOGGER.debug("parsing tagNode= " + tagNode.toString() + " schedule = " + schedule.toString());
        this.schedule = schedule;
        TagNode[] trs = tagNode.getElementsByName("tr", true);
        LOGGER.debug("trs = " + Arrays.toString(trs));
        for (TagNode tr : trs) {

            newTr(tr);
        }
        pytDay();
        for (DayTime dayTime : schedule.getWeekTime().getDayTimeList()) {
            System.out.println(dayTime.toXML());
        }
    }

    private void newTr(TagNode tr) {
        LOGGER.debug("newTr tr = "+tr.getText());
        TagNode[] elementsN_para = tr.getElementsByAttValue("class", "n_para", true, true);
        if (elementsN_para.length == 0) return;
        TagNode n_para = elementsN_para[0];
        TagNode[] times = tr.getElementsByAttValue("class", "time", true, true);
        newLessonTime(n_para, times);
    }

    /**
     * Обработка n_para и times
     *
     * @param n_para
     * @param times
     */
    private void newLessonTime(TagNode n_para, TagNode[] times) {
        LOGGER.debug("newLessonTime n_para = "+n_para.getText()+" times = "+ Arrays.toString(times));
        String[] time0 = timeHandler(times[0]);
        String[] time1 = timeHandler(times[1]);
        String substring = n_para.getText().toString().substring(0, 1);
        LessonTime lessonTime0 = createLessonTime(Integer.parseInt(substring), time0);
        LessonTime lessonTime1 = createLessonTime(Integer.parseInt(substring), time1);
        monday.addLessonTime(lessonTime0);
        saturday.addLessonTime(lessonTime1);
    }

    /**
     * Создаёт LessonTime.
     * @param times моссив с временем.
     * @param number номер пары.
     * @return null если не удолось создать LessonTime.
     */
    private LessonTime createLessonTime(int number, String[] times){
        LOGGER.debug("createLessonTime number = "+number +
                " times = "+ Arrays.toString(times));
        if (times.length != 4) {
            LOGGER.warn("times.length must be equal to 4 length=" + times.length);
            return null;
        }
        LessonTime lessonTimeMonday = new LessonTime(
                number,times[0], times[1], times[2], times[3]);
        LOGGER.info("return "+lessonTimeMonday+toString());
        return lessonTimeMonday;
    }


    /**
     * Форматирует строку в более удобный вид.
     *
     * @param time
     * @return
     */
    private String[] timeHandler(TagNode time) {
        LOGGER.debug("timeHandler time = "+time.getName());
        addSeparator(time.getElementsByName("br", true));
        LOGGER.debug("after addSeparator = "+time.getText());
        String replace = time.getText().toString().replace("&ndash;", replacement);
        LOGGER.debug("after replace = "+replace);
        return  replace.split(replacement);
    }



    /**
     * Добавляет в тег br разделитель.
     * @param brs лист с br тегами в которых нужно добавить зазделители.
     */
    private void addSeparator(TagNode[] brs) {
        if (brs.length != 1) {
            LOGGER.warn("brs.length must be equal to 1 length=" + brs.length);
            return;
        }
        for (TagNode br:brs) {
            TagNode childSeparator = new TagNode("h1");
            childSeparator.addChild(new ContentNode(replacement));
            br.addChild(childSeparator);
        }
    }

    /**
     * Добовляет день
     */
    private void pytDay() {
        WeekTime weekTime = schedule.getWeekTime();
        weekTime.addDayTime(monday);
        weekTime.addDayTime(saturday);

        weekTime.addDayTime(new DayTime(DayTime.THURSDAY, monday));
        weekTime.addDayTime(new DayTime(DayTime.WEDNESDAY, monday));
        weekTime.addDayTime(new DayTime(DayTime.THURSDAY, monday));
        weekTime.addDayTime(new DayTime(DayTime.FRIDAY, monday));
    }

}
