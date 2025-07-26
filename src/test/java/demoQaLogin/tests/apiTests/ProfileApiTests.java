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

import static demoQaLogin.TestData.assertBooks;
import static demoQaLogin.TestData.createAuthModel;
import static demoQaLogin.TestData.createBookDate;
import static demoQaLogin.TestData.createBooksData;

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

        profileApi.deleteBooksFromProfile(response);
        profileApi.addBookFromBookStore(booksData, response);

        authorizationApi.setCookie(response);

        profilePage.openProfilePage()
                .assertAddedBook(assertBooks(booksList, booksData));
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
        BookData bookData = createBookDate(booksData, response);

        profileApi.deleteBooksFromProfile(response);
        profileApi.addBookFromBookStore(booksData, response);
        profileApi.deleteBookFromProfile(response, bookData);

        authorizationApi.setCookie(response);

        profilePage.openProfilePage()
                .assertDeleteBook(assertBooks(booksList, booksData));
    }
}
