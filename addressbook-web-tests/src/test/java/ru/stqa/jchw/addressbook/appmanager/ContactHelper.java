package ru.stqa.jchw.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.jchw.addressbook.model.ContactData;

public class ContactHelper extends HelperBase {

    public ContactHelper(FirefoxDriver wd) {
        super(wd);
    }

    public void saveNewContact() {
        wd.findElement(By.xpath("//div[@id='content']/form/input[21]")).click();
    }

    public void fillContactForm(ContactData contactData) {
        fillFormWithText(By.name("firstname"), contactData.getFirstname());
        fillFormWithText(By.name("lastname"), contactData.getLastname());
        fillFormWithText(By.name("address"), contactData.getLastname());
        fillFormWithText(By.name("mobile"), contactData.getMobilePhone());
        fillFormWithText(By.name("email"), contactData.getEmail());
    }

    public void addNewContact() {
        wd.findElement(By.linkText("add new")).click();
    }

    public void selectContact() {
        clickOnForm(By.name("selected[]"));
    }

    public void acceptDeletion() {
        wd.switchTo().alert().accept();
    }

    public void clickOnDelete() {
        wd.findElement(By.xpath("//div[@id='content']/form[2]/div[2]/input")).click();
    }
}

