package model.websocket;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WebsocketMessage {

    private String name;
    private JsonElement msg;

    public WebsocketMessage(String name, String message) {
        this.name = name;
        this.msg = new JsonPrimitive(message);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public JsonElement getMessage() {
        return msg;
    }

    public void setMessage(JsonElement message) {
        this.msg = message;
    }
}
