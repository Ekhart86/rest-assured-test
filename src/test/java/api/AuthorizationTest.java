package api;

import endpoints.EndPoints;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import model.api.authorization.AuthorizationPassedResponse;

import model.api.authorization.InvalidEmailResponse;
import model.api.authorization.InvalidPasswordResponse;
import model.api.authorization.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import static io.restassured.RestAssured.given;


public class AuthorizationTest {

    @Test(dataProvider = "authorizationDataFields")
    public void autorizationTestPost(String email, String password, Boolean result, int statusCode, Response response, String expectedMessage) {

        response = given().baseUri(EndPoints.baseURL).log().all().urlEncodingEnabled(true)
                .param("email", email)
                .param("password", password)
                .header("Accept", ContentType.JSON.getAcceptHeader())
                .post(EndPoints.authorization)
                .then().log().all()
                .statusCode(statusCode)
                .extract()
                .body()
                .as(response.getClass());
        System.out.println(response.isSuccessful());
//        Assert.assertEquals(response.showMessage(), expectedMessage);
//        Assert.assertEquals((Object) response.isSuccessful(), result);



    }

    @DataProvider
    public static Object[][] authorizationDataFields() {

        return new Object[][]{
                {"valderama1@yandex.ru", "iqoption86", true, 200, new AuthorizationPassedResponse(), "Успешная авторизация"},
                {"valderama1@yandex.ru", "123456", false, 208, new InvalidPasswordResponse(), "Invalid login or password"},
                {"tester@yan.dex.ru", "123457", false, 208, new InvalidEmailResponse(), "E-mail \"tester@yan.dex.ru\" is not valid"},
        };
    }


}
