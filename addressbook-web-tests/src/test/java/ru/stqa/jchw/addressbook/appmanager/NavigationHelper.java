package ru.stqa.jchw.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

public class NavigationHelper extends HelperBase{

    public NavigationHelper(FirefoxDriver wd) {
        super(wd);
    }

    public void gotoGroupPage() {
        clickOnForm(By.linkText("groups"));
    }

    public void gotoHomePage() {
        clickOnForm(By.linkText("home"));
    }
}