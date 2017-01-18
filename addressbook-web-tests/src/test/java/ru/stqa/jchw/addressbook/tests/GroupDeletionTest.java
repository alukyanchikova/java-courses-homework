package ru.stqa.jchw.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.jchw.addressbook.model.GroupData;

public class GroupDeletionTest extends TestBase {

    @Test
    public void testGroupDeletion() {
        app.getNavigationHelper().gotoGroupPage();
        int numberOfElementsBefore = app.getGroupHelper().getGroupCount();
        if (! app.getGroupHelper().isThereAGroup()) {
            app.getGroupHelper().createGroup(new GroupData("test", null, null));
        }
        app.getGroupHelper().selectGroup(numberOfElementsBefore-1);
        app.getGroupHelper().deleteSelectedGroup();
        app.getGroupHelper().returnToGroupPage();
        int numberOfElementsAfter = app.getGroupHelper().getGroupCount();
        Assert.assertEquals(numberOfElementsAfter, numberOfElementsBefore - 1);
    }
}
