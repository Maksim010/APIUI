package demoQaLogin;

import demoQaLogin.models.request.AuthModel;
import demoQaLogin.models.request.BooksData;
import demoQaLogin.models.request.CollectionOfIsbn;
import demoQaLogin.models.response.AuthModelResponse;
import demoQaLogin.models.response.BooksCollectionResponse;
import demoQaLogin.models.response.BooksList;
import org.openqa.selenium.NotFoundException;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

public class TestData {

    public static final int PAGE_LOAD_TIMEOUT = 20000;
    public static final String DEFAULT_USERNAME = "sadasdas";
    public static final String DEFAULT_PASSWORD = "WF4!9jqYH3ztS4f";
    public static final String DEFAULT_ISBN = "9781449325862";
    public static final String PROFILE_PATH = "/profile";
    public static final String FAVICON_PATH = "/favicon.ico";


    public static AuthModel createAuthModel() {
        return AuthModel.builder()
                .userName(DEFAULT_USERNAME)
                .password(DEFAULT_PASSWORD)
                .build();
    }

    public static BooksData createBooksData(AuthModelResponse authModelResponse, BooksList booksList) {

        List<CollectionOfIsbn> collectionOfIsbn = createCollectionOfIsbn(booksList);

        return BooksData.builder()
                .userId(authModelResponse.userId())
                .collectionOfIsbns(collectionOfIsbn)
                .build();
    }

    public static List<CollectionOfIsbn> createCollectionOfIsbn(BooksList booksList) {

        return Collections.singletonList(
                CollectionOfIsbn.builder()
                        .isbn(booksList.books().stream()
                                .filter(i -> !i.isbn().equals(DEFAULT_ISBN))
                                .findAny().map(BooksCollectionResponse::isbn)
                                .orElseThrow(NoSuchElementException::new))
                        .build()
        );
    }

    public static String assertBooks(BooksList booksList, BooksData booksData) {
        return booksList.books().stream()
                .filter(i -> i.isbn().equals(booksData.collectionOfIsbns().get(0).isbn()))
                .map(BooksCollectionResponse::title).
                findFirst()
                .orElseThrow(NotFoundException::new);
    }
}
