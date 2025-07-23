package demoQaLogin.baseTests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import demoQaLogin.helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

import static demoQaLogin.TestData.PAGE_LOAD_TIMEOUT;
import static demoQaLogin.helpers.CustomAllureListener.withCustomTemplates;

public class BaseTests {

    protected static final String DOMAIN = "https://demoqa.com";

    @BeforeAll
    public static void setUpBeforeClass() throws Exception {
        Configuration.baseUrl = System.getProperty("baseUrl", "https://demoqa.com");
        Configuration.browser = System.getProperty("browser", "chrome");
        Configuration.pageLoadTimeout = PAGE_LOAD_TIMEOUT;

        RestAssured.baseURI = System.getProperty("baseUrl", DOMAIN);;
        RestAssured.filters(withCustomTemplates());

        Configuration.remote = System.getProperty("remote.url", "https://user1:1234@" +
                System.getProperty("selenoid", "selenoid.autotests.cloud") + "/wd/hub");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));
        Configuration.browserCapabilities = capabilities;

        SelenideLogger.addListener("AllureListener" , new AllureSelenide());
    }

    @AfterEach
    public void tearDown() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Selenide.closeWebDriver();
        Selenide.webdriver().driver().clearCookies();
    }
}
