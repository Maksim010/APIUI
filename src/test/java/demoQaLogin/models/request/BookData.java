package demoQaLogin.models.request;

import lombok.Builder;

import java.util.List;

@Builder
public record BookData(

        String userId,

        String isbn
){


}
