package util;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import endpoints.EndPoints;
import okhttp3.*;
import okio.ByteString;

import static java.lang.Thread.sleep;


public class WebsocketHelper extends WebSocketListener {

    public static String jsonResponse;
    private Gson gson = new Gson();
    private static final int NORMAL_CLOSURE_STATUS = 1000;


    @Override
    public void onOpen(WebSocket webSocket, Response response) {
        System.out.println("server response header:" + response.headers());
        System.out.println("server response:" + response);

    }

    @Override
    public void onMessage(WebSocket webSocket, String text) {

        JsonObject temp = gson.fromJson(text, JsonObject.class);
        if (temp != null && temp.get("name").getAsString().equals("profile")) {
            jsonResponse = text;
        }

    }

    @Override
    public void onMessage(WebSocket webSocket, ByteString bytes) {
        System.out.println("Receiving byteString: " + bytes.hex());
    }

    @Override
    public void onClosing(WebSocket webSocket, int code, String reason) {
        webSocket.close(NORMAL_CLOSURE_STATUS, null);
        System.out.println("Closing: " + code + " " + reason);
    }

    @Override
    public void onFailure(WebSocket webSocket, Throwable t, Response response) {
        t.printStackTrace();
    }


    public static void sendMessage(String message) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(EndPoints.websocketURL)
                .build();

        WebsocketHelper listener = new WebsocketHelper();
        WebSocket webSocket = client.newWebSocket(request, listener);
        webSocket.send(message);
        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        webSocket.close(NORMAL_CLOSURE_STATUS, "Закрытие вебсокета");
        client.dispatcher().executorService().shutdown();
    }

}
