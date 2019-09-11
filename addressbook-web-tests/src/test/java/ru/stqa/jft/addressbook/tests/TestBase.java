package ru.stqa.jft.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.jft.addressbook.appmanager.ApplicationManager;

public class TestBase {


    protected static final ApplicationManager app = new ApplicationManager(BrowserType.CHROME);

    @BeforeSuite(alwaysRun = true)
    public void setUp() {
        app.init();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        app.stop();
    }

}
