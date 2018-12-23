package api;


import endpoints.EndPoints;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import model.api.registration.RegistrationResponse;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;


public class RegistrationTest {

    private RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri(EndPoints.baseURL)
            .log(LogDetail.ALL)
            .build();


    @Test(dataProvider = "registrationDataFields",description = "Регистрация пользователя с некорректными данными")
    public void registrationTestFailed(String email, String password, String first_name, String last_name, String tz, int expectedResultCode) {

        RegistrationResponse response = given().spec(requestSpec).urlEncodingEnabled(true)
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
