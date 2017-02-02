package ru.stqa.jchw.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.jchw.addressbook.model.GroupData;

import java.util.ArrayList;
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

    public void selectGroup(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
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
        returnToGroupPage();
    }

    public void modify(int indexGroupForModification, GroupData group) {
        selectGroup(indexGroupForModification);
        initGroupModification();
        fillGroupForm(group);
        submitGroupModification();
        returnToGroupPage();
    }

    public void delete(int indexGroupDelition) {
        selectGroup(indexGroupDelition);
        deleteSelectedGroup();
        returnToGroupPage();
    }

    public boolean isThereAGroup() {
        return isElementPresent(By.name("selected[]"));
    }

    public int getGroupCount() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public List<GroupData> list() {
        List<GroupData> groups = new ArrayList<GroupData>();
        List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
        for (WebElement element : elements) {
            String name = element.getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            groups.add(new GroupData().withId(id).withName(name));
        }
        return groups;
    }
}
