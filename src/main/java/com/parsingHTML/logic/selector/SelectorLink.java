package com.parsingHTML.logic.selector;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * Селектор нужен для выбора расписания.
 * 1) Получает Button из {@link SelectorLink#startURL}.
 * 2) Передаёт список Button {@link SelectorLink#parsingButtonNames()}
 * 3) Переход по кнопке {@link SelectorLink#goButton(int)}
 * 4) Получение текущего URL.
 */
public class SelectorLink {
    private static final Logger LOGGER = LogManager.getLogger(SelectorLink.class);
    /**
     * Стартовая URL.
     */
    private final URL startURL;
    /**
     * URL который сейчас обрабатывается.
     */
    private URL thisURL;
    /**
     * Время на ожидания загрузки URL.
     */
    private int timeoutMillis = 20000;
    /**
     * Количество переходов.
     */
    private int move = 0;
    /**
     * cssQuery для поиска Button.
     */
    private String elementSelectorCSS = "table.zv td a";
    /**
     * Спарсеные Button.
     */
    private Elements buttonSelect = new Elements();

    /**
     * Создание SelectorLink.
     *
     * @param startURL URL c которого начнётся парсинг.
     */
    public SelectorLink(URL startURL) {
        this.startURL = startURL;
        this.thisURL = startURL;
        LOGGER.debug("SelectorLink create " + this.toString());
    }

    public SelectorLink() throws MalformedURLException {
        this(new URL("http://rasp.bukep.ru/Default.aspx?tr=1"));
    }

    public String getElementSelectorCSS() {
        return elementSelectorCSS;
    }

    public void setElementSelectorCSS(String elementSelectorCSS) {
        this.elementSelectorCSS = elementSelectorCSS;
    }

    public int getTimeoutMillis() {
        return timeoutMillis;
    }

    public void setTimeoutMillis(int timeoutMillis) {
        this.timeoutMillis = timeoutMillis;
    }

    /**
     * Получить button который есть на {@link SelectorLink#thisURL}.
     */
    public ArrayList<String> parsingButtonNames() throws IOException {
        Document parse = Jsoup.parse(thisURL, timeoutMillis);
        buttonSelect = parse.select(elementSelectorCSS);
        ArrayList<String> buttonNames = new ArrayList<>();
        for (Element element : buttonSelect) {
            buttonNames.add(element.text());
        }
        LOGGER.debug("parsingButtonNames return " + buttonNames.toString());
        return buttonNames;
    }

    /**
     * Перейти по кнопке.
     *
     * @param index Index кнопки из списка полученного с помощью {@link SelectorLink#parsingButtonNames()}
     */
    public boolean goButton(int index) throws MalformedURLException {
        LOGGER.debug("goButton " + index);
        int size = buttonSelect.size();
        if (index < size) {
            Element element = buttonSelect.get(index);
            if (move == 0) {
                thisURL = new URL(moveButtonOne(element));
                move++;
                return true;
            }
            if (move == 1) {
                thisURL = new URL(moveButtonTwo(element));
                move++;
                return true;
            }
            LOGGER.warn("can't go link more than 2 times. move = " + move);
            return false;
        } else {
            LOGGER.warn("Failed get Element - index<buttonSelect.size() size = " + size);
            return false;
        }
    }

    private String moveButtonOne(Element element) {
        LOGGER.debug("Move " + element.text());
        String id = element.id().replace("ctl00_head_", "");
        String url = "http://rasp.bukep.ru/Default.aspx?tr=s&f=" + id;
        LOGGER.debug("return " + url);
        return url;
    }

    private String moveButtonTwo(Element element) {
        LOGGER.debug("Move " + element.text());
        String id = element.id().replace("ctl00_head_", "");
        String url = thisURL.toString().replace("tr=s", "tr=k");
        try {
            id = URLEncoder.encode(id, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        url += "&s=" + id;
        LOGGER.debug("return " + url);
        return url;
    }

}
