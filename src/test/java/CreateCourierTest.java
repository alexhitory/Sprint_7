import apiMetods.CreateCourier;
import apiMetods.DeleteCourier;
import apiMetods.LoginCourier;

import credentials.CreateCourierCredentials;
import credentials.DeleteCourierCredentials;
import credentials.LoginCourierCredentials;

import io.qameta.allure.junit4.DisplayName;
import org.junit.AfterClass;
import org.junit.Test;

import static org.apache.http.HttpStatus.*;
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
                .statusCode(SC_CREATED)
                .assertThat()
                .body("ok", equalTo(true));
    }

    @Test
    @DisplayName("Создание курьера. Создание уже существующего курьера")
    public void createExistingCourierTest() {
        CreateCourier createCourier = new CreateCourier();
        CreateCourierCredentials courier = new CreateCourierCredentials(login, password, firstName);
        createCourier
                .createCourier(courier)
                .then()
                .statusCode(SC_CONFLICT)
                .assertThat()
                .body("message", equalTo("Этот логин уже используется"));
    }

    @Test
    @DisplayName("Создание курьера.Не передан логин")
    public void createCourierWithoutLoginTest() {
        CreateCourier createCourier = new CreateCourier();
        CreateCourierCredentials courier = new CreateCourierCredentials(null, password, firstName);
        createCourier
                .createCourier(courier)
                .then()
                .statusCode(SC_BAD_REQUEST)
                .assertThat()
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    @Test
    @DisplayName("Создание курьера.Не передан пароль")
    public void createCourierWithoutPasswordTest() {
        CreateCourier createCourier = new CreateCourier();
        CreateCourierCredentials courier = new CreateCourierCredentials(login, null, firstName);
        createCourier
                .createCourier(courier)
                .then()
                .statusCode(SC_BAD_REQUEST)
                .assertThat()
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }
}
