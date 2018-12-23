package model.api.authorization;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class InvalidEmailResponse extends Response {

    Message message;
    ArrayList<Object> result = new ArrayList<Object>();

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message messageObject) {
        this.message = messageObject;
    }

    @Override
    public String showMessage() {
        return message.email.get(0);
    }
}

