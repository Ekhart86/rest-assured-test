package model.api.authorization;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
@JsonIgnoreProperties(ignoreUnknown = true)

class Message {

    ArrayList<String> email = new ArrayList<String>();

}
