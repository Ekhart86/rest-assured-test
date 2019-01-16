package model.api.profile;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class ApiProfile {

    private boolean isSuccessful;

    private String message;

    private Result result;

}
