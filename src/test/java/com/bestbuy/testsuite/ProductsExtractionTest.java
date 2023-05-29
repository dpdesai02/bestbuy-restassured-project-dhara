package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import jdk.nashorn.internal.objects.annotations.Where;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

/*
21. Extract the limit
22. Extract the total
23. Extract the name of 5th product
24. Extract the names of all the products
25. Extract the productId of all the products
26. Print the size of the data list
27. Get all the value of the product where product name = Energizer - MAX Batteries AA (4- Pack)
28. Get the model of the product where product name = Energizer - N Cell E90 Batteries (2- Pack)
29. Get all the categories of 8th products
30. Get categories of the store where product id = 150115
31. Get all the descriptions of all the products
32. Get id of all the all categories of all the products
33. Find the product names Where type = HardGood
34. Find the Total number of categories for the product where product name = Duracell - AA  1.5V CopperTop Batteries (4-Pack)
35. Find the createdAt for all products whose price < 5.49
36. Find the name of all categories Where product name = “Energizer - MAX Batteries AA (4- Pack)”
37. Find the manufacturer of all the products
38. Find the image of products whose manufacturer is = Energizer
39. Find the createdAt for all categories products whose price > 5.99
40. Find the uri of all the products

 */
public class ProductsExtractionTest {
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


    // 21) Extract the value of limit
    @Test
    public void test21() {
        int limit = response.extract().path("limit");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of limit is : "+ limit);
        System.out.println("------------------End of Test---------------------------");


    }

