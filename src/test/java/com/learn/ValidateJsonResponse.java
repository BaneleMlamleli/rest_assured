package com.learn;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ValidateJsonResponse {
    
    /* Validate directly from the response.
     * This validation approach provides very minimal ways of validating a response
     */ 
    //@Test
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
    
    //@Test
    // Capturing entire response into a variable then conduct validations based on that captured response
    public void validateJsonResponseApproachTwo() {
        Response response = 
        given()
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
        .when()
            .get("http://localhost:3000/books");
            
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.header("Content-Type"), "application/json");
        Assert.assertTrue(response.getTime() < 400);
        Assert.assertEquals(response.jsonPath().get("author[0]").toString(), "Nigel Rees");
    }
    
    @Test
    // Capturing entire response into a variable then use JSONObject conduct validations based on that captured response
    public void validateJsonResponseApproachThree() {
        Response response = 
        given()
            .contentType(ContentType.JSON)
        .when()
            .get("http://localhost:3000/books");

        boolean status = false;
        String validJson = "{\"books\":"+response.getBody().asString() + "}";
        JSONObject jsonObject = new JSONObject(validJson);
        for (int i = 0; i < jsonObject.getJSONArray("books").length(); i++) {
            String bookTitle = jsonObject.getJSONArray("books").getJSONObject(i).get("title").toString();
            if (bookTitle.equalsIgnoreCase("Moby Dick")) {
                status = true;
            }
        }

        Assert.assertEquals(status, true);
    }
}