package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

public class TestBase {

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";

        Configuration.browser = getBrowser();
        Configuration.browserVersion = getBrowserVersion();
        Configuration.browserSize = getBrowserSize();
        Configuration.remote = getServer();

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));
        Configuration.browserCapabilities = capabilities;

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        //Configuration.holdBrowserOpen = true;

    }

    @AfterEach
    void afterEach(){
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
    }

    static String getServer(){
        String login = System.getProperty("login");
        String pw = System.getProperty("pw");
        String server = System.getProperty("server");

        return "https://" + login + ":" + pw + server + "/wd/hub";
    }

    static String getBrowser(){
        return System.getProperty("browser","chrome");
    }

    static String getBrowserVersion(){
        return System.getProperty("version");
    }

    static String getBrowserSize(){
        return System.getProperty("size","1920x1080");
    }
}