package demoQaLogin.models.request;

import lombok.Builder;

import java.util.List;

@Builder
public record BooksData (

        String userId,

        List<CollectionOfIsbn> collectionOfIsbns
){


}
