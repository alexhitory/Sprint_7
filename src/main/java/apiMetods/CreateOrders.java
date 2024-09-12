package apiMetods;

import credentials.CreateOrdersCredentials;
import io.restassured.response.Response;

public class CreateOrders extends BaseApiClass {
    private final String apiPath = "/api/v1/orders";

    public Response createOrder(CreateOrdersCredentials createOrdersCredentials) {
        return postRequest(apiPath, createOrdersCredentials);
    }
}