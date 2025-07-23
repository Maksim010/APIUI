package demoQaLogin.models.response;

import lombok.Builder;

import java.util.List;

@Builder
public record BooksList(

        List<BooksCollectionResponse> books
) {
}
