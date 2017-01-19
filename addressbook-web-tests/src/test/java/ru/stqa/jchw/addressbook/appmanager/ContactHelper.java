package ru.stqa.jchw.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.jchw.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void saveNewContact() {
        clickOnForm(By.name("submit"));
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        fillFormWithText(By.name("firstname"), contactData.getFirstname());
        fillFormWithText(By.name("lastname"), contactData.getLastname());
        fillFormWithText(By.name("address"), contactData.getAddress());
        fillFormWithText(By.name("mobile"), contactData.getMobilePhone());
        fillFormWithText(By.name("email"), contactData.getEmail());

        if (creation) {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void addNewContact() {
        wd.findElement(By.linkText("add new")).click();
    }

    public void selectContact(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public void acceptDeletion() {
        wd.switchTo().alert().accept();
    }

    public void clickOnDelete() {
        clickOnForm(By.xpath("//input[@value='Delete']"));
    }

    public void initContactModification(int index) {
        wd.findElements(By.xpath ("//img[@title='Edit'][1]")).get(index).click();
    }

    public void submitContactModification() {
        clickOnForm(By.name("update"));
    }

    public void createContact(ContactData contact) {
        returnToHomePage();
        List<ContactData> before = getContactList();
        addNewContact();
        fillContactForm(contact, true);
        saveNewContact();
        returnToHomePage();
        List<ContactData> after = getContactList();
        Assert.assertEquals(after.size(), before.size() + 1);
    }

    public List<ContactData> getContactList() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        List<WebElement> elements = wd.findElements(By.xpath(".//table[@id='maintable']/descendant::tr[@name='entry']"));
        //List<WebElement> elements = wd.findElements(By.xpath(".//table[@id='maintable']/descendant::input[@type='checkbox']"));
        for (WebElement element : elements) {
            String name = element.getText();
            ContactData contact = new ContactData(name, null, null, null, null, null);
            contacts.add(contact);
        }
        return contacts;
    }

    public void returnToHomePage() {
        clickOnForm(By.linkText("home"));
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public int getContactCount() {
        return wd.findElements(By.name("selected[]")).size();
    }
}

