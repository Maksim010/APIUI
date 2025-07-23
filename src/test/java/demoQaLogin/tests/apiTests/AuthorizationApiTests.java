package demoQaLogin.tests.apiTests;

import demoQaLogin.api.AuthorizationApi;
import demoQaLogin.baseTests.BaseTests;
import demoQaLogin.models.request.AuthModel;
import demoQaLogin.models.response.AuthModelResponse;
import demoQaLogin.pages.ProfilePage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;
import static demoQaLogin.TestData.PROFILE_PATH;
import static demoQaLogin.TestData.createAuthModel;
import static io.qameta.allure.Allure.step;

public class AuthorizationApiTests extends BaseTests {

    ProfilePage profilePage = new ProfilePage();
    AuthorizationApi authorizationApi = new AuthorizationApi();

    @Test
    @Tag("API")
    @DisplayName("API Authorization")
    void successfulOpenProfilePageWithCookie() {

        AuthModel authModel = createAuthModel();

        AuthModelResponse response =
                authorizationApi.getAuthentication(authModel);

        step("Set Cookie", () ->
                authorizationApi.setCookie(response)
        );
        step("Open profile page", () ->
            open(PROFILE_PATH)
        );

        step("Verify Auth", () ->
                profilePage.assertUserName(authModel.userName()));
    }
}
