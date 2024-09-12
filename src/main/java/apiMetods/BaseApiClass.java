package apiMetods;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public abstract class BaseApiClass {

    private static final String BASE_URL = "https://qa-scooter.praktikum-services.ru";

    protected Response getRequest(String path) {
        return given()
                .log()
                .all()
                .baseUri(BASE_URL)
                .header("Content-Type", "application/json")
                .get(path)
                .thenReturn();
    }

    protected Response postRequest(String path, Object body) {
        return given()
                .log()
                .all()
                .baseUri(BASE_URL)
                .header("Content-Type", "application/json")
                .body(body)
                .post(path)
                .thenReturn();
    }

    protected Response deleteRequest(String path, Object body) {
        return given()
                .log()
                .all()
                .baseUri(BASE_URL)
                .header("Content-Type", "application/json")
                .body(body)
                .delete(path)
                .thenReturn();
    }
}