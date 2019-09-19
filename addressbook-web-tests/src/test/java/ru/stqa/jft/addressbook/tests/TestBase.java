package ru.stqa.jft.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.jft.addressbook.appmanager.ApplicationManager;
import ru.stqa.jft.addressbook.model.ContactData;
import ru.stqa.jft.addressbook.model.GroupData;

import java.io.IOException;

public class TestBase {

    protected static final ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

    public TestBase() {
    }

    @BeforeSuite(alwaysRun = true)
    public void setUp() throws IOException {
        app.init();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        app.stop();
    }

    public void createDefaultContact() {
        app.contact().create(new ContactData().withFirstName(app.properties.getProperty("contact.firstName"))
                .withMiddleName(app.properties.getProperty("contact.middleName"))
                .withLastName(app.properties.getProperty("contact.lastName"))
                .withNik(app.properties.getProperty("contact.nik"))
                .withPhone(app.properties.getProperty("contact.phone"))
                .withEmail(app.properties.getProperty("contact.email")));
    }

    public void createDefaultGroup() {
        app.group().create(new GroupData().withName(app.properties.getProperty("group.name"))
                .withHeader(app.properties.getProperty("group.header"))
                .withFooter(app.properties.getProperty("group.footer")));
    }

}
