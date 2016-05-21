package com.company.parsingHTML.logic;


import com.company.parsingHTML.logic.file.FileManagerDefault;
import com.company.parsingHTML.logic.loader.LoaderHTML;
import com.company.parsingHTML.logic.loader.LoaderHTMLDefault;
import com.company.parsingHTML.logic.parsing.helper.ParserFileHTML;
import com.company.parsingHTML.logic.parsing.tag.ParserDayTime;
import com.company.parsingHTML.logic.parsing.tag.ParserRoot;
import com.company.parsingHTML.logic.parsing.tag.ParserTagAbstract;
import com.company.parsingHTML.logic.schedule.Schedule;


import java.io.File;
import java.util.Date;

/**
 * Created by Nikita on 03.05.2016.
 */
public class Main {
    public static void main(String[] args) {
        //loadHTML("http://rasp.bukep.ru/");
        File file = getFile("rasp.bukep.ru.html");
        ParserFileHTML parserFileHTML = new ParserFileHTML();

        Schedule schedule = new Schedule(new Date().toString(), "БУКЭП");
        ParserTagAbstract parserRoot = new ParserRoot();
        parserRoot.getObservableParing().addObserver(new ParserDayTime());
        
        parserRoot.parsing(parserFileHTML.parsing(file),schedule);
    }


    public static void testParsing(File fileXML){

    }

    public static File getFile(String name){
        return new FileManagerDefault().getFile(name);
    }

    /**
     * Загрузка html
     * @param stringURL
     */
    public static void loadHTML(String stringURL){
        LoaderHTML loaderHTMLDefault = new LoaderHTMLDefault();
        File file = loaderHTMLDefault.loadHTML("http://rasp.bukep.ru/");
    }

}
