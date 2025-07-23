package demoQaLogin.models.request;

import lombok.Builder;

@Builder
public record AuthModel(

        String userName,

        String password
) {
}
