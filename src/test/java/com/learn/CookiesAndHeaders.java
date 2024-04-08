package com.learn;

import static io.restassured.RestAssured.*;

import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class CookiesAndHeaders {

    // @Test
    public void testCookies() {
        Response response = get("https://www.google.com/");

        // Getting an individual cookie value using the name of the cookie
        System.out.println("Cookie value: " + response.cookie("NID"));

        // Getting all the cookie with their names and values
        Map<String, String> cookies = response.getCookies();
        System.out.println("Cookie names: " + cookies.keySet());
        for (String cookie : cookies.keySet()) {
            System.out.println("Cookie info -->" + cookie + " : " + response.getCookie(cookie));
        }
    }

    @Test
    public void testHeaders() {
        Response response = given().when().get("https://www.google.com/");

        // Getting a single header information
        System.out.println("Header value: " + response.getHeader("Content-Type"));

        // Getting all the headers with their names and values
        Headers headersData = response.getHeaders();

        for (Header header : headersData) {
            System.out.println("Header info --> " + header.getName() + " : " + header.getValue());
        }
    }

    /*
     * When logging you can log all(), headers(), cookies(), body()
    */
    @Test
    public void testLogging() {
        given()
        .when()
            .get("https://www.google.com/")
        .then()
            .log().cookies();
    }


}
