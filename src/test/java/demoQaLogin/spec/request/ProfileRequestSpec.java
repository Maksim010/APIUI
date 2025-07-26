package demoQaLogin.spec.request;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.with;

public class ProfileRequestSpec {

    private static final String BOOKS_COLLECTION_PATH = "/BookStore/v1/Books";
    private static final String BOOK_COLLECTION_PATH = "/BookStore/v1/Book";


    public static RequestSpecification profileGetBooksRequestSpec = with()
            .log().uri()
            .log().method()
            .basePath(BOOKS_COLLECTION_PATH);

    public static RequestSpecification profileAddBooksRequestSpec = with()
            .log().uri()
            .log().method()
            .log().body()
            .contentType(ContentType.JSON)
            .basePath(BOOKS_COLLECTION_PATH);

    public static RequestSpecification profileDeleteBooksRequestSpec = with()
            .log().uri()
            .log().method()
            .log().body()
            .contentType(ContentType.JSON)
            .basePath(BOOKS_COLLECTION_PATH);

    public static RequestSpecification profileDeleteBookRequestSpec = with()
            .log().uri()
            .log().method()
            .log().body()
            .contentType(ContentType.JSON)
            .basePath(BOOK_COLLECTION_PATH);
}
