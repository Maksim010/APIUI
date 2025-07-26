package demoQaLogin.api;

import demoQaLogin.models.request.BookData;
import demoQaLogin.models.request.BooksData;
import demoQaLogin.models.response.AuthModelResponse;
import demoQaLogin.models.response.BooksList;

import static demoQaLogin.spec.request.ProfileRequestSpec.profileAddBooksRequestSpec;
import static demoQaLogin.spec.request.ProfileRequestSpec.profileDeleteBookRequestSpec;
import static demoQaLogin.spec.request.ProfileRequestSpec.profileDeleteBooksRequestSpec;
import static demoQaLogin.spec.request.ProfileRequestSpec.profileGetBooksRequestSpec;
import static demoQaLogin.spec.response.ProfileResponseSpec.profileAddBooksResponseSpec;
import static demoQaLogin.spec.response.ProfileResponseSpec.profileDeleteBookResponseSpec;
import static demoQaLogin.spec.response.ProfileResponseSpec.profileDeleteBooksResponseSpec;
import static demoQaLogin.spec.response.ProfileResponseSpec.profileGetBooksResponseSpec;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;

public class ProfileApi {

    public BooksList getBooksFromBookStore(AuthModelResponse authModelResponse) {
        return step("Get Books from Book Store", () ->
                given()
                        .spec(profileGetBooksRequestSpec)
                        .header("Authorization", "Bearer " + authModelResponse.token())
                        .when()
                        .get()
                        .then()
                        .spec(profileGetBooksResponseSpec)
                        .extract().as(BooksList.class)
        );
    }

    public void addBookFromBookStore(BooksData booksData, AuthModelResponse authModelResponse) {
         step("Add Book from Book Store", () ->
                given()
                        .spec(profileAddBooksRequestSpec)
                        .header("Authorization", "Bearer " + authModelResponse.token())
                        .body(booksData)
                        .when()
                        .post()
                        .then()
                        .spec(profileAddBooksResponseSpec));
    }

    public void deleteBooksFromProfile(AuthModelResponse authModelResponse) {
         step("Delete Books from Profile", () ->
                given()
                        .spec(profileDeleteBooksRequestSpec)
                        .header("Authorization", "Bearer " + authModelResponse.token())
                        .queryParams("UserId", authModelResponse.userId())
                        .when()
                        .delete()
                        .then()
                        .spec(profileDeleteBooksResponseSpec));
    }

    public void deleteBookFromProfile(AuthModelResponse authModelResponse, BookData bookData) {
         step("Delete Book from Profile", () ->
                given()
                        .spec(profileDeleteBookRequestSpec)
                        .body(bookData)
                        .header("Authorization", "Bearer " + authModelResponse.token())
                        .when()
                        .delete()
                        .then()
                        .spec(profileDeleteBookResponseSpec));
    }
}
