package testsuite.websocket;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import executers.BaseExecutor;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import model.api.profile.ApiProfile;
import model.websocket.WebsocketMessage;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import util.LogListener;
import util.WebsocketHelper;

import java.util.Map;


@Listeners(LogListener.class)
@Feature("Тестирование вебсокета")
public class WebsocketTest {

    private Gson gson = new Gson();
    private BaseExecutor baseExecutor = new BaseExecutor();

    @Test(description = "Успешная авторизация в Websocket")
    public void websockedLoginPassedTest() {

        /** Авторизация **/
        Response responseAuthorization = baseExecutor.authPassedPost();
        Map<String, String> cookies = responseAuthorization.cookies();
        String ssid = cookies.get("ssid");

        /** Получение профиля через rest testsuite.api **/

        ApiProfile apiProfile = baseExecutor.apiProfileGet(ssid);

        /** Отправка ssid в testsuite.websocket **/

        String jsonWithSsid = gson.toJson(new WebsocketMessage("ssid", cookies.get("ssid")));
        WebsocketHelper.sendMessage(jsonWithSsid);

        JsonObject jsonWebsocketProfile = gson.fromJson(WebsocketHelper.jsonResponse, JsonObject.class);
        System.out.println("Профиль полученный через вебсокет " + jsonWebsocketProfile);

        String websocketProfileEmail = jsonWebsocketProfile.get("msg").getAsJsonObject().get("email").getAsString();
        String websocketProfileFirstName = jsonWebsocketProfile.get("msg").getAsJsonObject().get("first_name").getAsString();
        String websocketProfileLastName = jsonWebsocketProfile.get("msg").getAsJsonObject().get("last_name").getAsString();
        int websocketProfileUserId = jsonWebsocketProfile.get("msg").getAsJsonObject().get("user_id").getAsInt();

        Assert.assertEquals(websocketProfileEmail, apiProfile.getResult().getEmail(), "E-mail не совпадает");
        Assert.assertEquals(websocketProfileFirstName, apiProfile.getResult().getFirst_name(), "Имя не совпадает");
        Assert.assertEquals(websocketProfileLastName, apiProfile.getResult().getLast_name(), "Фамилия не совпадает");
        Assert.assertEquals(websocketProfileUserId, apiProfile.getResult().getUser_id(), "User ID не совпадает");
        System.out.println("Тест пройден. Данные профиля полученные через API соответствуют данным профиля полученным через Websocket");
        WebsocketHelper.jsonResponse = null;
    }


    @Test(description = "Не успешная авторизация в Websocket")
    public void websockedLoginFailedTest() {

        String jsonWithIncorrectSsid = gson.toJson(new WebsocketMessage("ssid", "11111111111111111111111111111111"));
        System.out.println(jsonWithIncorrectSsid);
        WebsocketHelper.sendMessage(jsonWithIncorrectSsid);
        JsonObject jsonWebsocketProfile = gson.fromJson(WebsocketHelper.jsonResponse, JsonObject.class);
        System.out.println("Профиль полученный через вебсокет " + jsonWebsocketProfile);
        Assert.assertEquals(jsonWebsocketProfile.get("msg").getAsString(), "false", "Статус профиля неверный");
        System.out.println("Тест пройден. При отправке неверного ssid в ответ получаем профиль в статусе false");
    }

}


