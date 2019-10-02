package ru.stqa.jft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.jft.addressbook.model.ContactData;
import ru.stqa.jft.addressbook.model.Contacts;
import ru.stqa.jft.addressbook.model.GroupData;
import ru.stqa.jft.addressbook.model.Groups;

import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDivisionIntoGroupTest extends TestBase {

    @Test
    public void testContactDivisionIntoGroup() {
        Groups groups = app.db().groups();
        app.goTo().homePage();
        Contacts contacts = app.db().contacts();
        Contacts fullList = app.contact().all();
        ContactData grouppedContact = fullList.iterator().next();
        app.contact().selectById(grouppedContact.getId());

        ContactData baseContact = contacts.find(grouppedContact);
        Set<GroupData> contactGroups = baseContact.groups;

        if (groups.size() == contactGroups.size()) {
            putContactInNewGroup(grouppedContact);
        } else {
            for (int i = 0; i < groups.size(); i++) {
                GroupData group = (GroupData) groups.delegate.toArray()[i];
                if (!contactGroups.contains(group)) {
                    app.contact().putInGroup(group.getId());
                    return;
                }
            }
        }
        Set<GroupData> contactGroupsNew = app.db().contacts().find(grouppedContact).groups;
        assertThat("Контакт не был добавлен в новую группу",
                contactGroupsNew.size() == contactGroups.size() + 1);
    }


}


