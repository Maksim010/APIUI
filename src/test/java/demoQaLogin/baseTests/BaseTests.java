package demoQaLogin.baseTests;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import demoQaLogin.config.App;
import demoQaLogin.helpers.Attach;
import demoQaLogin.helpers.extensions.DriverSettings;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import static demoQaLogin.helpers.CustomAllureListener.withCustomTemplates;

public class BaseTests {

    @BeforeAll
    public static void setUpBeforeClass() throws Exception {
        DriverSettings.configure();
        RestAssured.baseURI = App.config.apiUrl();
        RestAssured.filters(withCustomTemplates());
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
