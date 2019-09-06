package ru.stqa.jft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.jft.addressbook.model.ContactData;

public class ContactCreationTest extends TestBase {

    @Test
    public void testContactCreation() {
        app.getNavigationHelper().gotoHomePage();
        app.getContactHelper().initContactCreation();
        app.getContactHelper().fillContactData(new ContactData("Тест", "Тестович", "Тестик", "баг", "9630000001", "test@mail.ru"));
        app.getContactHelper().submitContactCreation();
        app.getContactHelper().returnContactPage();

    }

}
