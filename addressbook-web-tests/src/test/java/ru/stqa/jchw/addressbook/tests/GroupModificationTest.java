package ru.stqa.jchw.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.jchw.addressbook.model.GroupData;

public class GroupModificationTest extends TestBase{

    @Test
    public void testGroupModification() {
        app.getNavigationHelper().gotoGroupPage();
        int numberOfElementsBefore = app.getGroupHelper().getGroupCount();
        if (! app.getGroupHelper().isThereAGroup()) {
            app.getGroupHelper().createGroup(new GroupData("test", null, null));
        }
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().initGroupModification();
        app.getGroupHelper().fillGroupForm(new GroupData("test", "test", "test"));
        app.getGroupHelper().submitGroupModification();
        app.getGroupHelper().returnToGroupPage();
        int numberOfElementsAfter = app.getGroupHelper().getGroupCount();
        Assert.assertEquals(numberOfElementsAfter, numberOfElementsBefore);
    }
}
