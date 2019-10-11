package ru.stqa.jft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.jft.addressbook.model.ContactData;
import ru.stqa.jft.addressbook.model.Contacts;
import ru.stqa.jft.addressbook.model.GroupData;
import ru.stqa.jft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDivisionIntoGroupTest extends TestBase {

    @Test
    public void testContactDivisionIntoGroup() {

        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            createDefaultGroup();
        }
        if (app.db().contacts().size() == 0) {
            app.goTo().homePage();
            createDefaultContact();
        }
        Groups groups = app.db().groups();
        Contacts contacts = app.db().contacts();
        GroupData workGroup = null;

//        for (GroupData group: groups){
//            group.getContacts().size() < contacts.size() ? workGroup = group : if(!groups.iterator().hasNext()){ createDefaultGroup();}
//        }
//
//
//
//

        for (ContactData contact : contacts) {
            Groups contactGroupsBefore = contact.getGroups();
            if (groups.size() > contactGroupsBefore.size()) {
                app.contact().selectById(contact.getId());
                for (int i = 0; i < groups.size(); i++) {
                    GroupData group = (GroupData) groups.delegate.toArray()[i];
                    if (!contact.groups.contains(group)) {
                        app.contact().putInGroup(group.getId());
                        contact.inGroup(group);

                        Groups contactGroupsAfter = contact.getGroups();
                        assertThat(contactGroupsAfter, equalTo(contactGroupsBefore.withAdded(group)));
                        return;
                    }
                }
            } else {
                if (groups.size() == contactGroupsBefore.size() && !contacts.iterator().hasNext()) {
                    putContactInNewGroup(contact);
                    Groups contactGroupsAfter = contact.getGroups();
                    assertThat(contactGroupsAfter.size(), equalTo(contactGroupsBefore.size() + 1));
                }
            }
        }
    }

}


