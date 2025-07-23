package demoQaLogin.models.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record BooksCollectionResponse(

        String isbn,

        String title,

        String subTitle,

        String author,

        @JsonProperty(value = "publish_date")
        String publishDate,

        String publisher,

        Integer pages,

        String description,

        String website
) {


}
