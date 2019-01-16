package executers;

import constants.EndPoints;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import model.api.profile.ApiProfile;
import model.api.registration.RegistrationResponse;

import static constants.RequestData.*;
import static io.restassured.RestAssured.given;

public class BaseExecutor {


    private RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri(EndPoints.BASE_URL)
            .log(LogDetail.ALL)
            .build();

    public Response authPassedPost() {

        return given()
                .spec(requestSpec).urlEncodingEnabled(true)
                .param("email", VALID_LOGIN)
                .param("password", VALID_PASSWORD)
                .header("Accept", ContentType.JSON.getAcceptHeader())
                .post(EndPoints.AUTHORIZATION);
    }

    public Response authInvalidPasswordPost() {

        return given()
                .spec(requestSpec).urlEncodingEnabled(true)
                .param("email", VALID_LOGIN)
                .param("password", INVALID_PASSWORD)
                .header("Accept", ContentType.JSON.getAcceptHeader())
                .post(EndPoints.AUTHORIZATION);
    }

    public Response authInvalidEmailPost() {

        return given()
                .spec(requestSpec).urlEncodingEnabled(true)
                .param("email", INVALID_LOGIN)
                .param("password", VALID_PASSWORD)
                .header("Accept", ContentType.JSON.getAcceptHeader())
                .post(EndPoints.AUTHORIZATION);
    }

    public RegistrationResponse registerInvalidDataPost(String email, String password, String first_name, String last_name, String tz, int expectedResultCode) {

        return given().spec(requestSpec).urlEncodingEnabled(true)
                .param("email", email)
                .param("password", password)
                .param("first_name", first_name)
                .param("last_name", last_name)
                .param("tz", tz)
                .header("Accept", ContentType.JSON.getAcceptHeader())
                .post(EndPoints.REGISTRATION)
                .then().log().body()
                .statusCode(200)
                .extract()
                .body()
                .as(RegistrationResponse.class);

    }

    public ApiProfile apiProfileGet(String ssid) {

        return given().baseUri(EndPoints.BASE_URL).urlEncodingEnabled(true)
                .header("Accept", ContentType.JSON.getAcceptHeader())
                .header("Cookie", "ssid=" + ssid)
                .header("Cookie", "lang=ru_RU")
                .get(EndPoints.USERS_PROFILE).then().log().all().extract()
                .body()
                .as(ApiProfile.class);
    }

}
