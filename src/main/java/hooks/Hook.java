package hooks;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeSuite;

public class Hook {

    @BeforeSuite
    public void initData() {
        RestAssured.baseURI = "https://iqoption.com";
    }
}
