package demoQaLogin.tests.apiTests;

import demoQaLogin.api.AuthorizationApi;
import demoQaLogin.api.ProfileApi;
import demoQaLogin.baseTests.BaseTests;
import demoQaLogin.models.request.AuthModel;
import demoQaLogin.models.request.BookData;
import demoQaLogin.models.request.BooksData;
import demoQaLogin.models.response.AuthModelResponse;
import demoQaLogin.models.response.BooksList;
import demoQaLogin.pages.ProfilePage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;
import static demoQaLogin.TestData.PROFILE_PATH;
import static demoQaLogin.TestData.assertBooks;
import static demoQaLogin.TestData.createAuthModel;
import static demoQaLogin.TestData.createBooksData;
import static io.qameta.allure.Allure.step;

public class ProfileApiTests extends BaseTests {

    AuthorizationApi authorizationApi = new AuthorizationApi();
    ProfileApi profileApi = new ProfileApi();
    ProfilePage profilePage = new ProfilePage();

    @Test
    @Tag("API")
    @DisplayName("Added book in profile")
    void successfulAddBook() {

        AuthModel authModel = createAuthModel();

        AuthModelResponse response =
                authorizationApi.getAuthentication(authModel);

        BooksList booksList = profileApi.getBooksFromBookStore(response);

        BooksData booksData = createBooksData(response, booksList);

        step("Delete books from Profile", () ->
                profileApi.deleteBooksFromProfile(response)
        );

        step("Add book to profile", () ->
                profileApi.addBookFromBookStore(booksData, response)
        );
        step("Set Cookie", () ->
                authorizationApi.setCookie(response)
        );

        step("Open profile page", () ->
                open(PROFILE_PATH)
        );

        step("Verify", () ->
                profilePage.assertAddedBook(assertBooks(booksList, booksData)));
    }

    @Test
    @Tag("API")
    @DisplayName("Delete book in profile")
    void successfulDeleteBook() {

        AuthModel authModel = createAuthModel();

        AuthModelResponse response =
                authorizationApi.getAuthentication(authModel);

        BooksList booksList = profileApi.getBooksFromBookStore(response);

        BooksData booksData = createBooksData(response, booksList);

        BookData bookData = BookData.builder()
                .isbn(booksData.collectionOfIsbns().get(0).isbn())
                .userId(response.userId())
                .build();

        step("Delete books from Profile", () ->
                profileApi.deleteBooksFromProfile(response)
        );

        step("Add book to profile", () ->
                profileApi.addBookFromBookStore(booksData, response)
        );

        step("Delete book from Profile", () ->
                profileApi.deleteBookFromProfile(response, bookData));

        step("Set Cookie", () ->
                authorizationApi.setCookie(response)
        );

        step("Open profile page", () ->
                open(PROFILE_PATH)
        );

        step("Verify", () ->
                profilePage.assertDeleteBook(assertBooks(booksList, booksData)));
    }
}
