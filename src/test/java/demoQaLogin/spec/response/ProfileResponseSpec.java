package demoQaLogin.spec.response;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;

public class ProfileResponseSpec {

    public static ResponseSpecification profileGetBooksResponseSpec = new ResponseSpecBuilder()
            .log(LogDetail.BODY)
            .log(LogDetail.STATUS)
            .expectContentType(ContentType.JSON)
            .expectStatusCode(200)
            .build();

    public static ResponseSpecification profileAddBooksResponseSpec = new ResponseSpecBuilder()
            .log(LogDetail.BODY)
            .log(LogDetail.STATUS)
            .expectContentType(ContentType.JSON)
            .expectStatusCode(201)
            .build();

    public static ResponseSpecification profileDeleteBooksResponseSpec = new ResponseSpecBuilder()
            .log(LogDetail.STATUS)
            .expectStatusCode(204)
            .build();

    public static ResponseSpecification profileDeleteBookResponseSpec = new ResponseSpecBuilder()
            .log(LogDetail.STATUS)
            .expectStatusCode(204)
            .build();
}