    //22. Extract the total
    @Test
    public void test22() {
        int total= response.extract().path("total");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Extract the total is : "+ total);
        System.out.println("------------------End of Test---------------------------");

    }

//23. Extract the name of 5th product
@Test
public void test23() {
    String productName = response.extract().path("data[4].name");

    System.out.println("------------------StartingTest---------------------------");
    System.out.println(" Name of 5th product is : "+ productName);
    System.out.println("------------------End of Test---------------------------");

}
//24. Extract the names of all the products
@Test
public void test24() {
    List<String>allProductName = response.extract().path("data.name");

    System.out.println("------------------StartingTest---------------------------");
    System.out.println(" Name of all product are : " + allProductName);
    System.out.println("------------------End of Test---------------------------");
}
//25. Extract the productId of all the products
@Test
public void test25() {
    List<Integer>allProductId = response.extract().path("data.id");

    System.out.println("------------------StartingTest---------------------------");
    System.out.println(" All productId are : " + allProductId);
    System.out.println("------------------End of Test---------------------------");}

//26. Print the size of the data list
@Test
public void test26() {
    List<Object> datalistsize = response.extract().path("data");

    System.out.println("------------------StartingTest---------------------------");
    System.out.println("The value of data list size is : " + datalistsize);
    System.out.println("------------------End of Test---------------------------");

}
//27. Get all the value of the product where product name = Energizer - MAX Batteries AA (4- Pack)
@Test
public void test27() {
    List<HashMap<String,Object>> values = response.extract().path("data.findAll{it.name=='Energizer - MAX Batteries AA (4- Pack)'}");

    System.out.println("------------------StartingTest---------------------------");
    System.out.println("The value of the product is : " + values);
    System.out.println("------------------End of Test---------------------------");}

//28. Get the model of the product where product name = Energizer - N Cell E90 Batteries (2- Pack)
@Test
public void test28() {
    List<HashMap<String,?>> modelName =response.extract().path("data.findAll{it.name == 'Energizer - N Cell E90 Batteries (2-Pack)'}.model");
    System.out.println("------------------Starting Test---------------------------");
    System.out.println("The model for product name 'Energizer - N Cell E90 Batteries (2-  Pack)' are: "+ modelName);
    System.out.println("------------------End of Test---------------------------");}

//29. Get all the categories of 8th products
@Test
public void test29() {
    List<HashMap<String,Object>> categoriesOfProducts =response.extract().path("data[7].categories");
    System.out.println("------------------Starting Test---------------------------");
    System.out.println("The categories of 8th products are: "+categoriesOfProducts);
    System.out.println("------------------End of Test---------------------------");}
//30. Get categories of the store where product id = 150115
@Test
public void test30() {
    List<HashMap<String,Object>> categoriesOfProduct =response.extract().path("data.findAll{it.id == 150115}.categories");
    System.out.println("------------------Starting Test---------------------------");
    System.out.println("The categories of the store where id is 150115 are: "+categoriesOfProduct);
    System.out.println("------------------End of Test---------------------------");}

//31. Get all the descriptions of all the products
@Test
public void test31() {
    List<HashMap<String,Object>> description =response.extract().path("data.description");
    System.out.println("------------------Starting Test---------------------------");
    System.out.println("The all the descriptions of all the products are: "+description);
    System.out.println("------------------End of Test---------------------------");}

//32. Get id of all the all categories of all the products
@Test
public void test32() {
    List<HashMap<String, Object>> idsCategories = response.extract().path("data.categories.id");
    System.out.println("------------------Starting Test---------------------------");
    System.out.println("The id of all the all categories of all the products are: " + idsCategories);
    System.out.println("------------------End of Test---------------------------");
}
//33. Find the product names Where type = HardGood
@Test
public void test33() {
    List<String> names =response.extract().path("data.findAll{it.type == 'HardGood'}.name");
    System.out.println("------------------Starting Test---------------------------");
    System.out.println("The product names where the type is HardGood are: "+names);
    System.out.println("------------------End of Test---------------------------");
}

//34. Find the Total number of categories for the product where product name = Duracell - AA  1.5V CopperTop Batteries (4-Pack)
@Test
public void test34() {
    int totalCategories =response.extract().path("data.find{it.name == 'Duracell - AA 1.5V CopperTop Batteries (4-Pack)'}.categories.size");
    System.out.println("------------------Starting Test---------------------------");
    System.out.println("The Total number of categories for the product where product name = Duracell - AA1.5V CopperTop Batteries (4-Pack) is : "+totalCategories);
    System.out.println("------------------End of Test---------------------------");
    }
//35. Find the createdAt for all products whose price < 5.49
@Test
public void test35() {
    List<String> values =response.extract().path("data.findAll{it.price < 5.49}.createdAt");
    System.out.println("------------------Starting Test---------------------------");
    System.out.println("The createdAt for all products whose price < 5.49 are: "+values);
    System.out.println("------------------End of Test---------------------------");}

// 36. Find the name of all categories Where product name = “Energizer - MAX Batteries AA (4- Pack)”
@Test
public void test36() {
    List<String> categoriesName =response.extract().path("data.findAll{it.name == 'Energizer - MAX Batteries AA (4-Pack)'}.categories.name");
    System.out.println("------------------Starting Test---------------------------");
    System.out.println("The name of all categories where product name is “Energizer - MAX Batteries AA (4-Pack)” are : "+categoriesName);
    System.out.println("------------------End of Test---------------------------");}
// 37. Find the manufacturer of all the products
@Test
public void test37() {
    List<Object> manufacturer = response.extract().path("data.manufacturer");
    System.out.println("------------------Starting Test---------------------------");
    System.out.println("The manufacturer of all the products are : " + manufacturer);
    System.out.println("------------------End of Test---------------------------");}

//38. Find the image of products whose manufacturer is = Energizer
@Test
public void test38() {
    List<String> imageProduct =response.extract().path("data.findAll{it.manufacturer == 'Energizer'}.image");
    System.out.println("------------------Starting Test---------------------------");
    System.out.println("The image of products whose manufacturer is = Energizer : "+imageProduct);
    System.out.println("------------------End of Test---------------------------");
}
//39. Find the createdAt for all categories products whose price > 5.99
@Test
public void test39() {
    List<String> price =response.extract().path("data.findAll{it.price > 5.99}.createdAt");
    System.out.println("------------------Starting Test---------------------------");
    System.out.println("The createdAt for all products whose price > 5.99 are: "+ price);
    System.out.println("------------------End of Test---------------------------");}

//40. Find the uri of all the products
@Test
public void test40() {
    List<String> uri =response.extract().path("data.url");
    System.out.println("------------------Starting Test---------------------------");
    System.out.println("The uri of all the products are: "+uri);
    System.out.println("------------------End of Test---------------------------");}


}
