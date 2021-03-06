package ru.stqa.jchw.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.jchw.addressbook.model.ContactData;
import ru.stqa.jchw.addressbook.model.Contacts;
import ru.stqa.jchw.addressbook.model.GroupData;
import ru.stqa.jchw.addressbook.model.Groups;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTest extends TestBase {

    @DataProvider
    public Iterator<Object[]> validContactsFromJson() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/generated-data/contacts.json")))) {
            String json = "";
            String line =reader.readLine();
            while (line != null) {
                json += line;
                line = reader.readLine();
            }
            Gson gson = new Gson();
            List<ContactData> contacts =  gson.fromJson(json, new TypeToken<List<ContactData>>() {}.getType());
            return contacts.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
        }
    }

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test"));
        }
    }

    @Test (dataProvider = "validContactsFromJson")
    public void testContactCreation(ContactData contact) {
        // given
        Groups groups = app.db().groups();
        Contacts before = app.db().contacts();
        app.goTo().homePage();

        // when
        app.contact().createContact(contact.inGroup(groups.iterator().next()));

        // then
        assertThat(app.contact().count(), equalTo(before.size() + 1));
        Contacts after = app.db().contacts();

        int addedContactId = after.stream().mapToInt((c) -> c.getId()).max().getAsInt();
        assertThat(after, equalTo(before.withAdded(contact.withId(addedContactId))));
        verifyContactsListUI();
    }
}
