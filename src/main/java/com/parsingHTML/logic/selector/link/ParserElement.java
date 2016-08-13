package com.parsingHTML.logic.selector.link;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * Парсит {@link Element} из URL.
 */
public class ParserElement {

    /**
     * Парсит
     *
     * @param url
     * @param timeoutMillis
     * @param elementSelectorCSS для нахождения {@link Element}.
     * @return
     * @throws IOException
     */
    public static List<Element> parsingButton(URL url, int timeoutMillis, String elementSelectorCSS) throws IOException {
        Document parse = Jsoup.parse(url, timeoutMillis);
        return parse.select(elementSelectorCSS);
    }

}
