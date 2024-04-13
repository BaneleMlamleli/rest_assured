package com.learn;

import static io.restassured.RestAssured.*;

import org.testng.annotations.Test;

import io.restassured.matcher.RestAssuredMatchers;
import io.restassured.module.jsv.JsonSchemaValidator;

public class JSONXMLSchemaValidation {

    @Test
    public void jsonSchemaValidation() {
        given()
        .when()
            .get("http://localhost:3000/store")
        .then()
            .assertThat()
            .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("storeJsonSchema.json"));
    }
    
    @Test
    public void xmlSchemaValidation() {
        given()
        .when()
            .get("https://thetestrequest.com/authors.xml")
        .then()
            .assertThat()
            .body(RestAssuredMatchers.matchesXsdInClasspath("authorXmlSchema.xsd"));
    }
}
