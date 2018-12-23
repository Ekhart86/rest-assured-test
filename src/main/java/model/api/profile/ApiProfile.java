package model.api.profile;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class ApiProfile {
    private boolean isSuccessful;
    private String message;
    private Result result;

    public boolean isSuccessful() {
        return isSuccessful;
    }

    public String getMessage() {
        return message;
    }

    public Result getResult() {
        return result;
    }
}
