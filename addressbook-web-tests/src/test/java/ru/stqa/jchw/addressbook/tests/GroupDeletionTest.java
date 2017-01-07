package ru.stqa.jchw.addressbook.tests;

import org.testng.annotations.Test;

public class GroupDeletionTest extends TestBase {


    @Test
    public void testGroupDelition() {

        app.getNavigationHelper().gotoGroupPage();
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().deleteSelectedGroup();
        app.getGroupHelper().returnToGroupPage();
    }


}
