package com.parsingHTML.logic.file;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

/**
 * Created by Nikita on 14.05.2016.
 */
public class FileManagerTest {

    private FileManager fileManager = new FileManagerDefault();
    private static final Logger LOGGER = LogManager.getLogger(FileManagerTest.class);
    private static final String defaultDirectory = "src\\main\\resources\\html\\test";
    private static File newPathDirectory;
    private static final File fileDefault = new File("Test.txt");

    @Before
    public void setUp() throws Exception {
        if (!fileManager.setDirectory(defaultDirectory)) {
            LOGGER.warn("Failed set defaultDirectory!!");
        }
        newPathDirectory = new File("src\\main\\resources\\html\\test2");
    }

    /**
     * Удаляет все папки в src\main\resources\html.
     * @throws Exception
     */
    @After
    public void setDown() throws Exception {
        File[] files = new File(defaultDirectory).listFiles();
        if (files == null) return;
        for (File file : files) {
            if (file.delete()) {
                LOGGER.debug("Delete file "+file);
            }else {
                LOGGER.warn("Failed to remove file "+file);
            }
        }

    }

    @Test
    public void defaultSetting(){
        String directory = fileManager.getDirectory();
        assertNotNull(directory);
        assertTrue(new File(directory).exists());
    }



    @Test
    public void setAndGetDirectory() throws Exception {
        assertTrue(fileManager.getDirectory().equals(new File(defaultDirectory).getAbsolutePath()));
        assertTrue(fileManager.setDirectory(newPathDirectory.getAbsolutePath()));
        assertTrue(fileManager.getDirectory().equals(newPathDirectory.getAbsolutePath()));
    }

    @Test
    public void createDirectory() throws Exception {
        assertTrue(fileManager.createDirectory(newPathDirectory.getAbsoluteFile()));
        assertTrue(newPathDirectory.exists());
    }


    @Test
    public void createAndGetFile() throws Exception {
        assertTrue(fileManager.createFile(fileDefault.getName()));
        File file = new File(fileManager.getDirectory()+"\\"+fileDefault.getName());
        assertTrue(file.exists());
        String absolutePath = fileManager.getFile(fileDefault.getName()).getAbsolutePath();
        assertTrue(file.getAbsolutePath().equals(absolutePath));
    }

    @Test
    public void getAllFile() throws Exception {
        assertTrue(fileManager.getAllFile().length==0);
        fileManager.createFile("Text0.txt");
        fileManager.createFile("Text1.txt");
        assertTrue(fileManager.getAllFile().length==2);
        File[] allFile = fileManager.getAllFile();
        assertTrue(allFile[0].getName().equals("Text0.txt"));
        assertTrue(allFile[1].getName().equals("Text1.txt"));

    }

    @Test
    public void deleteFile() throws Exception {
        String name0 = "Text0.txt";
        String name1 = "Text1.txt";
        assertTrue(fileManager.createFile(name0));
        assertTrue(fileManager.createFile(name1));
        assertTrue(fileManager.deleteFile(name0));
        assertFalse(fileManager.deleteFile("Text2.txt"));
        assertTrue(fileManager.getAllFile().length==1);
    }

    @Test
    public void deleteAllFile() throws Exception {
        String name0 = "Text0.txt";
        String name1 = "Text0.txt";
        assertTrue(fileManager.createFile(name0));
        assertTrue(fileManager.createFile(name1));
        assertTrue(fileManager.deleteAllFile());
        assertTrue(fileManager.getAllFile().length==0);
    }

}