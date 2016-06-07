package com.company.parsingHTML.logic.parsing.tag.grouplesson;

import com.company.parsingHTML.logic.parsing.tag.ParserHTMLAbstract;
import com.company.parsingHTML.logic.xml.ElementXML;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Element;

/**
 * Created by Nikita on 31.05.2016.
 */
public class ParserGroupLesson extends ParserHTMLAbstract {
    private static final Logger LOGGER = LogManager.getLogger(ParserGroupLesson.class);

    /**
     * Создание ParserHTMLAbstract
     *
     */
    public ParserGroupLesson() {
        super(new String[]{"table"});
    }

    @Override
    public ElementXML parsing(Element element) {
        LOGGER.info("parsing Tag name = "+element.nodeName());
        return null;
    }

}
