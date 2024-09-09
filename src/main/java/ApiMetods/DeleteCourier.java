package ApiMetods;


import Credentials.DeleteCourierCredentials;
import io.restassured.response.Response;

public class DeleteCourier extends BaseApiClass {
    private final String apiPath = "/api/v1/courier/";

    public Response deleteCourier(DeleteCourierCredentials deleteCourier, int id) {
        return DeleteRequest(apiPath + id, deleteCourier);
    }
}