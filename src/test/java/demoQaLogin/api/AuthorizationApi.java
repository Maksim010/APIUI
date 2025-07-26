package demoQaLogin.api;

import demoQaLogin.models.request.AuthModel;
import demoQaLogin.models.response.AuthModelResponse;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static demoQaLogin.TestData.FAVICON_PATH;
import static demoQaLogin.spec.request.LoginRequestSpec.authRequestSpec;
import static demoQaLogin.spec.response.LoginResponseSpec.authResponseSpec;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;

public class AuthorizationApi {

    public AuthModelResponse getAuthentication(AuthModel authModel) {

        return step("Authentication", () -> given()
                .spec(authRequestSpec)
                .body(authModel)
                .when()
                .post()
                .then()
                .spec(authResponseSpec)
                .extract().as(AuthModelResponse.class));
    }

    public void setCookie(AuthModelResponse authModelResponse) {
        step("Set Cookies");
        open(FAVICON_PATH);
        getWebDriver().manage().addCookie(new Cookie("userID", authModelResponse.userId()));
        getWebDriver().manage().addCookie(new Cookie("expires", authModelResponse.expires()));
        getWebDriver().manage().addCookie(new Cookie("token", authModelResponse.token()));
    }
}
