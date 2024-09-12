package apiMetods;


import credentials.LoginCourierCredentials;
import io.restassured.response.Response;

public class LoginCourier extends BaseApiClass {
    private final String apiPath = "/api/v1/courier/login";

    public Response loginCourier(LoginCourierCredentials courier) {
        return postRequest(apiPath, courier);
    }
}
