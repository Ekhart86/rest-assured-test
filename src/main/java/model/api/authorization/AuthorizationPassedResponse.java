package model.api.authorization;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthorizationPassedResponse extends Response {


    @Override
    public String showMessage() {
        return "Успешная авторизация";
    }
}
