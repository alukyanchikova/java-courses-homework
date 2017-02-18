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
        fillFormWithText(By.name("home"), contactData.getHomePhone());
        fillFormWithText(By.name("mobile"), contactData.getMobilePhone());
        fillFormWithText(By.name("work"), contactData.getWorkPhone());
        fillFormWithText(By.name("email"), contactData.getEmail());
        fillFormWithText(By.name("email2"), contactData.getEmail2());
        fillFormWithText(By.name("email3"), contactData.getEmail3());
        attach(By.name("photo"), contactData.getPhoto());

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

    public ContactData initFromEditForm(ContactData contact) {
        initContactModification(contact.getId());
        String firstName = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastName = wd.findElement(By.name("lastname")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withFirstname(firstName).withLastname(lastName).withAddress(address)
                .withEmail(email).withEmail2(email2).withEmail3(email3)
                .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work);
     }

    public void initContactModification(int id) {
        WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));
        WebElement row = checkbox.findElement(By.xpath("./../.."));
        List<WebElement> cells = row.findElements(By.tagName("td"));
        cells.get(7).findElement(By.tagName("a")).click();

        //wd.findElement(By.xpath(String.format("//input[@value='%s']/../../td[8]/a", id))).click();
        //wd.findElement(By.xpath(String.format("//tr[.//input[@value='%s']]/td[8]/a", id))).click();
    }

    public String initFromDetailsForm(ContactData contact) {
        initContactDetails(contact.getId());
        return wd.findElement(By.id("content")).getText();
        //return wd.findElement(By.xpath("//form[@id='content']")).getText();
    }

    public void initContactDetails (int id) {
        WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));
        WebElement row = checkbox.findElement(By.xpath("./../.."));
        List<WebElement> cells = row.findElements(By.tagName("td"));
        cells.get(6).findElement(By.tagName("a")).click();
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
            String lastName = columns.get(1).getText();
            String firstName = columns.get(2).getText();
            String address = columns.get(3).getText();
            String allEmails = columns.get(4).getText();
            String allPhones = columns.get(5).getText();
            contactCache.add(new ContactData().withId(id).withFirstname(firstName).withLastname(lastName).withAddress(address).withAllEmails(allEmails).withAllPhones(allPhones));
        }
        return new Contacts(contactCache);
    }

    public void returnToHomePage() {
        clickOnForm(By.linkText("home"));
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }
}

