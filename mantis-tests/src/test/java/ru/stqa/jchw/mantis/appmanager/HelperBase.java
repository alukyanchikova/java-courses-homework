package ru.stqa.jchw.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.File;

public class HelperBase {

    protected ApplicationManager app;
    protected WebDriver wd;

    public HelperBase(ApplicationManager app) {
        this.app = app;
        this.wd = app.getDriver();
    }

    protected void clickOnForm(By locator) {
        wd.findElement(locator).click();
    }

    protected void type(By locator, String text) {
        clickOnForm(locator);
        if (text != null) {
            String existingText = wd.findElement(locator).getAttribute("value");
            if (! text.equals(existingText)){
                wd.findElement(locator).clear();
                wd.findElement(locator).sendKeys(text);
            }
        }
    }

    protected void attach(By locator, String pathToFile) {
        if (pathToFile != null) {
            wd.findElement(locator).sendKeys(new File(pathToFile).getAbsolutePath());
        }
    }
}
