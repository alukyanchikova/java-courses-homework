package ru.stqa.jchw.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.jchw.addressbook.model.ContactData;
import ru.stqa.jchw.addressbook.model.Contacts;

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

    public void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[value ='" + id + "']")).click();
    }

    public void acceptDeletion() {
        wd.switchTo().alert().accept();
    }

    public void clickOnDelete() {
        clickOnForm(By.xpath("//input[@value='Delete']"));
    }

    public void initContactModification(int id) {
        List<WebElement> rows = wd.findElements(By.xpath(".//table[@id='maintable']/descendant::tr[@name='entry']"));
        for (WebElement row : rows) {
            List<WebElement> columns = row.findElements(By.xpath(".//td"));
            WebElement idColumn = columns.get(0);
            int columnId = Integer.parseInt(idColumn.findElement(By.tagName("input")).getAttribute("value"));
            if (columnId == id) {
                WebElement editButton = columns.get(7);
                editButton.click();
                return;
            }
        }
    }

    public void submitContactModification() {
        clickOnForm(By.name("update"));
    }

    public void createContact(ContactData contact) {
        returnToHomePage();
        addNewContact();
        fillContactForm(contact, true);
        saveNewContact();
        contactCache = null;
        returnToHomePage();
    }

    public void modify(ContactData contact) {
        selectContactById(contact.getId());
        initContactModification(contact.getId());
        fillContactForm(contact, false);
        submitContactModification();
        contactCache = null;
        returnToHomePage();
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        clickOnDelete();
        acceptDeletion();
        contactCache = null;
        returnToHomePage();
    }

    public Contacts contactCache = null;

    public Contacts all() {
        if (contactCache != null) {
            return new Contacts(contactCache);
        }
        contactCache = new Contacts();
        List<WebElement> rows = wd.findElements(By.xpath(".//table[@id='maintable']/descendant::tr[@name='entry']"));
        for (WebElement row : rows) {
            List<WebElement> columns = row.findElements(By.xpath(".//td"));
            WebElement idColumn = columns.get(0);
            int id = Integer.parseInt(idColumn.findElement(By.tagName("input")).getAttribute("value"));
            WebElement firstNameColumn = columns.get(2);
            String firstName = firstNameColumn.getText();
            WebElement lastNameColumn = columns.get(1);
            String lastName = lastNameColumn.getText();
            contactCache.add(new ContactData().withId(id).withFirstname(firstName).withLastname(lastName));
        }
        return new Contacts(contactCache);
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

