package registration;


import endpoints.EndPoints;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import model.registration.RegistrationResponse;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;


public class RegistrationTest {

    @Test(dataProvider = "registrationDataFields")
    public void registrationTestPost(String email, String password, String first_name, String last_name, String tz, int expectedResultCode) {

        RestAssured.baseURI = "https://iqoption.com";
        RegistrationResponse response = given().log().all().urlEncodingEnabled(true)
                .param("email", email)
                .param("password", password)
                .param("first_name", first_name)
                .param("last_name", last_name)
                .param("tz", tz)
                .header("Accept", ContentType.JSON.getAcceptHeader())
                .post(EndPoints.registration)
                .then().log().body()
                .statusCode(200)
                .extract()
                .body()
                .as(RegistrationResponse.class);
        assertEquals(response.getCode(), expectedResultCode, "Код ответа не совпадает с ожидаемым");
        assertFalse(response.getIsSuccessful(), "Неверное значение поля result");

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
