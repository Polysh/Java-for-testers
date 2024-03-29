package ru.stqa.jft.mantis.appmanager;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.regex.MatchResult;

public class ApplicationManager {
    private static WebDriver wd;
    private final Properties properties;


    private String browser;
    private RegistrationHelper registrationHelper;
    private FtpHelper ftp;
    private MailHelper mailHelper;
    private JamesHelper jamesHelper;
    private DbHelper dbHelper;
    private UserHelper us;

    public ApplicationManager(String browser) {
        this.browser = browser;
        properties = new Properties();

    }

    public void init() throws IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
        dbHelper = new DbHelper();


    }


    public static Object executeJS(String query) {
        return ((JavascriptExecutor) (wd)).executeScript(query);
    }

    public static WebElement jQuery(String selector) {
        return (WebElement) executeJS("return jQuery.find(\"" + selector + "\")[0]");
    }

    public void stop() {
        if (wd != null) {
            wd.quit();
        }
    }

    public HttpSession newSession() {
        return new HttpSession(this);
    }

    public String getProperty(String key) {
        return properties.getProperty(key);

    }

    public RegistrationHelper registration() {
        if (registrationHelper == null) {
            registrationHelper = new RegistrationHelper(this);
        }
        return registrationHelper;
    }

    public FtpHelper ftp() {
        if (ftp == null) {
            ftp = new FtpHelper(this);
        }
        return ftp;
    }

    public WebDriver getDriver() {
        if (wd == null) {
            if (browser.equals(BrowserType.CHROME)) {
                wd = new ChromeDriver();
            } else if (browser.equals(BrowserType.IE)) {
                wd = new InternetExplorerDriver();
            }
            wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            wd.get(properties.getProperty("web.baseUrl"));
        }
        return wd;
    }

    public MailHelper mail(){
        if(mailHelper ==null){
            mailHelper = new MailHelper(this);
        }
        return mailHelper;
    }

    public JamesHelper james(){
        if(jamesHelper ==null){
            jamesHelper = new JamesHelper(this);
        }
        return jamesHelper;
    }

    public DbHelper db() {
        return dbHelper;
    }

    public UserHelper us() {
        if (us == null) {
            us = new UserHelper(this);
        }
        return us;
    }
}
