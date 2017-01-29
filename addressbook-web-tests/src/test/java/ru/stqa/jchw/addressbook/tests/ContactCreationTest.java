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
        app.getContactHelper().returnToHomePage();
        List<ContactData> before = app.getContactHelper().getContactList();
        ContactData contact = new ContactData("Ivan", "Ivanov", "144A Mira str., Apt. 1, Moscow 123456, " +
                "Russia.", "8 (999) 11-11-111", "111@111.com", "test");

        // when
        app.getContactHelper().createContact(contact);
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() + 1);
        before.add(contact);

        // then
        Comparator<ContactData> byLastAndFirstName = (o1, o2) -> {
            int lestNameCompareResult = o1.getLastname().compareTo(o2.getLastname());
            if (lestNameCompareResult == 0) {
                return o1.getFirstname().compareTo(o2.getFirstname());
            }
            return lestNameCompareResult;
        };

        before.sort(byLastAndFirstName);
        after.sort(byLastAndFirstName);

        Assert.assertEquals(before, after);
    }
}
