package by.andruhovich.test;

import by.andruhovich.task.validator.ValidatorFileName;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class ValidatorFileNameTest {
    private ValidatorFileName validatorFileName;

    @BeforeSuite
    public void fieldInitialization() {
        validatorFileName = new ValidatorFileName();
    }

    @Test()
    public void firstTestIsRightFilename() {
        Assert.assertTrue(validatorFileName.isRightFilename("files.txt"));
    }

    @Test()
    public void secondTestIsRightFilename() {
        Assert.assertFalse(validatorFileName.isRightFilename("qw.txt4"));
    }
    @Test()
    public void thirdTestIsRightFilename() {
        Assert.assertFalse(validatorFileName.isRightFilename(""));
    }

    @AfterSuite
    public void clearAll() {
        validatorFileName = null;
    }
}
