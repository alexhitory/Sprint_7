package ApiMetods;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public abstract class BaseApiClass {

    private static final String BASE_URL = "https://qa-scooter.praktikum-services.ru";

    protected Response GetRequest(String path) {
        return given()
                .log()
                .all()
                .baseUri(BASE_URL)
                .header("Content-Type", "application/json")
                .get(path)
                .thenReturn();
    }

    protected Response PostRequest(String path, Object body) {
        return given()
                .log()
                .all()
                .baseUri(BASE_URL)
                .header("Content-Type", "application/json")
                .body(body)
                .post(path)
                .thenReturn();
    }

    protected Response DeleteRequest(String path, Object body) {
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