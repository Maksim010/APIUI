package demoQaLogin.spec.request;

import demoQaLogin.baseTests.BaseTests;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.with;
import static io.restassured.http.ContentType.JSON;

public class LoginRequestSpec extends BaseTests {

    private static final String AUTH_PATH = "/Account/v1/Login";

    public static RequestSpecification authRequestSpec = with()
            .log().uri()
            .log().headers()
            .log().body()
            .contentType(JSON)
            .basePath(AUTH_PATH);



}
