package model.api.authorization;


public class AuthorizationPassedResponse extends Response {


    @Override
    public String showMessage() {
        return "Успешная авторизация";
    }
}
