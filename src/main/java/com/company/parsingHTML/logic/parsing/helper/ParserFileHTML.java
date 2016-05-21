package com.company.parsingHTML.logic.parsing.helper;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;

import java.io.File;
import java.io.IOException;

/**
 * Парсит TagNode из File.
 */
public class ParserFileHTML implements TagNodeHelper<File> {
    private static final Logger LOGGER = LogManager.getLogger(ParserFileHTML.class);

    @Override
    public TagNode parsing(File fileHTML) {
        TagNode rootNode = toTagNode(fileHTML);
        if (rootNode == null) {
            LOGGER.warn("ToTagNode return null!");
            return null;
        } else {
            return rootNode;
        }
    }

    /**
     * Получение из File корневой TagNode.
     * @param file из коророго нужно получить TagNode.
     * @return TagNode из file.
     */
    private TagNode toTagNode(File file) {
        try {
            LOGGER.debug("ToTagNode file " + file.getAbsolutePath());
            return new HtmlCleaner().clean(file);
        } catch (IOException e) {
            LOGGER.warn(e);
            return null;
        }
    }

}
