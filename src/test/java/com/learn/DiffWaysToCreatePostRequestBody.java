package com.learn;

import org.json.JSONTokener;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import io.restassured.http.ContentType;

public class DiffWaysToCreatePostRequestBody {

    //Post request body using a HashMap
    @Test
    public void postUsingHashMap() {
        System.out.println("Executed method: 'postUsingHashMap()'");
        baseURI = "http://localhost:3000";
        HashMap<String, Object> mapData = new HashMap<String, Object>();
        mapData.put("email", "newstudent@student.com");
        mapData.put("first_name", "usedHashMap");
        mapData.put("last_name", "usedHashMap");
        mapData.put("avatar", "https://reqres.in/img/faces/8-image.jpg");
        String arrayCourse[] = { "Engineering and Design I", "Electrical Engineering II" };
        mapData.put("courses", arrayCourse);

        given()
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .body(mapData)
        .when()
            .post("/student")
        .then()
            .statusCode(201);
    }
    
    //Post request body using org.json
    @Test
    public void postUsingOrgJsonDependency(){
        System.out.println("Executed method: 'postUsingOrgJsonDependency()'");
        baseURI = "http://localhost:3000";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("email", "newemployee@employee.com");
        jsonObject.put("first_name", "usedJSONObject");
        jsonObject.put("last_name", "usedJSONObject");
        jsonObject.put("avatar", "https://reqres.in/img/faces/8-image.jpg");

        given()
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .body(jsonObject.toJSONString())
        .when()
            .post("/employees")
        .then()
            .statusCode(201);
    }
    
    //Post request body using POJO class
    @Test
    public void postUsingPojoClass() {
        System.out.println("Executed method: 'postUsingPojoClass()'");
        baseURI = "http://localhost:3000";
        Data data = new Data("newemployee@employee.com", "usedPojo", "usedPojo", "https://reqres.in/img/faces/11-image.jpg");
        
        given()
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .body(data)
        .when()
            .post("/employees")
        .then()
            .statusCode(201);
    }
    
    //Post request body using external json file data
    @Test
    public void postUsingExternalJsonFile(){
        System.out.println("Executed method: 'postUsingExternalJsonFile'");
        baseURI = "http://localhost:3000";

        try {
            FileInputStream fileInputStream = new FileInputStream(new File("/dataFile.json"));
            JSONTokener jsonTokener = new JSONTokener(fileInputStream); 
            org.json.JSONObject jsonObject = new org.json.JSONObject(jsonTokener);
            jsonObject.put("email", "newemployee@employee.com");
            jsonObject.put("first_name", "usedJSONObject");
            jsonObject.put("last_name", "usedJSONObject");
            jsonObject.put("avatar", "https://reqres.in/img/faces/8-image.jpg");
            
            given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(jsonObject.toString())
            .when()
                .post("/employees")
            .then()
                .statusCode(201);
        } catch (FileNotFoundException e) {
        }
    }
}