package ru.stqa.jchw.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.jchw.addressbook.model.GroupData;

public class GroupCreationTest extends TestBase {

    @Test
    public void testGroupCreation() {
        app.getNavigationHelper().gotoGroupPage();
        int numberOfElementsBefore = app.getGroupHelper().getGroupCount();
        app.getGroupHelper().createGroup(new GroupData("test", null, null));
        int numberOfElementsAfter = app.getGroupHelper().getGroupCount();
        Assert.assertEquals(numberOfElementsAfter, numberOfElementsBefore + 1);
    }
}
