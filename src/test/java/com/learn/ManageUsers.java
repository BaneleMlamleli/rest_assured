package com.learn;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

import java.util.HashMap;
import java.util.Map;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ManageUsers {

    @DataProvider(name = "createUser")
    public Object[][] getData() {
        Object[][] data = { { "Banele", "QA Engineer" }, { "Sethu", "Dental Assistant" }, { "Sam", "baby" } };
        return data;
    }

    @Test
    public void getAllUsers() {
        Response restResponse = get("https://reqres.in/api/users?page=2");
        System.out.println("Time: " + restResponse.getTime());
        System.out.println("Status code: " + restResponse.statusCode());
        System.out.println("Body: " + restResponse.getBody().asString());

        Assert.assertEquals(restResponse.statusCode(), 200);
    }

    @Test
    public void getSingleUser() {
        baseURI = "https://reqres.in/api";
        given().get("/users/2").then().statusCode(200);
    }

    @Test(dataProvider = "createUser")
    public void postNewUser(String name, String jobTitle) {
        baseURI = "https://reqres.in/api";
        JSONObject jsonObjectMap = new JSONObject();
        jsonObjectMap.put("name", name);
        jsonObjectMap.put("job", jobTitle);
        System.out.println(jsonObjectMap);

        given()
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .body(jsonObjectMap.toJSONString())
        .when()
            .post("/users")
        .then()
            .statusCode(201).log().all();
    }
    
    @Test
    public void putUserUpdate() {
        baseURI = "https://reqres.in/api";
        Map<String, Object> userDetailsMap = new HashMap<String, Object>();
        userDetailsMap.put("name", "Banele01");
        userDetailsMap.put("job", "QA Engineer01");
        JSONObject jsonObjectMap = new JSONObject(userDetailsMap);

        given()
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .body(jsonObjectMap.toJSONString())
        .when()
            .put("/users/2")
        .then()
            .statusCode(200).log().all();
    }
    
    @Test
    public void patchUserUpdate() {
        baseURI = "https://reqres.in/api";
        JSONObject jsonObjectMap = new JSONObject();
        jsonObjectMap.put("name", "Banele02");
        jsonObjectMap.put("job", "QA Engineer02");
        System.out.println(jsonObjectMap);

        given()
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .body(jsonObjectMap.toJSONString())
        .when()
            .patch("/users/2")
        .then()
            .statusCode(200).log().all();
    }
    
    @Test
    public void deleteUser() {
        baseURI = "https://reqres.in/api";
        when()
            .delete("/users/2")
        .then()
            .statusCode(204).log().all();
    }
}