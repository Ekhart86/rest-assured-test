package websocket;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

import java.io.BufferedReader;

import java.io.InputStreamReader;


public class WebsocketHelper extends WebSocketListener {


    private static final int NORMAL_CLOSURE_STATUS = 1000;
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


    @Override
    public void onOpen(WebSocket webSocket, Response response) {
        System.out.println("server response header:" + response.headers());
        System.out.println("server response:" + response);

    }

    @Override
    public void onMessage(WebSocket webSocket, String text) {
        System.out.println("Receiving: " + text);
        webSocket.send("response-" + text);

    }

    @Override
    public void onMessage(WebSocket webSocket, ByteString bytes) {
        System.out.println("Receiving: " + bytes.hex());
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
