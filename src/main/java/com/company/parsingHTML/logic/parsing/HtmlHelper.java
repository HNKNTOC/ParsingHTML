package com.company.parsingHTML.logic.parsing;

import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nikita on 08.05.2016.
 */
public class HtmlHelper {
    TagNode rootNode;

    //Конструктор
    public HtmlHelper(File fileXML) throws IOException {
        //Создаём объект HtmlCleaner
        HtmlCleaner cleaner = new HtmlCleaner();
        //Загружаем html код сайта
        rootNode = cleaner.clean(fileXML);
    }

    public List<TagNode> getLinksByClass(String CSSClassname, String nameElements) {
        List<TagNode> linkList = new ArrayList<TagNode>();

        //Выбираем все ссылки
        TagNode linkElements[] = rootNode.getElementsByName(nameElements, true);
        for (int i = 0; linkElements != null && i < linkElements.length; i++) {
            //получаем атрибут по имени
            String classType = linkElements[i].getAttributeByName("class");
            //если атрибут есть и он эквивалентен искомому, то добавляем в список
            if (classType != null && classType.equals(CSSClassname)) {
                linkList.add(linkElements[i]);
            }
        }

        return linkList;
    }
}
