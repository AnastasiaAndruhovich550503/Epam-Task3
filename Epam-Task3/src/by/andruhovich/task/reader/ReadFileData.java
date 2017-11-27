package by.andruhovich.task.reader;

import by.andruhovich.task.validator.ValidatorFileName;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class ReadFileData {
    private String defaultFilename = "data\\data.txt";

    private static final Logger LOGGER = LogManager.getLogger(ReadFileData.class);

    public String readData(String filename, Locale locale) {
        StringBuilder stringBuilder = new StringBuilder();
        ValidatorFileName validatorFileName = new ValidatorFileName();
        Scanner scanner = null;

        if (!validatorFileName.isRightFilename(filename)) {
            LOGGER.log(Level.WARN, "File " + filename + " has wrong extension. Filename will be replaced " +
                    "with default name " + defaultFilename);
            filename = defaultFilename;
        }

        try {
            scanner = new Scanner(new File(filename));
            scanner.useLocale(locale);

            while (scanner.hasNext()) {
               stringBuilder.append(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            LOGGER.log(Level.FATAL, "File " + filename + "not found.");
            throw new RuntimeException();
        } finally {
            scanner.close();
        }

        return stringBuilder.toString();
    }
}
