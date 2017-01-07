package ru.stqa.jchw.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.jchw.addressbook.model.GroupData;

public class GroupHelper extends HelperBase {

    public GroupHelper(FirefoxDriver wd) {
        super(wd);
    }

    public void returnToGroupPage() {
        clickOnForm(By.linkText("group page"));
    }

    public void submitGroupCreation() {
        clickOnForm(By.name("submit"));
    }

    public void fillGroupForm(GroupData groupData) {
        fillFormWithText(By.name("group_name"), groupData.getName());
        fillFormWithText(By.name("group_header"), groupData.getHeader());
        fillFormWithText(By.name("group_footer"), groupData.getFooter());
    }

    public void initGroupCreation() {
        clickOnForm(By.name("new"));
    }

    public void deleteSelectedGroup() {
        clickOnForm(By.name("delete"));
    }

    public void selectGroup() {
        clickOnForm(By.name("selected[]"));
    }

    public void initGroupModification() {
        clickOnForm(By.name("edit"));
    }

    public void submitGroupModification() {
        clickOnForm(By.name("update"));
    }
}
