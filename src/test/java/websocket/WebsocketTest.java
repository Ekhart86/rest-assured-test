package websocket;


import endpoints.EndPoints;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class WebsocketTest {


    @Test
    public void websocketTest() {
        RestAssured.baseURI = "https://iqoption.com";
        Response response = given().urlEncodingEnabled(true)
                .param("email", "valderama1@yandex.ru")
                .param("password", "iqoption86")
                .header("Accept", ContentType.JSON.getAcceptHeader())
                .post(EndPoints.authorization);
        Map<String, String> contentType = response.cookies();

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("wss://iqoption.com/echo/websocket")
                .build();

        WebsocketHelper listener = new WebsocketHelper();
        WebSocket ws = client.newWebSocket(request, listener);

        ws.send("ssid=" + contentType.get("ssid"));

        client.dispatcher().executorService().shutdown();


    }


}
