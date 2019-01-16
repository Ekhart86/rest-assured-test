package model.api.registration;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class RegistrationResponse {

    private boolean isSuccessful;

    private String result = null;

    private int code;


}