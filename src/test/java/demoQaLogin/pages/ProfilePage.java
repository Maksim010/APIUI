package demoQaLogin.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.Selenide.open;
import static demoQaLogin.TestData.PROFILE_PATH;

public class ProfilePage {

    public static final SelenideElement LOGIN_SELECTOR = $("#userName");
    private static final SelenideElement PASSWORD_SELECTOR = $("#password");
    private static final SelenideElement BUTTON_SELECTOR = $("#login");
    private static final SelenideElement USERNAME_SELECTOR = $("#userName-value");
    private static final SelenideElement BOOK_SELECTOR = $(".ReactTable");
    private static final String OPEN_PROFILE_RELATIVE_URL = "/login";
    private static final SelenideElement PRACTICE_FORM_TEXT_SELECTOR = $(".text-center");
    private static final String PRACTICE_FORM_TEXT = "Login";
    private static final String FIXEDBAN_REMOVE = "$('#fixedban').remove()";
    private static final String FOOTER_REMOVE = "$('footer').remove()";

    public ProfilePage setLogin(String login) {
        LOGIN_SELECTOR.sendKeys(login);
        return this;
    }

    public ProfilePage precondition() {
        open(OPEN_PROFILE_RELATIVE_URL);
        PRACTICE_FORM_TEXT_SELECTOR.shouldHave(text(PRACTICE_FORM_TEXT));
        executeJavaScript(FIXEDBAN_REMOVE);
        executeJavaScript(FOOTER_REMOVE);
        return this;
    }

    public ProfilePage setPassword(String password) {
        PASSWORD_SELECTOR.sendKeys(password);
        return this;
    }
    public ProfilePage clickButton() {
        BUTTON_SELECTOR.pressEnter();
        return this;
    }

    public ProfilePage assertUserName(String userName) {
        USERNAME_SELECTOR.shouldBe(Condition.visible, Duration.ofSeconds(10)).shouldHave(text(userName));
        return this;
    }

    @Step("Assert added book in profile")
    public ProfilePage assertAddedBook(String bookName) {
        BOOK_SELECTOR.shouldHave(text(bookName));
        return this;
    }

    public ProfilePage assertDeleteBook(String bookName) {
        BOOK_SELECTOR.shouldNotHave(text(bookName));
        return this;
    }

    @Step("Open profile page")
    public ProfilePage openProfilePage() {
        open(PROFILE_PATH);
        return this;
    }
}
