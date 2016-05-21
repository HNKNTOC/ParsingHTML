package com.company.parsingHTML.logic.parsing.tag;

import com.company.parsingHTML.logic.schedule.Schedule;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.htmlcleaner.ContentNode;
import org.htmlcleaner.TagNode;

/**
 * Парсит тег table в котором находятся время для каждой пары.
 */
public class ParserDayTime extends ParserTagAbstract {
    private static final Logger LOGGER = LogManager.getLogger(ParserDayTime.class);

    /**
     * Разделитель для time.
     */
    private static final String replacement = "/";

    public ParserDayTime() {
        super(new String[]{"table"});
    }

    @Override
    public void parsing(TagNode tagNode, Schedule schedule) {
        LOGGER.debug("parsing tagNode= "+tagNode.toString()+" schedule = "+schedule.toString());
        TagNode[] trs = tagNode.getElementsByName("tr", true);
        for (TagNode tr : trs) {
            TagNode n_para = tr.getElementsByAttValue("class", "n_para", true, true)[0];
            TagNode[] times = tr.getElementsByAttValue("class", "time", true, true);
            newLessonTime(n_para,times);
        }
    }

    /**
     * Обработка n_para и times
     * @param n_para
     * @param times
     */
    private void newLessonTime(TagNode n_para, TagNode[] times) {
        String time1 = timeHandler(times[0]);
        String time2 = timeHandler(times[1]);
        System.out.println(time1+" == "+time2);
        //new LessonTime(Integer.parseInt(n_para.getText().toString()),);
    }

    private String timeHandler(TagNode time) {
        TagNode[] brs = time.getElementsByName("br",true);
        TagNode childSeparator = new TagNode("h1");
        childSeparator.addChild(new ContentNode(replacement));
        brs[0].addChild(childSeparator);
        return time.getText().toString().replace("&ndash;", replacement);
    }
}
