package ru.stqa.jchw.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.jchw.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTest extends TestBase{

    @Test
    public void testContactCreation() {
        // given
        app.contact().returnToHomePage();
        List<ContactData> before = app.contact().list();
        ContactData contact = new ContactData().withFirstname("Ivan").withLastname("Ivanov").withAddress("144A Mira str., Apt. 1, Moscow 123456, Russia.").
                withMobilePhone("8 (999) 11-11-111").withtEmail("111@111.com").withGroup("test");

        // when
        app.contact().createContact(contact);
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size() + 1);
        before.add(contact);

        // then
        Comparator<ContactData> byLastAndFirstName = (o1, o2) -> {
            int lastNameCompareResult = o1.getLastname().compareTo(o2.getLastname());
            if (lastNameCompareResult == 0) {
                return o1.getFirstname().compareTo(o2.getFirstname());
            }
            return lastNameCompareResult;
        };

        before.sort(byLastAndFirstName);
        after.sort(byLastAndFirstName);

        Assert.assertEquals(before, after);
    }
}
