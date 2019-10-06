package ru.stqa.jft.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ru.stqa.jft.addressbook.appmanager.ApplicationManager;
import ru.stqa.jft.addressbook.model.ContactData;
import ru.stqa.jft.addressbook.model.Contacts;
import ru.stqa.jft.addressbook.model.GroupData;
import ru.stqa.jft.addressbook.model.Groups;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestBase {

    Logger logger = LoggerFactory.getLogger(TestBase.class);

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

    @BeforeMethod(alwaysRun = true)
    public void logTestStart(Method m, Object[] p) {
        logger.info("Start test " + m.getName() + " with parameters" + Arrays.asList(p));
    }

    @AfterMethod(alwaysRun = true)
    public void logTestStop(Method m) {
        logger.info("Stop test " + m.getName());
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

    public void verifyGroupListInUI() {
        if (Boolean.getBoolean("verifyUI")) {
            Groups dbGroups = app.db().groups();
            Groups uiGroups = app.group().all();
            assertThat(uiGroups, equalTo(dbGroups.stream()
                    .map((g) -> new GroupData().withId(g.getId()).withName(g.getName()))
                    .collect(Collectors.toSet())));
        }
    }

    public void verifyContactListInUI() {
        if (Boolean.getBoolean("verifyUI")) {
            Contacts dbContacts = app.db().contacts();
            Contacts uiContacts = app.contact().all();
            assertThat(uiContacts, equalTo(dbContacts.stream()
                    .map((c) -> new ContactData().withId(c.getId()).withFirstName(c.getFirstName())
                            .withLastName(c.getLastName())).collect(Collectors.toSet())));

        }
    }

    public void putContactInNewGroup(ContactData contact) {
        app.goTo().groupPage();
        createDefaultGroup();
        app.goTo().homePage();
        app.contact().selectById(contact.getId());
        int group_id = app.db().groups().stream().mapToInt((g) -> g.getId()).max().getAsInt();
        app.contact().putInGroup(group_id);
        GroupData group = app.group().findInGroups(contact.getGroups(), group_id);
        contact.inGroup(group);
    }

    public void putNewContactInGroup(GroupData group) {
        app.goTo().homePage();
        app.contact().selectGroup(0);
        ContactData contact = app.db().contacts().iterator().next();
        app.contact().selectById(contact.getId());
        app.contact().putInGroup(group.getId());
        contact.inGroup(group);
    }
}
