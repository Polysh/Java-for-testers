package ru.stqa.jft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.jft.addressbook.model.ContactData;

public class ContactModificationTest extends TestBase {

    @Test
    public void testContactModification() {
        app.getNavigationHelper().gotoHomePage();
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("Тест", "Тестович", "Тестик", "баг", "9630000001", "test@mail.ru"));
        }
        app.getContactHelper().selectContact();
        app.getContactHelper().editContact();
        app.getContactHelper().fillContactData(new ContactData("Тест", "Тестович", "Тестиков", "баг", "9630000001", "test@mail.ru"));
        app.getContactHelper().submitContactModifiation();
        app.getContactHelper().returnContactPage();

    }
}
