package demoQaLogin.models.request;

import lombok.Builder;

@Builder
public record CollectionOfIsbn(

        String isbn
) {
}
