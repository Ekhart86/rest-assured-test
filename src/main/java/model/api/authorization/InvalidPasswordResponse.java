package model.api.authorization;

import java.util.ArrayList;

public class InvalidPasswordResponse extends Response {

    ArrayList<String> message = new ArrayList<String>();
    ArrayList<String> result = new ArrayList<String>();


    public ArrayList<String> getMessage() {
        return message;
    }

    public void setMessage(ArrayList<String> message) {
        this.message = message;
    }

    public ArrayList<String> getResult() {
        return result;
    }

    public void setResult(ArrayList<String> result) {
        this.result = result;
    }


    @Override
    public String showMessage() {
        return message.get(0);
    }
}
