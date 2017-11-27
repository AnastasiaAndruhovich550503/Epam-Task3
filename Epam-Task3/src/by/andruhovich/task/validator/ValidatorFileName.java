package by.andruhovich.task.validator;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ValidatorFileName {
    private static final Logger LOGGER = LogManager.getLogger(ValidatorFileName.class);

    public boolean isRightFilename(String filename) {
        String extension;
        try {
            extension = filename.substring(filename.length() - 4, filename.length());
        }
        catch (IndexOutOfBoundsException e) {
            LOGGER.log(Level.ERROR, "Filename " + filename + " has no extension");
            return false;
        }
        return (".txt").compareTo(extension) == 0;
    }
}
