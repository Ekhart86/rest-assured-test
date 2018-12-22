package model.api.authorization;

import java.util.ArrayList;

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

