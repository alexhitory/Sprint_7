package ApiMetods;


import Credentials.CreateCourierCredentials;
import io.restassured.response.Response;

public class CreateCourier extends BaseApiClass {
    private final String apiPath = "/api/v1/courier";

    public Response createCourier(CreateCourierCredentials courier) {
        return PostRequest(apiPath, courier);
    }
}