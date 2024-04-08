package com.learn;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class ValidateJsonResponse {
    
    @Test
    public void validateJsonResponseApproachOne() {
        given()
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
        .when()
            .get("http://localhost:3000/books")
        .then()
            .statusCode(200)
            .body("title[3]", equalTo("The Lord of the Rings"));
    }   
}