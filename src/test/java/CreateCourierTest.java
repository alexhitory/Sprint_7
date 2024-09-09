import ApiMetods.CreateCourier;
import ApiMetods.DeleteCourier;
import ApiMetods.LoginCourier;

import Credentials.CreateCourierCredentials;
import Credentials.DeleteCourierCredentials;
import Credentials.LoginCourierCredentials;

import io.qameta.allure.junit4.DisplayName;
import org.junit.AfterClass;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;

public class CreateCourierTest {

    private static String login = "Pikachu";
    private static String password = "12345";
    private static String firstName = "TestLogin";

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
    @DisplayName("Создания курьера")
    public void positiveCreateCourierTest() {
        CreateCourier createCourier = new CreateCourier();
        CreateCourierCredentials courier = new CreateCourierCredentials(login, password, firstName);
        createCourier
                .createCourier(courier)
                .then()
                .statusCode(201)
                .assertThat()
                .body("ok", equalTo(true));
    }

    @Test
    @DisplayName("Создание курьера. Создание уже существующего курьера")
    public void negativeCreateCourierTest_1() {
        CreateCourier createCourier = new CreateCourier();
        CreateCourierCredentials courier = new CreateCourierCredentials(login, password, firstName);
        createCourier
                .createCourier(courier)
                .then()
                .statusCode(409)
                .assertThat()
                .body("message", equalTo("Этот логин уже используется"));
    }

    @Test
    @DisplayName("Создание курьера.Не передан логин")
    public void negativeCreateCourierTest_2() {
        CreateCourier createCourier = new CreateCourier();
        CreateCourierCredentials courier = new CreateCourierCredentials(null, password, firstName);
        createCourier
                .createCourier(courier)
                .then()
                .statusCode(400)
                .assertThat()
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    @Test
    @DisplayName("Создание курьера.Не передан пароль")
    public void negativeCreateCourierTest_3() {
        CreateCourier createCourier = new CreateCourier();
        CreateCourierCredentials courier = new CreateCourierCredentials(login, null, firstName);
        createCourier
                .createCourier(courier)
                .then()
                .statusCode(400)
                .assertThat()
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }
}
