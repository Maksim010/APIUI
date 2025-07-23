package demoQaLogin.tests.uiTests;

import demoQaLogin.baseTests.BaseTests;
import demoQaLogin.pages.ProfilePage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static demoQaLogin.TestData.DEFAULT_PASSWORD;
import static demoQaLogin.TestData.DEFAULT_USERNAME;
import static io.qameta.allure.Allure.step;

public class AuthorizationTest extends BaseTests {

    ProfilePage profilePage = new ProfilePage();

    @Test
    @Tag("WEB")
    @DisplayName("Authorization WEB")
    void successfulUILogin() {

        step("Authorization", () ->
                profilePage
                        .precondition()
                        .setPassword(DEFAULT_PASSWORD)
                        .setLogin(DEFAULT_USERNAME)
                        .clickButton());

        step("Verify", () ->
                profilePage.assertUserName(DEFAULT_USERNAME));
    }
}

