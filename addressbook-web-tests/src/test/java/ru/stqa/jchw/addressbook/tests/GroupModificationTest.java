package ru.stqa.jchw.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.jchw.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupModificationTest extends TestBase{

    @BeforeMethod
    public void ensurePreconditions() {
        app.getNavigationHelper().gotoGroupPage();
        if (app.getGroupHelper().getGroupList().size() == 0) {
            app.getGroupHelper().createGroup(new GroupData("test", null, null));
        }
    }

    @Test
    public void testGroupModification() {
        List<GroupData> before = app.getGroupHelper().getGroupList();
        int indexGroupModification = before.size() - 1;
        GroupData group = new GroupData(before.get(indexGroupModification).getId(), "test", "test", "test");
        app.getGroupHelper().modifyGroup(indexGroupModification, group);
        List<GroupData> after = app.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(indexGroupModification);
        before.add(group);
        Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
