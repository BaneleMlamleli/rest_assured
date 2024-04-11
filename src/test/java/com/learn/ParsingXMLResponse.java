package com.learn;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class ParsingXMLResponse {
    
    @Test
    public void validateXMLResponseApproachOne() {
        given()
            .contentType(ContentType.XML)
            .accept(ContentType.XML)
        .when()
            .get("https://thetestrequest.com/authors.xml")
        .then()
            .statusCode(200)
            .header("Content-Type", "application/xml; charset=utf-8")
            .body("Objects.object[0].id", equalTo("1"))
            .body("Objects.object[0].name", equalTo("Karl Zboncak"))
            .log().body();
    }
    
    @Test
    public void validateXMLResponseApproachTwo() {
        Response response = given()
                .when()
                .get("https://thetestrequest.com/authors.xml");
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.header("Content-Type"), "application/xml; charset=utf-8");
        Assert.assertEquals(response.xmlPath().get("Objects.object[0].id").toString(), "1");
    }

    @Test
    public void validateXMLResponseApproachThree() {
        Response response = 
            given()
            .when()
                .get("https://thetestrequest.com/authors.xml");

        XmlPath xmlPath = new XmlPath(response.asString());
        List<String> listOfOAuthors = xmlPath.getList("Objects.object.name");
        for (String authorName : listOfOAuthors) {
            if (authorName.equalsIgnoreCase("Leonard Champlin")) {
                System.out.println("Author Name: " + authorName);   
                break;
            }
        }
    }
}
