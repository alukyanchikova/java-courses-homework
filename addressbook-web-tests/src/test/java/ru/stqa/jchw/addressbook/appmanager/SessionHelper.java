package ru.stqa.jchw.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SessionHelper extends HelperBase{

    public SessionHelper(WebDriver wd) {
        super(wd);
    }

    public void login(String username, String password) {
        fillFormWithText(By.name("user"), username);
        fillFormWithText(By.name("pass"), password);
        clickOnForm(By.xpath("//form[@id='LoginForm']/input[3]"));
    }
}
