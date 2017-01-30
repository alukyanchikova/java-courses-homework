package ru.stqa.jchw.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.jchw.addressbook.model.GroupData;

import java.util.List;

public class GroupDeletionTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.getNavigationHelper().gotoGroupPage();
        if (app.getGroupHelper().getGroupList().size() == 0) {
            app.getGroupHelper().createGroup(new GroupData("test", null, null));
        }
    }

    @Test
    public void testGroupDeletion() {
        List<GroupData> before = app.getGroupHelper().getGroupList();
        int indexGroupDelition = before.size() - 1;
        app.getGroupHelper().deleteGroup(indexGroupDelition);
        List<GroupData> after = app.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(indexGroupDelition);
        Assert.assertEquals(before, after);
    }


}
