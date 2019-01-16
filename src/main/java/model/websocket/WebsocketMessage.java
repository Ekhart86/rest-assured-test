package model.websocket;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class WebsocketMessage {

    private String name;
    private JsonElement msg;

    public WebsocketMessage(String name, String message) {
        this.name = name;
        this.msg = new JsonPrimitive(message);
    }

}
