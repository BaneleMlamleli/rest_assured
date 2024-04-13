package com.learn;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class Authorisation {

    String accessToken = "github_pat_11AG6Y5CSM4ZVQbCuz6YTM25o3mX1ZqsffRRThtrhIkliQKT3W62KBO0Re0yaY";

    @Test
    public void basicAuthentication() {
        given()
            .auth().basic("postman", "password")
        .when()
            .get("https://postman-echo.com/basic-auth")
        .then()
            .statusCode(200)
            .log().body();
        }
    
    @Test
    public void digestAuthentication() {
        given()
            .auth().digest("postman", "password")
        .when()
            .get("https://postman-echo.com/basic-auth")
        .then()
            .statusCode(200)
            .log().body();
    }
    
    @Test
    public void preemptiveAuthentication() {
        given()
            .auth().preemptive().basic("postman", "password")
        .when()
            .get("https://postman-echo.com/basic-auth")
        .then()
            .statusCode(200)
            .log().body();
    }
    
    @Test
    public void bearerTokenAuthentication() {
        given()
            .headers("Authorization", "Bearer "+accessToken)
        .when()
            .get("https://api.github.com/user/repos")
        .then()
            .statusCode(200)
            .log().all();
    }
    
    @Test
    public void oauthV1Authentication() {
        given()
            .auth().oauth("consumerKey", "consumerSecret", "accessToken", "secretToken")
        .when()
            .get("https://api.github.com/user/repos")
        .then()
                .statusCode(200);
    }
    
    @Test
    // multiple authentication steps needs to be completed before obtaining a token that will be used int oauth2 parameter
    public void oauthV2Authentication() {
        given()
            .auth().oauth2(accessToken)
        .when()
            .get("https://api.github.com/user/repos")
        .then()
                .statusCode(200);
    }
    
    @Test
    public void apiKeyAuthentication() {
        given()
            .queryParam("appid", accessToken) //appid is a key as this is key:value pair dataset
        .when()
            .get("url")
        .then()
                .statusCode(200);
    }
}
