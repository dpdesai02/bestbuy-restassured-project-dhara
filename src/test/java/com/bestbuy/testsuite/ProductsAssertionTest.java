package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.IsCollectionContaining.hasItem;



/*
11. Verify the if the total is equal to 51957
12. Verify the if the stores of limit is equal to 10
13. Check the single ‘Name’ in the Array list (Duracell - AAA Batteries (4-Pack))
14. Check the multiple ‘Names’ in the ArrayList (Duracell - AA 1.5V CopperTop Batteries (4- Pack), Duracell - AA Batteries (8-Pack), Energizer - MAX Batteries AA (4-Pack))
15. Verify the productId=150115 inside categories of the third name is “Household Batteries”
16. Verify the categories second name = “Housewares” of productID = 333179
17. Verify the price = 4.99 of forth product
18. Verify the Product name = Duracell - D Batteries (4-Pack) of 6th product  19. Verify the ProductId = 333179 for the 9th product
20. Verify the prodctId = 346575 have 5 categories

 */
public class ProductsAssertionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = given()
                .when()
                .get("/products")
                .then().statusCode(200);
    }

    //    11. Verify the if the total is equal to 51957
    //as per product list on web side its shows 51959
    @Test
    public void test11() {

        response.body("total", equalTo(51959));

    }

    //            12. Verify the if the stores of limit is equal to 10
    @Test
    public void test12() {

        response.body("limit", equalTo(10));

    }

    //13. Check the single ‘Name’ in the Array list (Duracell - AAA Batteries (4-Pack))
    @Test
    public void test13() {

        response.body("data.name", hasItem("Duracell - AAA Batteries (4-Pack)"));
    }

//14. Check the multiple ‘Names’ in the ArrayList (Duracell - AA 1.5V CopperTop Batteries (4- Pack), Duracell - AA Batteries (8-Pack), Energizer - MAX Batteries AA (4-Pack))

    @Test
    public void test14() {
        response.body("data.name", hasItems("Duracell - AAA Batteries (4-Pack)", "Duracell - AA 1.5V CopperTop Batteries (4-Pack)", "Duracell - AA Batteries (8-Pack)", "Energizer - MAX Batteries AA (4-Pack)", "Duracell - C Batteries (4-Pack)", "Duracell - D Batteries (4-Pack)", "Pioneer - 4\" 3-Way Surface-Mount Speakers with IMPP Composite Cones (Pair) - Black", "Duracell - 9V Batteries (2-Pack)", "Energizer - N Cell E90 Batteries (2-Pack)", "Metra - Radio Installation Dash Kit for Most 1989-2000 Ford, Lincoln & Mercury Vehicles - Black"));
    }


    //  15. Verify the productId=150115 inside categories of the third name is “Household Batteries”
    @Test
    public void test15() {
        response
                .body("data[3].id",equalTo(150115))
                .body("data[3].categories[2].name",equalTo("Household Batteries"));

    }

// 16. Verify the categories second name = “Housewares” of productID = 333179
@Test
public void test16() {
        response
                .body("data[8].id",equalTo(333179))
                .body("data[8].categories[1].name",equalTo("Housewares"));


}
//17. Verify the price = 4.99 of forth product
@Test
public void test17() {
    response.body("data[3].price",equalTo( 4.99f));
    }
//18. Verify the Product name = Duracell - D Batteries (4-Pack) of 6th product
@Test
public void test18() {
        response.body("data[5].name",equalTo("Duracell - D Batteries (4-Pack)"));

}

// 19. Verify the ProductId = 333179 for the 9th product
@Test
public void test19() {
response.body("data[8].id",equalTo(333179));

}
//20. Verify the prodctId = 346575 have 5 categories
@Test
public void test20() {
       // response.body("data[9].categories",equalTo(346575));
response.body("data[9].categories",hasSize(5));


}

}

