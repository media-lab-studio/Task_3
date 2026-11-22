package util;

import io.restassured.response.Response;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.isOneOf;
import static pages.BasePage.BASE_URL;

public class TestUser {

    public final String email = "qa-" + UUID.randomUUID() + "@example.com";
    public final String name = "Stellar Tester";
    public final String password;
    private String accessToken;

    public TestUser(String password) {
        this.password = password;
    }

    public void create() {
        accessToken = given()
                .baseUri(BASE_URL)
                .contentType("application/json")
                .body(String.format("{\"name\":\"%s\",\"email\":\"%s\",\"password\":\"%s\"}", name, email, password))
                .post("/api/auth/register")
                .then().statusCode(200).extract().path("accessToken");
    }

    public void delete() {
        if (accessToken == null) {
            // После регистрации через интерфейс получаем токен по данным формы.
            Response login = given()
                    .baseUri(BASE_URL)
                    .contentType("application/json")
                    .body(String.format("{\"email\":\"%s\",\"password\":\"%s\"}", email, password))
                    .post("/api/auth/login");
            if (login.statusCode() == 401) {
                return;
            }
            accessToken = login.then().statusCode(200).extract().path("accessToken");
        }
        given().baseUri(BASE_URL)
                .header("Authorization", accessToken)
                .delete("/api/auth/user")
                .then().statusCode(isOneOf(200, 202, 204));
    }
}
