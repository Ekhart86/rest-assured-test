package websocket;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;


public class WebsocketHelper extends WebSocketListener {

    static String jsonResponse;
    Gson gson = new Gson();
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
        webSocket.close(NORMAL_CLOSURE_STATUS, "Данные получены");

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




}
