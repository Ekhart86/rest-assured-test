package model.api.authorization;

public abstract class Response {
    private boolean isSuccessful;


    public boolean isSuccessful() {
        return isSuccessful;
    }

    public void setSuccessful(boolean successful) {
        isSuccessful = successful;
    }

    public abstract String showMessage();

}
