package model.api.profile;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Result {

    private int user_id;

    private String first_name;

    private String last_name;

    private String email;

}
