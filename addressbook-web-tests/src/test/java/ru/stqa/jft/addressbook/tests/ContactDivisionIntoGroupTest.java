package ru.stqa.jft.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.jft.addressbook.model.ContactData;
import ru.stqa.jft.addressbook.model.Contacts;
import ru.stqa.jft.addressbook.model.Groups;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDivisionIntoGroupTest extends TestBase {


    @DataProvider
    public Iterator<Object[]> validGroupsFromJson() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File(app.properties.getProperty("test.contactsJson"))));
        String json = "";
        String line = reader.readLine();
        while (line != null) {
            json += line;
            line = reader.readLine();

        }
        Gson gson = new Gson();
        List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>() {
        }.getType());
        return contacts.stream().map((c) -> new Object[]{c}).collect(Collectors.toList()).iterator();
    }

    @Test
    public void testContactDivisionIntoGroup() {
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            createDefaultGroup();
        }

        Groups groups = app.db().groups();
        app.goTo().homePage();
        Contacts fullList = app.contact().all();
//        Contacts before = app.db().contacts();
        ContactData grouppedContact = fullList.iterator().next();
        grouppedContact.getGroups();
        grouppedContact.inGroup(groups.iterator().next());
//        app.contact().create(contact);
//        assertThat(app.contact().count(), equalTo(before.size() + 1));
//        Contacts after = app.db().contacts();
//        assertThat(after, equalTo(
//                before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
//        verifyContactListInUI();
    }

}
