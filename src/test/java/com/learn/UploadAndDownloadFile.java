package com.learn;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;

import org.testng.annotations.Test;

public class UploadAndDownloadFile {
    
    @Test
    public void singleFileUpload() {
        File dataFile = new File("Test.txt");

        given()
            .multiPart("files", dataFile)
            .contentType("multipart/form-data")
        .when()
            .post("http://locahost:8080/Test")
        .then()
            .statusCode(200)
                .body("filesName", equalTo("Test.txt"));
    }
    
    @Test
    public void multipleFileUpload() {
        File dataFile = new File("Test.txt");
        File dataFile1 = new File("Test1.txt");

        given()
            .multiPart("files", dataFile)
            .multiPart("files", dataFile1)
            .contentType("multipart/form-data")
        .when()
            .post("http://locahost:8080/Test")
        .then()
            .statusCode(200)
                .body("[0].filesName", equalTo("Test.txt"))
                .body("[1].filesName1", equalTo("Test1.txt"));
    }
}
