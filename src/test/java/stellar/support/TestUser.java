package stellar.support;

import com.google.gson.*;
import io.qameta.allure.Step;
import java.net.URI;
import java.net.http.*;
import java.time.Duration;
import java.util.UUID;
import static stellar.pages.BasePage.SITE;
import static org.junit.jupiter.api.Assertions.*;

public class TestUser {
    public final String email = "qa-" + UUID.randomUUID() + "@example.com";
    public final String name = "Stellar Tester";
    public final String password;
    private String token;
    private final HttpClient client = HttpClient.newHttpClient();

    public TestUser(String password) { this.password = password; }

    private HttpResponse<String> post(String path) throws Exception {
        JsonObject data = new JsonObject();
        data.addProperty("email", email);
        data.addProperty("password", password);
        if (path.equals("register")) data.addProperty("name", name);
        HttpRequest request = request(path).header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(data.toString())).build();
        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    private HttpRequest.Builder request(String path) {
        return HttpRequest.newBuilder(URI.create(SITE + "/api/auth/" + path)).timeout(Duration.ofSeconds(20));
    }

    private String accessToken(HttpResponse<String> response) {
        assertEquals(200, response.statusCode(), "API авторизации");
        return JsonParser.parseString(response.body()).getAsJsonObject().get("accessToken").getAsString();
    }

    @Step("Создать пользователя через API") public void create() throws Exception { token = accessToken(post("register")); }

    @Step("Удалить тестового пользователя через API, если он создан") public void cleanUp() throws Exception {
        if (token == null) {
            HttpResponse<String> response = post("login");
            if (response.statusCode() == 401) return;
            token = accessToken(response);
        }
        HttpResponse<String> result = client.send(request("user").header("Authorization", token).DELETE().build(), HttpResponse.BodyHandlers.ofString());
        assertTrue(result.statusCode() / 100 == 2, "Удаление пользователя: HTTP " + result.statusCode());
    }
}
