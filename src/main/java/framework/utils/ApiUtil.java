package framework.utils;

import aquality.selenium.browser.AqualityServices;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

class ApiUtil {

    static Response post(String uri) {
        Response response = RestAssured.given().contentType(ContentType.JSON).post(uri);
        AqualityServices.getLogger().info("Getting response");
        return response;
    }

    static Response get(String uri) {
        AqualityServices.getLogger().info("Getting info");
        Response response = RestAssured.get(uri).andReturn();
        return response;
    }
}
