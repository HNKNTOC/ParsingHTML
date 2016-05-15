package com.company.parsingHTML.logic;


import com.company.parsingHTML.logic.file.FileManagerDefault;
import com.company.parsingHTML.logic.loader.LoaderHTML;
import com.company.parsingHTML.logic.loader.LoaderHTMLDefault;
import org.htmlcleaner.ContentNode;
import org.htmlcleaner.TagNode;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * Created by Nikita on 03.05.2016.
 */
public class Main {
    public static void main(String[] args) {
        File file = getFile("rasp.bukep.ru");
        System.out.println(file.getAbsolutePath());
    }


    public static void testParsing(File fileXML){
        /*System.out.println(fileXML.getAbsoluteFile());
        try {
            HtmlHelper htmlHelper = new HtmlHelper(fileXML);
            List<TagNode> listTag = htmlHelper.getLinksByClass("knock", "table");
            System.out.println("ListTag size="+listTag.size());
            TagNode tagNode1 = listTag.get(0);

            for (TagNode tagNode : tagNode1.getElementsByName("tr",true)) {
                for (TagNode td : tagNode.getElementsByName("td", true)) {
                    if (Objects.equals(td.getAttributeByName("class"), "n_para")) {
                        System.out.println("      ====="+td.getText()+"===");
                    }
                    if (Objects.equals(td.getAttributeByName("class"), "time")) {
                        TagNode child = new TagNode("h1");
                        child.addChild(new ContentNode("     "));
                        td.getElementsByName("br",true)[0].addChild(child);
                        String text = td.getText().toString();
                        text = text.replace("&ndash;","-");
                        System.out.println(text);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }*/
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
