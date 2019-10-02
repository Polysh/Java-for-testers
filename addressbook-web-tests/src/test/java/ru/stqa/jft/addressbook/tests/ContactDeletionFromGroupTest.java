package ru.stqa.jft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.jft.addressbook.model.ContactData;
import ru.stqa.jft.addressbook.model.Contacts;
import ru.stqa.jft.addressbook.model.GroupData;
import ru.stqa.jft.addressbook.model.Groups;

import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionFromGroupTest extends TestBase {

    @Test
    public void testContactDeletionFromGroup() {
        Groups groups = app.db().groups();
        Assert.assertTrue(groups.size() > 0, "Группы не найдены");
        app.goTo().homePage();
        Contacts fullList = app.db().contacts();
        ContactData deletedContact = fullList.iterator().next();
        for (int i = 0; i < groups.size(); i++) {
            GroupData group = (GroupData) groups.delegate.toArray()[i];
            app.contact().selectGroup(group.getId());
            Contacts contactsInGroup = app.contact().all();

            if (contactsInGroup.size() > 0) {
                for (ContactData contact : contactsInGroup) {
                    if (contact.getId() == deletedContact.getId()) {
                        app.contact().selectById(deletedContact.getId());
                        app.contact().deleteFromGroup(group.getName());
                        Set<ContactData> contactInGroupsNew = app.contact().all();
                        assertThat("Контакт не был удален из группы",
                                ((Contacts) contactInGroupsNew).find(deletedContact).getFirstName() == null);
                        return;
                    }
                }
            }
        }
    }

}


