package ru.stqa.jchw.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.jchw.addressbook.model.GroupData;
import ru.stqa.jchw.addressbook.model.Groups;

import java.util.List;

public class GroupHelper extends HelperBase {

    public GroupHelper(WebDriver wd) {
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

    public void selectGroupById(int id) {
        wd.findElement(By.cssSelector("input[value ='" + id + "']")).click();
    }

    public void initGroupModification() {
        clickOnForm(By.name("edit"));
    }

    public void submitGroupModification() {
        clickOnForm(By.name("update"));
    }

    public void create(GroupData group) {
        initGroupCreation();
        fillGroupForm(group);
        submitGroupCreation();
        groupCache = null;
        returnToGroupPage();
    }

    public void modify(GroupData group) {
        selectGroupById(group.getId());
        initGroupModification();
        fillGroupForm(group);
        submitGroupModification();
        groupCache = null;
        returnToGroupPage();
    }

    public void delete(GroupData group) {
        selectGroupById(group.getId());
        deleteSelectedGroup();
        groupCache = null;
        returnToGroupPage();
    }

    public boolean isThereAGroup() {
        return isElementPresent(By.name("selected[]"));
    }

    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }

    private Groups groupCache = null;

    public Groups all() {
        if (groupCache != null) {
            return new Groups(groupCache);
        }
        groupCache = new Groups();
        List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
        for (WebElement element : elements) {
            String name = element.getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            groupCache.add(new GroupData().withId(id).withName(name));
        }
        return new Groups(groupCache);
    }

    public void removeContactFromGroup(int contactId, int groupId) {
        WebElement groupDropdownList = wd.findElement(By.name("group"));
        new Select(groupDropdownList).selectByValue(String.valueOf(groupId));
        wd.findElement(By.cssSelector("input[value ='" + contactId + "']")).click();
        wd.findElement(By.name("remove")).click();
    }
}
