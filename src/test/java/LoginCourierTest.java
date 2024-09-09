import ApiMetods.CreateCourier;
import ApiMetods.DeleteCourier;
import ApiMetods.LoginCourier;

import Credentials.CreateCourierCredentials;
import Credentials.DeleteCourierCredentials;
import Credentials.LoginCourierCredentials;

import io.qameta.allure.junit4.DisplayName;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.core.IsEqual.equalTo;

public class LoginCourierTest {

    private static String login = "Pikachu";
    private static String password = "12345";
    private static String firstName = "TestLogin";
    private static String notValidLogin = "PicaPica";
    private static String notValidPassword = "54321";


    @BeforeClass
    public static void regCourier() {
        CreateCourier createCourier = new CreateCourier();
        CreateCourierCredentials courier = new CreateCourierCredentials(login, password, firstName);
        createCourier.createCourier(courier);
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

        DeleteCourier deleteCourier = new DeleteCourier();
        DeleteCourierCredentials deleteCourierCredentials = new DeleteCourierCredentials(id);
        deleteCourier.deleteCourier(deleteCourierCredentials, id);
    }

    @Test
    @DisplayName("Авторизация курьера")
    public void authorizationCourierTest() {
        LoginCourier loginCourier = new LoginCourier();
        LoginCourierCredentials loginCourierCredentials = new LoginCourierCredentials(login, password);
        loginCourier
                .loginCourier(loginCourierCredentials)
                .then()
                .statusCode(200)
                .assertThat()
                .body("id", notNullValue());
    }

    @Test
    @DisplayName("Создания курьера без логина")
    public void negativeAuthorizationCourierTest_1() {
        LoginCourier loginCourier = new LoginCourier();
        LoginCourierCredentials loginCourierCredentials = new LoginCourierCredentials(null, password);
        loginCourier
                .loginCourier(loginCourierCredentials)
                .then()
                .statusCode(400)
                .assertThat()
                .body("message", equalTo("Недостаточно данных для входа"));
    }

    @Test
    @DisplayName("Создания курьера без пароля")
    public void negativeAuthorizationCourierTest_2() {
        LoginCourier loginCourier = new LoginCourier();
        LoginCourierCredentials loginCourierCredentials = new LoginCourierCredentials(login, "");
        loginCourier
                .loginCourier(loginCourierCredentials)
                .then()
                .statusCode(400)
                .assertThat()
                .body("message", equalTo("Недостаточно данных для входа"));
    }

    @Test
    @DisplayName("Передан не валидный логин")
    public void negativeAuthorizationCourierTest_3() {
        LoginCourier loginCourier = new LoginCourier();
        LoginCourierCredentials loginCourierCredentials = new LoginCourierCredentials(notValidLogin, password);
        loginCourier
                .loginCourier(loginCourierCredentials)
                .then()
                .statusCode(404)
                .assertThat()
                .body("message", equalTo("Учетная запись не найдена"));
    }

    @Test
    @DisplayName("Передан не валидный пароль")
    public void negativeAuthorizationCourierTest_4() {
        LoginCourier loginCourier = new LoginCourier();
        LoginCourierCredentials loginCourierCredentials = new LoginCourierCredentials(login, notValidPassword);
        loginCourier
                .loginCourier(loginCourierCredentials)
                .then()
                .statusCode(404)
                .assertThat()
                .body("message", equalTo("Учетная запись не найдена"));
    }

}