package by.andruhovich.test;

import by.andruhovich.task.reader.ReadFileData;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

public class ReadFileDataTest {
    private String filename;
    private String notExistFilename = "dat.txt";
    private ReadFileData readFileData;
    private Locale locale;
    private static final Logger LOGGER = LogManager.getLogger(by.andruhovich.task.reader.ReadFileData.class);
    private String string;

    @BeforeSuite
    public void intialization() {
        readFileData = new ReadFileData();
        locale = new Locale("en", "en");
        filename = "data\\data.txt";

        FileWriter fileWriter = null;
        File file = new File(filename);

        string = new String("12 34 2 -5 4.6 7 -54   612 34 2 -5dsfg 4.6 7 -54 612 34 2,712 34 2ert -5 4.6 7 -54 6");

        try {
           fileWriter = new FileWriter(file);
           fileWriter.write("12 34 2 -5 4.6 7 -54   6\n");
           fileWriter.write("12 34 2 -5dsfg 4.6 7 -54 6\n");
           fileWriter.write("12 34 2,7\n");
           fileWriter.write("12 34 2ert -5 4.6 7 -54 6\n");

        } catch (IOException e) {
            LOGGER.log(Level.FATAL, e.getMessage());
        } finally {
            try {
                fileWriter.close();
            } catch (IOException e) {
                LOGGER.log(Level.FATAL, e.getMessage());
            }
        }
    }

    @BeforeGroups("Negative")
    public void negativeInitialization() {
        try {
            FileWriter fileWriter = new FileWriter(filename);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("");
            bufferedWriter.close();
        } catch (IOException e) {
           LOGGER.log(Level.FATAL, e.getMessage());
        }
    }

    @Test
    public void firstTestReadFileData() {
        Assert.assertEquals(readFileData.readData(filename, locale), string);
    }

    @Test (groups =  "Negative", expectedExceptions = RuntimeException.class)
    public void negativeTestReadFileData() {
        readFileData.readData(notExistFilename, locale);
    }

    @AfterSuite
    public void clearAll() {
       string = null;
    }
}
