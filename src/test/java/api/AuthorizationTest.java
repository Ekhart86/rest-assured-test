package api;


import endpoints.EndPoints;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;


import static endpoints.EndPoints.*;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.*;


public class AuthorizationTest {

    private RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri(EndPoints.baseURL)
            .log(LogDetail.ALL)
            .build();

    @Test(description = "Успешная авторизация с верным логином и паролем")
    public void autorizationPassedTest() {

        Response response = given()
                .spec(requestSpec).urlEncodingEnabled(true)
                .param("email", validLogin)
                .param("password", validPassword)
                .header("Accept", ContentType.JSON.getAcceptHeader())
                .post(EndPoints.authorization);
        System.out.println(response.prettyPrint());
        assertEquals(200, response.getStatusCode());
        assertTrue(response.jsonPath().get("isSuccessful"), "Не верный статус поля isSuccessful");

    }


    @Test(description = "Авторизация пользователя с некорректным паролем")
    public void autorizationFailedTestInvalidPassword() {

        Response response = given()
                .spec(requestSpec).urlEncodingEnabled(true)
                .param("email", validLogin)
                .param("password", invalidPassword)
                .header("Accept", ContentType.JSON.getAcceptHeader())
                .post(EndPoints.authorization);
        System.out.println(response.prettyPrint());
        assertEquals(208, response.getStatusCode());
        assertFalse(response.jsonPath().get("isSuccessful"), "Не верный статус поля isSuccessful");
        assertEquals(response.jsonPath().get("message[0]"), "Invalid login or password", "Не соответствует сообщение о некорректном логине или пароле");

    }


    @Test(description = "Авторизация пользователя с некорректным E-mail")
    public void autorizationFailedTestInvalidEmail() {

        Response response = given()
                .spec(requestSpec).urlEncodingEnabled(true)
                .param("email", invalidLogin)
                .param("password", validPassword)
                .header("Accept", ContentType.JSON.getAcceptHeader())
                .post(EndPoints.authorization);
        System.out.println(response.prettyPrint());
        assertEquals(208, response.getStatusCode());
        assertFalse(response.jsonPath().get("isSuccessful"), "Не верный статус поля isSuccessful");
        assertEquals(response.jsonPath().get("message.email[0]"), "E-mail \"tester@yan.dex.ru\" is not valid", "Не соответствует сообщение о некорректном E-mail");
    }

}