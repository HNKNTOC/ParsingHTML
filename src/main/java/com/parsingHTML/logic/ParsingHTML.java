package com.parsingHTML.logic;

import com.parsingHTML.logic.parsing.ElementJsoupFactory;
import com.parsingHTML.logic.parsing.html.ParserHTMLAbstract;
import com.parsingHTML.logic.parsing.html.ParserHTMLFactory;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.helper.W3CDom;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

/**
 * Фасад управления ParsingHTML.
 */
public class ParsingHTML {
    private static final Logger LOGGER = LogManager.getLogger(ParsingHTML.class);
    private static ParserHTMLFactory parserHTMLFactory = new ParserHTMLFactory();
    private static ElementJsoupFactory elementFactory = new ElementJsoupFactory();

    public static void setParserHTMLFactory(ParserHTMLFactory parserHTMLFactory) {
        ParsingHTML.parserHTMLFactory = parserHTMLFactory;
    }

    public static void setElementFactory(ElementJsoupFactory elementFactory) {
        ParsingHTML.elementFactory = elementFactory;
    }

    public static ParserHTMLFactory getParserHTMLFactory() {
        return parserHTMLFactory;
    }

    public static ElementJsoupFactory getElementFactory() {
        return elementFactory;
    }

    public static Element parsingSchedule(File timeContent, File scheduleContent, String charsetName) throws IOException {
        Element schedule = elementFactory.createSchedule();
        schedule.appendChild(elementFactory.createParsingTime());

        Element university = elementFactory.createUniversity();
        university.appendChild(parsingWeekTime(timeContent, charsetName));
        university.appendChild(parsingGroupLesson(scheduleContent, charsetName));

        schedule.appendChild(university);
        return schedule;
    }

    /**
     * Спарсить содержание с помощью ParserWeekTime File.
     *
     * @param file        Файл с HTML.
     * @param charsetName установить характер содержимого файла.
     * @return получившийся при парсинге элемент.
     * @throws IOException если файл не может быть найден, или прочитан, или если charsetName является недопустимым.
     */
    public static Element parsingGroupLesson(File file, String charsetName) throws IOException {
        LOGGER.info("parsingGroupLesson file: " + file + " charsetName: " + charsetName);
        return parsing(file, charsetName, parserHTMLFactory.createParserGroupLesson());
    }

    /**
     * Спарсить содержание с помощью ParserGroupLesson File.
     *
     * @param file        Файл с HTML.
     * @param charsetName установить характер содержимого файла.
     * @return получившийся при парсинге элемент.
     * @throws IOException если файл не может быть найден, или прочитан, или если charsetName является недопустимым.
     */
    public static Element parsingWeekTime(File file, String charsetName) throws IOException {
        LOGGER.info("parsingWeekTime file: " + file + " charsetName: " + charsetName);
        return parsing(file, charsetName, parserHTMLFactory.createParserWeekTime());
    }

    /**
     * Спарсить содержание File.
     *
     * @param file        Файл с HTML.
     * @param charsetName установить характер содержимого файла.
     * @param parser      ParserHTMLAbstract который будет парсить.
     * @return получившийся при парсинге элемент.
     * @throws IOException если файл не может быть найден, или прочитан, или если charsetName является недопустимым.
     */
    public static Element parsing(File file, String charsetName, ParserHTMLAbstract parser) throws IOException {
        LOGGER.info("parsing file: " + file + " charsetName: " + charsetName + " parser: = " + parser);
        Document document = Jsoup.parse(file, charsetName);
        return parser.parsing(document);
    }

    /**
     * Трансформирует org.jsoup.nodes.Element in org.w3c.dom.Document.
     *
     * @param element Element который нужно трансформировать в Document.
     * @return полученный при трансформации Document.
     */
    public static org.w3c.dom.Document transformation(Element element) {
        LOGGER.info("transformation element " + element);

        Document document = new Document(element.baseUri());
        document.appendChild(element);

        org.w3c.dom.Document returnDOC = createDOC();
        new W3CDom().convert(document, returnDOC);
        return returnDOC;
    }

    private static org.w3c.dom.Document createDOC() {
        LOGGER.info("createDOC");
        try {
            return DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        return null;
    }

}
