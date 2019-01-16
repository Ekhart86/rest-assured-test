package testsuite.api;


import executers.BaseExecutor;
import io.qameta.allure.Feature;

import io.restassured.response.Response;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import util.LogListener;


import static org.testng.Assert.*;

@Listeners(LogListener.class)
@Feature("Тестирование авторизации")
public class AuthorizationTest {
   private  BaseExecutor baseExecutor = new BaseExecutor();

    @Test(description = "Успешная авторизация с верным логином и паролем")
    public void autorizationPassedTest() {
        Response response = baseExecutor.authPassedPost();
        System.out.println(response.prettyPrint());
        assertEquals(200, response.getStatusCode());
        assertTrue(response.jsonPath().get("isSuccessful"), "Не верный статус поля isSuccessful");

    }

    @Test(description = "Авторизация пользователя с некорректным паролем")
    public void autorizationFailedTestInvalidPassword() {
        Response response = baseExecutor.authInvalidPasswordPost();
        System.out.println(response.prettyPrint());
        assertEquals(208, response.getStatusCode());
        assertFalse(response.jsonPath().get("isSuccessful"), "Не верный статус поля isSuccessful");
        assertEquals(response.jsonPath().get("message[0]"), "Invalid login or password", "Не соответствует сообщение о некорректном логине или пароле");

    }

    @Test(description = "Авторизация пользователя с некорректным E-mail")
    public void autorizationFailedTestInvalidEmail() {
        Response response = baseExecutor.authInvalidEmailPost();
        System.out.println(response.prettyPrint());
        assertEquals(208, response.getStatusCode());
        assertFalse(response.jsonPath().get("isSuccessful"), "Не верный статус поля isSuccessful");
        assertEquals(response.jsonPath().get("message.email[0]"), "E-mail \"tester@yan.dex.ru\" is not valid", "Не соответствует сообщение о некорректном E-mail");
    }

}