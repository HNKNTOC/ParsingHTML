package com.parsingHTML.logic.file;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;

/**
 * Created by Nikita on 14.05.2016.
 */
public class FileManagerDefault implements FileManager {

    private File fileDirectory;
    private static final Logger LOGGER = LogManager.getLogger(FileManagerDefault.class);

    public FileManagerDefault() {
        this(new File("src\\main\\resources\\html\\test"));
    }

    public FileManagerDefault(File fileDirectory) {
        this.fileDirectory = fileDirectory;
        createDirectory(fileDirectory);
    }

    @Override
    public boolean setDirectory(String pathDirectory) {
        LOGGER.debug("setDirectory: "+pathDirectory);
        File fileDirectory = new File(pathDirectory);
        if (createDirectory(fileDirectory)) {
            this.fileDirectory = fileDirectory;
            LOGGER.debug("set successful: "+pathDirectory);
            return true;
        }
        LOGGER.debug("set not successful: "+pathDirectory);
        return false;
    }

    @Override
    public boolean createDirectory(File fileDirectory) {
        LOGGER.debug("CreateDirectory "+fileDirectory.getAbsolutePath());
        if(!fileDirectory.isDirectory()){
            LOGGER.warn("Not directory or does not exist");
            if(!fileDirectory.mkdirs()) {
                LOGGER.warn("Failed to create!");
                return false;
            }
        }
            LOGGER.debug("Create successful!");
            return true;
    }

    @Override
    public String getDirectory() {
        if (fileDirectory == null) return null;
        return fileDirectory.getAbsolutePath();
    }


    @Override
    public boolean createFile(String name) {
        File file = new File(fileDirectory.getAbsolutePath()+"\\"+name);
        LOGGER.debug("CreateFile "+file.getAbsolutePath());
        try {
            if(file.exists()){
                LOGGER.debug("File already exists!");
                return true;
            }else {
                if (!file.createNewFile()) {
                    LOGGER.debug("File created not successful!");
                    return false;
                }
                LOGGER.debug("File created successful!");
                return true;
            }
        } catch (IOException e) {
            LOGGER.debug(e);
            return false;
        }
    }

    @Override
    public File getFile(String name) {
        return new File(fileDirectory.getAbsolutePath()+"\\"+name);
    }

    @Override
    public File[] getAllFile() {
        return fileDirectory.listFiles();
    }

    @Override
    public boolean deleteFile(String name) {
        return new File(fileDirectory.getAbsolutePath()+"\\"+name).delete();
    }

    @Override
    public boolean deleteAllFile() {
        File[] allFile = getAllFile();
        for (File file : allFile) {
            if (file.delete()) {
                LOGGER.debug("Delete file "+file);
            }else {
                LOGGER.warn("Failed to remove file "+file);
            }
        }
        return true;
    }


}
