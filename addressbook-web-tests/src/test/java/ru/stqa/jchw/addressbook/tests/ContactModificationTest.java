package ru.stqa.jchw.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.jchw.addressbook.model.ContactData;
import ru.stqa.jchw.addressbook.model.Contacts;
import ru.stqa.jchw.addressbook.model.GroupData;
import ru.stqa.jchw.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        Groups groups = app.db().groups();
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test"));
        }
        if (app.db().contacts().size() == 0) {
            app.goTo().homePage();
            app.contact().createContact(new ContactData().withFirstname("Ivan").withLastname("Ivanov").withAddress("144A Mira str., Apt. 1, Moscow 123456, Russia.").
                    withMobilePhone("8 (999) 11-11-111").withEmail("111@111.com").inGroup(groups.iterator().next()));
        }
    }

    @Test
    public void testContactModification() {
        Contacts before = app.db().contacts();
        ContactData modifiedContact = before.iterator().next();

        ContactData contact = new ContactData(modifiedContact)
                .withId(modifiedContact.getId())
                .withFirstname("Алексей")
                .withLastname("Алексеев");

        app.goTo().homePage();
        app.contact().modify(contact);
        assertEquals(app.contact().count(), before.size());
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
        verifyContactsListUI();
    }
}