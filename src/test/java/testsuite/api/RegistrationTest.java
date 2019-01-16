package testsuite.api;


import beans.Beans;
import executers.BaseExecutor;
import io.qameta.allure.Feature;
import model.api.registration.RegistrationResponse;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import util.LogListener;


import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

@Listeners(LogListener.class)
@Feature("Тестирование регистрации")
public class RegistrationTest extends Beans {




    @Test(dataProvider = "registrationDataFields", description = "Регистрация пользователя с некорректными данными")
    public void registrationTestFailed(String email, String password, String first_name, String last_name, String tz, int expectedResultCode) {

        RegistrationResponse response = baseExecutor.registerInvalidDataPost(email, password, first_name, last_name, tz, expectedResultCode);
        assertEquals(response.getCode(), expectedResultCode, "Код ответа не совпадает с ожидаемым");
        assertFalse(response.isSuccessful(), "Неверное значение поля result");

    }

    @DataProvider
    public static Object[][] registrationDataFields() {

        return new Object[][]{
                {"valderama1@yandex.ru", "123456", "linus", "torvalds", "Europe/Moscow", 4},
                {"notvalidemail@.email", "123456", "linus", "torvalds", "Europe/Moscow", 19},
                {"tester@yandex.ru", "12345", "linus", "torvalds", "Europe/Moscow", 29},
                {"tester2@yandex.ru", "123456", "", "torvalds", "Europe/Moscow", 12}
        };
    }
}
