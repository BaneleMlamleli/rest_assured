package com.learn;

import static io.restassured.RestAssured.given;
import java.time.LocalDate;
import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import com.github.javafaker.Faker;
import io.restassured.http.ContentType;

/*
 * I'm using the restful-booker API
 * URL: https://restful-booker.herokuapp.com/apidoc/index.html
*/
public class APIChaining {

    @Test
    // Creates a new auth token to use for access to the PUT and DELETE /booking
    public void createToken(ITestContext context) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", "admin");
        jsonObject.put("password", "password123");

        String token = given()
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .body(jsonObject.toString())
        .when()
            .post("https://restful-booker.herokuapp.com/auth")
            .jsonPath().getString("token");
        System.out.println(token);
        context.setAttribute("token", token);
        System.out.println("createToken() done!");
    }

    @Test
    // Returns the ids of all the bookings that exist within the API. Can take optional query strings to search and return a subset of booking ids.
    public void getBookingIDs() {
        given()
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
        .when()
            .get("https://restful-booker.herokuapp.com/booking")
        .then()
            .statusCode(200)
            .log().body();
        System.out.println("getBookingIDs() done!");
    }

    @Test
    // Returns a specific booking based upon the booking id provided
    public void getSingleBooking(ITestContext context) {
        given()
            .pathParam("id", context.getAttribute("bookingId"))
        .when()
            .get("https://restful-booker.herokuapp.com/booking/{id}")
        .then()
            .statusCode(200)
            .log().body();
        System.out.println("getSingleBooking() done!");
    }

    @Test
    // Creates a new booking in the API
    public void createBooking(ITestContext context) {
        Faker faker = new Faker();
        LocalDate checkin = LocalDate.of(2024, 04, 14);
        LocalDate checkout = LocalDate.of(2024, 04, 20);
        JSONObject jsonData = new JSONObject();
        JSONObject jsonDataBooking = new JSONObject();
        jsonData.put("firstname", faker.name().firstName());
        jsonData.put("lastname", faker.name().lastName());
        jsonData.put("totalprice", faker.number().numberBetween(10, 100));
        jsonData.put("depositpaid", faker.bool().bool());
        jsonDataBooking.put("checkin", checkin);
        jsonDataBooking.put("checkout", checkout);
        jsonData.put("bookingdates", jsonDataBooking);
        jsonData.put("additionalneeds", faker.food().vegetable());

        String bookingId = given()
            .contentType("application/json")
            .accept("application/json")
            .body(jsonData.toString())
        .when()
            .post("https://restful-booker.herokuapp.com/booking")
            .jsonPath().getString("bookingid");
        
        context.setAttribute("bookingId", bookingId);
        System.out.println("createBooking() done!");
    }

    @Test(priority = 1, dependsOnMethods = {"createToken", "createBooking"}, alwaysRun = true)
    // Updates a current booking
    public void updateBooking(ITestContext context) {
        Faker faker = new Faker();
        LocalDate checkin = LocalDate.of(2025, 04, 14);
        LocalDate checkout = LocalDate.of(2025, 04, 20);
        JSONObject jsonData = new JSONObject();
        JSONObject jsonDataBooking = new JSONObject();
        jsonData.put("firstname", faker.name().firstName());
        jsonData.put("lastname", faker.name().lastName());
        jsonData.put("totalprice", faker.number().numberBetween(10, 100));
        jsonData.put("depositpaid", faker.bool().bool());
        jsonDataBooking.put("checkin", checkin);
        jsonDataBooking.put("checkout", checkout);
        jsonData.put("bookingdates", jsonDataBooking);
        jsonData.put("additionalneeds", faker.food().vegetable());
        
        given()
            .header("Content-Type", "application/json")
            .header("Cookie", "token="+context.getAttribute("token"))
            .pathParam("id", context.getAttribute("bookingId"))
            .body(jsonData.toString())
        .when()
            .put("https://restful-booker.herokuapp.com/booking/{id}")
        .then()
            .statusCode(200)
            .log().all();
        System.out.println("createBooking() done!");
    }
    
    @Test
    public void deleteBooking(ITestContext context) {
        given()
            .header("Content-Type", "application/json")
            .header("Cookie", "token="+context.getAttribute("token"))
            .pathParam("id", context.getAttribute("bookingId"))
        .when()
            .delete("https://restful-booker.herokuapp.com/booking/{id}")
        .then()
            .statusCode(201)
            .log().all();
        System.out.println("deleteBooking() done!");
    }
}
