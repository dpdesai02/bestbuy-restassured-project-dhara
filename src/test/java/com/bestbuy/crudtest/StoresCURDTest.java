package com.bestbuy.crudtest;

import com.bestbuy.model.StorePojo;
import com.bestbuy.testbase.TestBase;
import com.bestbuy.utils.TestUtils;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class StoresCURDTest extends TestBase {
    static String name = "Prime" + TestUtils.getRandomValue();
    static String type = "Test" + TestUtils.getRandomValue();
    static String address =  TestUtils.getRandomValue() + " ,Angel Street" ;
    static String address2 = "Watford Street";
    static String City = "Harrow" ;
    static String state = "England" ;
    static String zip = "2423421";
    static double lat = 40.23569;
    static double lng = -74.95;
    static String hours = "Mon: 10-10; Tue: 10-10; Wed: 10-10; Thurs: 10-10; Fri: 10-10; Sat: 10-10; Sun: 10-10";

    static int storeId;


    @Test
    public void createStore() {
        StorePojo storePojo = new StorePojo();
        storePojo.setName(name);
        storePojo.setType(type);
        storePojo.setAddress(address);
        storePojo.setAddress2(address2);
        storePojo.setCity(City);
        storePojo.setState(state);
        storePojo.setLat((int) lat);
        storePojo.setLng((int) lng);
        storePojo.setHours(hours);


        Response response = given()
                .contentType(ContentType.JSON)
                .body(storePojo)
                .when()
                .post("/stores");
        response.then().log().all().statusCode(201);
    }



    @Test
    public void getStoreData() {
        Response response = given()
                .when()
                .get("http://localhost:3030/stores");
        response.then().statusCode(200);
        response.prettyPrint();
    }
    @Test
    public void getStoreId() {
        Response response = given()
                .when()
                .get("/stores/8921");
        response.then().statusCode(200);
        response.prettyPrint();
    }


    @Test
    public void updateStoreData() {
        StorePojo storePojo = new StorePojo();
        storePojo.setHours("Mon: 10-10; Tue: 10-8; Wed: 10-7; Thurs: 10-6; Fri: 10-5; Sat: 10-9; Sun: 10-8");
        storePojo.setName(name);
        storePojo.setType(type);
        storePojo.setAddress(address);
        storePojo.setAddress2(address2);
        storePojo.setCity(City);
        storePojo.setState(state);
        storePojo.setZip(zip);
        storePojo.setLat((int) lat);
        storePojo.setLng((int) lng);
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .body(storePojo)
                .patch("/stores/8921");
        response.prettyPrint();
        response.then().log().all().statusCode(200);
    }

    @Test
    public void deleteStore() {
        Response response = given()
                .when()
                .delete("/stores/8921");
        response.prettyPrint();
        response.then().log().all().statusCode(200);


    }


}
