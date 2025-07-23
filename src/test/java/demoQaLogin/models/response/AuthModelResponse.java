package demoQaLogin.models.response;

import lombok.Builder;

@Builder
public record AuthModelResponse(

        String userId,

        String username,

        String password,

        String token,

        String expires,

        String created_date,

        String isActive
) {

}
