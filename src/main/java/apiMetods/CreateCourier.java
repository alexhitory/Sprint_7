package apiMetods;


import credentials.CreateCourierCredentials;
import io.restassured.response.Response;

public class CreateCourier extends BaseApiClass {
    private final String apiPath = "/api/v1/courier";

    public Response createCourier(CreateCourierCredentials courier) {
        return postRequest(apiPath, courier);
    }
}