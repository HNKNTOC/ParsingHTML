package com.parsingHTML.logic.parser.grouplesson;

import com.parsingHTML.logic.element.ElementName;
import com.parsingHTML.logic.element.NumeratorName;
import com.parsingHTML.logic.parser.ParserHTMLAbstract;
import com.parsingHTML.logic.parser.ParserHelper;
import com.parsingHTML.logic.parser.exception.ExceptionParser;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Arrays;

/**
 * Получает элемент Lesson из .html.
 */
public class ParserLesson extends ParserHTMLAbstract {
    private static final Logger LOGGER = LogManager.getLogger(ParserLesson.class);
    private static final String separator = "/-";
    /**
     * cssQuery - выбор номер пары.
     */
    private final static String cssQueryNumPara = ".num_para";
    /**
     * cssQuery - выбор дня.
     */
    public static final String cssQueryPara = ".para";
    /**
     * cssQuery - выбор имя учителя.
     */
    public static final String cssQueryNameTeacher = "input.fioprep";
    /**
     * Атрибут в котором ноходится TeacherNames.
     */
    private static final String AttributeTeacherNames = "value";

    public ParserLesson(ParserHTMLAbstract nextParser) {
        super(ElementName.LESSON.getName(), nextParser);
    }

    @Override
    protected Element processingElement(Element element) throws ExceptionParser {
        Element numParaElement = ParserHelper.selectElement(element, cssQueryNumPara, 0);
        String number = parsingNumber(numParaElement);
        String numerator = parsingNumerator(numParaElement);

        Element paraElement = ParserHelper.selectElement(element, cssQueryPara, 0);
        String[] split = divideString(paraElement);
        String nameTeacher = parsingNameTeacher(paraElement);

        String nameLesson = split[0];
        String descriptionLesson = split[1];

        return elementFactory.createLesson(number, nameLesson, descriptionLesson, nameTeacher, numerator);
    }

    @Override
    protected Elements selectElementProcessing(Element element) throws ExceptionParser {
        Elements elements = ParserHelper.selectElements(element, cssQueryPara);
        Elements returnElements = new Elements();
        for (Element selectElement : elements) {
            returnElements.add(selectElement.parent());
        }
        return returnElements;
    }

    /**
     * Делит строку на пополам по первому dr элементу.
     *
     * @param element элемент текст которого нужно разделить.
     * @return массив с разделёнными строками.
     */
    private String[] divideString(Element element) {
        String html = element.html();
        LOGGER.debug("divideString .html = " + html);
        String replace = html.replace("<br>", separator);
        element.html(replace);
        String[] split = element.text().split(separator);
        if (split.length != 2) {
            LOGGER.warn("divideString failed to divide string!! split.length = " + split.length);
            return new String[]{"", ""};
        }
        LOGGER.debug("divideString return " + Arrays.toString(split));
        return split;
    }

    /**
     * Получение NumeratorName.
     * Ищет строку из переменной numeratorTrue и numeratorFalse если находит то возвращает её.
     *
     * @param element элемент у которого нужно получить NumeratorName.
     * @return null если получить не удалось.
     */
    private String parsingNumerator(Element element) {
        String text = element.text();
        final String numerator = NumeratorName.NUMERATOR.getName();
        final String denominator = NumeratorName.DENOMINATOR.getName();
        LOGGER.debug("parsingNumerator text = " + text);
        if (text.contains(numerator)) {
            LOGGER.debug("parsingNumerator return " + numerator);
            return numerator;
        }

        if (text.contains(denominator)) {
            LOGGER.debug("parsingNumerator return " + denominator);
            return denominator;
        }
        LOGGER.debug("parsingNumerator return null");
        return NumeratorName.EMPTY.getName();
    }

    /**
     * Получение TeacherNames.
     * @param paraElement элемент у которого нужно получить TeacherNames.
     * @return TeacherNames из paraElement.
     * @throws ExceptionParser если не удалось найти TeacherNames.
     */
    private String parsingNameTeacher(Element paraElement) throws ExceptionParser {
        Elements nameTeacherElement = ParserHelper.selectElements(paraElement, cssQueryNameTeacher);
        String teacherNames = "";
        int size = nameTeacherElement.size();
        for (int i = 0; i < size; i++) {
            Element element = nameTeacherElement.get(i);
            String attr = element.attr(AttributeTeacherNames);
            if (i != size - 1) {
                attr = attr.concat(",");
            }
            teacherNames = teacherNames.concat(attr);
        }
        return teacherNames;
    }

    /**
     * Получение номера пары.
     *
     * @param element элемент у которого нужно получить номер.
     * @return null если получить не удалось.
     */
    private String parsingNumber(final Element element) throws ExceptionParser {
        String text = element.text();
        LOGGER.debug("parsingNumber txt = " + text);
        if (text.length() != 1) {
            String number = text.split(" ")[0];
            LOGGER.debug("parsingNumber return " + number);
            return number;
        }
        LOGGER.warn("parsingNumber failed! return null");
        return "null";
    }
}
