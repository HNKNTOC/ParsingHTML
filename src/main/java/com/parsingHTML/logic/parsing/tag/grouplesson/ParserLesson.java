package com.parsingHTML.logic.parsing.tag.grouplesson;

import com.parsingHTML.logic.parsing.tag.ParserHTMLAbstract;
import com.parsingHTML.logic.xml.ElementXML;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Element;

/**
 * Created by Nikita on 08.06.2016.
 */
public class ParserLesson extends ParserHTMLAbstract {
    private static final Logger LOGGER = LogManager.getLogger(ParserLesson.class);
    @Override
    public ElementXML parsing(Element element) {
        LOGGER.info("parsing Tag name = " + element.nodeName());
        ElementXML lesson = new ElementXML("lesson");
        Element sibling = element.nextElementSibling();

        String number = parsingNumber(element);
        String numerator = parsingNumerator(element);

        String[] split = divideString(sibling);

        String nameLesson = split[0];
        String descriptionLesson = split[1];

        if(numerator==null){
            return createLesson(number,nameLesson,descriptionLesson,"Name Teacher");
        }else {
            return createLesson(number,nameLesson,descriptionLesson,"Name Teacher",numerator);
        }

    }

    /**
     * Делит строку на попалам по первому dr элементу.
     * @param sibling елемент текст которого нужно разделить.
     * @return моссив с разделёнными строками.
     */
    private String[] divideString(Element sibling) {
        Element element1 = sibling.children().get(0).children().get(0);
        element1.appendText("-");
        String[] split = sibling.text().split("-");
        if(split.length!=2){
            LOGGER.warn("divideString failed to divide string = "+sibling.text());
            return new String[]{"",""};
        }
        return split;
    }

    /**
     * Получение Numerator.
     * @param element элемент у которого нужно получить Numerator.
     * @return null если получить не удолось.
     */
    private String parsingNumerator(Element element) {
        String text = element.text();
        if(text.length()!=1){
            return text.split(" ")[1];
        }
        return null;
    }

    /**
     * Получение номера.
     * @param element элемент у которого нужно получить номер.
     * @return null если получить не удолось.
     */
    private String parsingNumber(Element element) {
        String text = element.text();
        if(text.length()!=1){
            return text.split(" ")[0];
        }
        return text;
    }

    private ElementXML createLesson(String number, String lessonName, String audience, String teacher){
        ElementXML lesson = new ElementXML("lesson");
        lesson.addAttribute("number", number);
        lesson.addAttribute("lessonName", lessonName);
        lesson.addAttribute("audience", audience);
        lesson.addAttribute("teacher", teacher);
        return lesson;
    }

    private ElementXML createLesson(String number, String lessonName, String audience, String teacher,String numerator){
        ElementXML lesson = createLesson(number,lessonName,audience,teacher);
        lesson.addAttribute("numerator", numerator);
        return lesson;
    }

}
