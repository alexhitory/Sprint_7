import ApiMetods.CreateCourier;
import ApiMetods.CreateOrders;
import ApiMetods.DeleteCourier;
import ApiMetods.LoginCourier;

import Credentials.CreateCourierCredentials;
import Credentials.CreateOrdersCredentials;
import Credentials.DeleteCourierCredentials;
import Credentials.LoginCourierCredentials;

import io.qameta.allure.junit4.DisplayName;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.hamcrest.CoreMatchers.notNullValue;


@RunWith(Parameterized.class)
public class CreateOrderTest {
    private static String login = "Pikachu";
    private static String password = "12345";
    private static String firstName = "Pica";
    private static String lastName = "Pica";
    private static String address = "Street";
    private static String metroStation = "Zyuzino ";
    private static String phone = "+88005553535";
    private static int rentTime = 1;
    private static String deliveryDate = "2024-09-10";
    private static String comment = "";
    private static String color1;
    private static String color2;
    private final String[] color = {color1, color2};

    public CreateOrderTest(String color1, String color2) {
        this.color1 = color1;
        this.color2 = color2;

    }

    @BeforeClass
    public static void loginCourier() {
        CreateCourier createCourier = new CreateCourier();
        CreateCourierCredentials courier = new CreateCourierCredentials(login, password, firstName);
        createCourier.createCourier(courier);
    }

    @Parameterized.Parameters
    public static Object[][] getCredentials() {
        return new Object[][]{
                {"BLACK", null},
                {null, "GREY"},
                {"BLACK", "GREY"},
                {null, null}

        };
    }

    @AfterClass
    public static void deleteCourier() {
        LoginCourier loginCourier = new LoginCourier();
        LoginCourierCredentials loginCourierCredentials = new LoginCourierCredentials(login, password);
        int id = loginCourier
                    .loginCourier(loginCourierCredentials)
                    .then()
                    .extract()
                    .path("id");

        DeleteCourier deleteCourierApi = new DeleteCourier();
        DeleteCourierCredentials deleteCourier = new DeleteCourierCredentials(id);
        deleteCourierApi.deleteCourier(deleteCourier, id);
    }

    @Test
    @DisplayName("Создание заказа")
    public void positiveLoginCourierTest() {
        CreateOrders createOrdersApi = new CreateOrders();
        CreateOrdersCredentials createOrdersCredentials = new CreateOrdersCredentials(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, color);
        createOrdersApi
                .createOrder(createOrdersCredentials)
                .then()
                .statusCode(201)
                .assertThat()
                .body("track", notNullValue());
    }
}