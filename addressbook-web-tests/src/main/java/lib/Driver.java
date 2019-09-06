package lib;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class Driver implements Runnable {

    /**
     * Переменная web-драйвера.
     */
    public static WebDriver webdriver;
    public static final Integer DRIVER_WINDOW_WEIGHT = 1800;
    public static final Integer DRIVER_WINDOW_HEIGHT = 1000;

    private static void createDriver() {

        webdriver = getChromeDriver();

        setSize(DRIVER_WINDOW_WEIGHT, DRIVER_WINDOW_HEIGHT);
    }

    private static WebDriver getChromeDriver() {

        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("profile.default_content_setting_values.automatic_downloads", 1);

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", chromePrefs);

        System.setProperty("webdriver.chrome.driver", "webdriver/chrome/chromedriver");

        options.addArguments("start-maximized");
        options.setCapability("networkConnectionEnabled", true);
        options.setCapability("takesScreenshot", true);

        options.setCapability("browserName", "LinkedinPeopleParser");
        options.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT, true);
        options.setCapability(CapabilityType.ELEMENT_SCROLL_BEHAVIOR, true);
        options.setCapability(CapabilityType.SUPPORTS_FINDING_BY_CSS, true);
        options.setCapability(CapabilityType.HAS_NATIVE_EVENTS, true);

        ChromeDriverService driverService = new ChromeDriverService.Builder().usingAnyFreePort()
                .usingDriverExecutable(new File("webdriver/chrome/chromedriver.exe")).build();

        ChromeDriver browser = new ChromeDriver(driverService, options);

        return browser;
    }

    public static WebDriver getDriver(String browser) {
        if (browser.equals(BrowserType.CHROME)) {
            createDriver();
        } else if (browser.equals(BrowserType.IE)) {
                webdriver = new InternetExplorerDriver();
        }
        return webdriver;
    }

    static {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                webdriver.quit();
            }
        });
    }

    @Override
    public void run() {
    }

    public static void setSize(Integer weight, Integer height) {
        if (weight == null) {
            weight = webdriver.manage().window().getSize().getWidth();

        }

        if (height == null) {
            height = webdriver.manage().window().getSize().getHeight();
        }

        webdriver.manage().window().setSize(new Dimension(weight, height));

    }
}
