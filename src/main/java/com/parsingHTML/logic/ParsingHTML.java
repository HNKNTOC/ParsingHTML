package com.parsingHTML.logic;

import com.parsingHTML.logic.element.ElementJsoupFactory;
import com.parsingHTML.logic.parser.ParserHTMLAbstract;
import com.parsingHTML.logic.parser.ParserHTMLFactory;
import com.parsingHTML.logic.parser.grouplesson.ParserGroupLesson;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.helper.W3CDom;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Фасад управления ParsingHTML.
 */
public class ParsingHTML {
    private static final Logger LOGGER = LogManager.getLogger(ParsingHTML.class);
    private static ParserHTMLFactory parserHTMLFactory = new ParserHTMLFactory();
    private static ElementJsoupFactory elementFactory = new ElementJsoupFactory();

    public static ParserHTMLFactory getParserHTMLFactory() {
        return parserHTMLFactory;
    }

    public static void setParserHTMLFactory(ParserHTMLFactory parserHTMLFactory) {
        ParsingHTML.parserHTMLFactory = parserHTMLFactory;
    }

    public static ElementJsoupFactory getElementFactory() {
        return elementFactory;
    }

    public static void setElementFactory(ElementJsoupFactory elementFactory) {
        ParsingHTML.elementFactory = elementFactory;
    }

    /**
     * Парсинг расписания из InputStream.
     *
     * @param timeContent     Element с временем.
     * @param scheduleContent Element с расписание.
     * @param charsetName     установить характер содержимого файла.
     * @return XML расписание.
     */
    public static Element parsingSchedule(InputStream timeContent, InputStream scheduleContent, String charsetName) throws IOException {
        Element schedule = elementFactory.createSchedule();
        schedule.appendChild(elementFactory.createParsingTime());

        Element university = elementFactory.createUniversity();
        university.appendChild(parsingWeekTime(timeContent, charsetName));
        university.appendChild(parsingGroupLesson(scheduleContent, charsetName));

        schedule.appendChild(university);
        return schedule;
    }

    /**
     * Парсинг расписания из Element.
     *
     * @param timeContent     Element с временем.
     * @param scheduleContent Element с расписание.
     * @param charsetName     установить характер содержимого файла.
     * @return XML расписание.
     */
    public static Element parsingSchedule(Element timeContent, Element scheduleContent, String charsetName) {
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
     * @param inputStream Файл с HTML.
     * @param charsetName установить характер содержимого файла.
     * @return получившийся при парсинге элемент.
     * @throws IOException если файл не может быть найден, или прочитан, или если charsetName является недопустимым.
     */
    public static Element parsingGroupLesson(InputStream inputStream, String charsetName) throws IOException {
        LOGGER.info("parsingGroupLesson inputStream: " + inputStream + " charsetName: " + charsetName);
        return parsing(inputStream, charsetName, parserHTMLFactory.createParserGroupLesson());
    }

    /**
     * Спарсить содержание с помощью ParserWeekTime File.
     *
     * @param element     Element с HTML.
     * @param charsetName установить характер содержимого файла.
     * @return получившийся при парсинге элемент.
     */
    public static Element parsingGroupLesson(Element element, String charsetName) {
        LOGGER.info("parsingGroupLesson inputStream: " + element + " charsetName: " + charsetName);
        return parsing(element, charsetName, parserHTMLFactory.createParserGroupLesson());
    }

    /**
     * Спарсить содержание с помощью ParserGroupLesson File.
     *
     * @param inputStream Файл с HTML.
     * @param charsetName установить характер содержимого файла.
     * @return получившийся при парсинге элемент.
     * @throws IOException если файл не может быть найден, или прочитан, или если charsetName является недопустимым.
     */
    public static Element parsingWeekTime(InputStream inputStream, String charsetName) throws IOException {
        LOGGER.info("parsingWeekTime inputStream: " + inputStream + " charsetName: " + charsetName);
        return parsing(inputStream, charsetName, parserHTMLFactory.createParserWeekTime());
    }

    /**
     * Спарсить содержание с помощью ParserGroupLesson File.
     *
     * @param element     Element с HTML.
     * @param charsetName установить характер содержимого файла.
     * @return получившийся при парсинге элемент.
     */
    public static Element parsingWeekTime(Element element, String charsetName) {
        LOGGER.info("parsingWeekTime inputStream: " + element + " charsetName: " + charsetName);
        return parsing(element, charsetName, parserHTMLFactory.createParserWeekTime());
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
    public static Element parsing(InputStream file, String charsetName, ParserHTMLAbstract parser) throws IOException {
        LOGGER.info("parsing file: " + file + " charsetName: " + charsetName + " parser: = " + parser);
        Document document = Jsoup.parse(file, charsetName, "");
        return parser.parsing(document);
    }

    /**
     * Спарсить содержание File.
     *
     * @param element     Файл с HTML.
     * @param charsetName установить характер содержимого файла.
     * @param parser      ParserHTMLAbstract который будет парсить.
     * @return получившийся при парсинге элемент.
     */
    public static Element parsing(Element element, String charsetName, ParserHTMLAbstract parser) {
        LOGGER.info("parsing file: " + element + " charsetName: " + charsetName + " parser: = " + parser);
        return parser.parsing(element);
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

    /**
     * Проверка является содержит ли строка описание.
     *
     * @param schedules строка которую нужно проверить.
     * @return true если содержит.
     */
    public static boolean checkSchedules(String schedules) {
        int size = Jsoup.parse(schedules).select(ParserGroupLesson.cssQueryTableDay).size();
        return size > 0;
    }


}
