package com.company.parsingHTML.logic.loader;

import com.company.parsingHTML.logic.file.FileManager;
import com.company.parsingHTML.logic.file.FileManagerDefault;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Стандартная реализация скачивания файла по URL.
 */
public class LoaderHTMLDefault implements LoaderHTML {

    private static final Logger logger = LogManager.getLogger(LoaderHTMLDefault.class);
    private FileManager fileManager;

    public LoaderHTMLDefault() {
        this(new FileManagerDefault());
    }

    public LoaderHTMLDefault(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    @Override
    public File loadHTML(String stringURL) {
        logger.info("Load... " + stringURL);
        URL url = null;
        File file = null;
        InputStream inputStream = null;
        try {
            url = new URL(stringURL);
            file = createFile(url.getHost());
            logger.debug("Write in file "+file.getName());
            inputStream = url.openConnection().getInputStream();
            writeInFile(file,inputStream);
            logger.debug("Return " + file.getAbsolutePath());
            return file;
        } catch (IOException e) {
            logger.warn(e.getMessage());
            return null;
        }finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    protected File createFile(String name){
        fileManager.createFile(name);
        return fileManager.getFile(name);
    }


    /**
     * Считывает всё из inputStream и записывает в файл.
     * @param file в который нужно записать.
     * @param inputStream из которого будет считыватся.
     */
    protected void writeInFile(File file,InputStream inputStream){
        logger.debug("WriteInFile "+file.getName());
        String s;
        BufferedReader bufferedReader = null;
        FileWriter fileWriter;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            fileWriter = new FileWriter(file);
            String txt = "";
            while((s=bufferedReader.readLine())!=null){
                txt = txt+s+"\n";
            }
            fileWriter.write(txt);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException e) {
                logger.warn(e);
            }
        }
    }

}
