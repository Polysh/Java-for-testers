package ru.stqa.jft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.jft.addressbook.model.ContactData;
import ru.stqa.jft.addressbook.model.Contacts;

public class ContactModificationTest extends TestBase {

    @Test
    public void testContactModification() {
        app.goTo().homePage();
        if (app.contact().all().size() == 0) {
            app.contact().create(new ContactData().withFirstName("Тест").withMiddleName("Тестович")
                    .withLastName("Тестик").withNik("баг").withPhone("9630000001").withEmail("test@mail.ru"));
        }
        Contacts before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstName("Тестюк")
                .withMiddleName("Тестович").withLastName("Тестиковский").withNik("баг")
                .withPhone("9630000001").withEmail("test@mail.ru");
        app.contact().modify(contact);
        Contacts after = app.contact().all();
        Assert.assertEquals(after.size(), before.size());
        MatcherAssert.assertThat(after, CoreMatchers.equalTo(before.withOut(modifiedContact).withAdded(contact)));
    }


}
