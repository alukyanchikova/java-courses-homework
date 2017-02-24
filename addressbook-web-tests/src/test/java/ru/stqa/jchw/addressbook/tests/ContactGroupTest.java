package ru.stqa.jchw.addressbook.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.jchw.addressbook.model.ContactData;
import ru.stqa.jchw.addressbook.model.GroupData;

import java.util.UUID;

import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;


public class ContactGroupTest extends TestBase {

    private ContactData contact;
    private GroupData group;

    @BeforeMethod
    public void createContact() {
        final String contactFirstName = UUID.randomUUID().toString();
        app.goTo().homePage();
        app.contact().createContact(new ContactData().withFirstname(contactFirstName));
        this.contact = app.db().getContactsByFirstName(contactFirstName).get(0);
    }

    @BeforeMethod
    public void createGroup() {
        final String groupName = UUID.randomUUID().toString();
        app.goTo().groupPage();
        app.group().create(new GroupData().withName(groupName));
        this.group = app.db().getGroupByName(groupName).get(0);
    }

    @AfterMethod
    public void deleteContact() {
        app.goTo().homePage();
        app.contact().chooseAllGroups();
        app.contact().delete(contact);
    }

    @AfterMethod
    public void deleteGroup() {
        app.goTo().groupPage();
        app.group().delete(group);
    }

    @Test
    private void testAddingContactInGroup() {
        // when
        app.contact().addContactToGroup(contact.getId(), group.getId());

        // then
        boolean contactAddedToGroup = app.db().isContactAddedToGroup(contact.getId(), group.getId());
        assertTrue(contactAddedToGroup);
    }

    @Test
    private void testRemoveContactFromGroup() throws InterruptedException {
        // given
        app.contact().addContactToGroup(contact.getId(), group.getId());

        // when
        app.goTo().homePage();
        app.group().removeContactFromGroup(contact.getId(), group.getId());

        // then
        Thread.sleep(500); // ensure timeout
        boolean contactAddedToGroup = app.db().isContactAddedToGroup(contact.getId(), group.getId());
        assertFalse(contactAddedToGroup);
    }
}
