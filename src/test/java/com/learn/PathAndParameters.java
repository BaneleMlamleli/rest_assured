package com.learn;

import static io.restassured.RestAssured.*;
import org.testng.annotations.Test;

public class PathAndParameters {

    /*
     * N.B Query parameters are not included in the url path unlike the path variables
     * N.B Query parameters names must be exactly the same as the names given in the url unlike
     * path parameters
     * User key-value pairs to define the parameters
    */
    @Test
    public void testPathAndParameters() {
        // baseURI = "https://reqres.in/api/users?page=2&id=3";
        given()
            .pathParam("resource", "users")
            .queryParam("page", "2")
            .queryParam("id","3")
        .when()
            .get("https://reqres.in/api/{resource}")
        .then()
            .statusCode(200).log().all();
    }
}