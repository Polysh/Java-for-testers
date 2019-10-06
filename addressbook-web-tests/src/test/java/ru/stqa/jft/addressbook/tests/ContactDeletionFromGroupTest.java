package ru.stqa.jft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.jft.addressbook.model.ContactData;
import ru.stqa.jft.addressbook.model.Contacts;
import ru.stqa.jft.addressbook.model.GroupData;
import ru.stqa.jft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionFromGroupTest extends TestBase {

    @Test
    public void testContactDeletionFromGroup() {
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            createDefaultGroup();
        }
        if (app.db().contacts().size() == 0) {
            app.goTo().homePage();
            createDefaultContact();
        }
        Groups groups = app.db().groups();
        for (GroupData group : groups) {
            app.contact().selectGroup(group.getId());
            Contacts contactsInGroupBefore = group.getContacts();

            if (contactsInGroupBefore.size() == 0) {
                putNewContactInGroup(group);
                app.goTo().homePage();
                app.contact().selectGroup(group.getId());
            }
            ContactData contact = app.contact().all().iterator().next();
            app.contact().selectById(contact.getId());
            app.contact().deleteFromGroup(group.getName());
            contact.fromGroup(group);
            Contacts contactsInGroupAfter = group.getContacts();
            assertThat(contactsInGroupAfter, equalTo(contactsInGroupBefore.withOut(contact)));
            return;

        }
    }
}




