package stellar.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.*;

public class ProfilePage extends Page {
    private final By login = field("Логин");
    private final By logout = By.xpath("//button[.='Выход'] | //button[.='Выйти']");
    public ProfilePage(WebDriver driver) { super(driver); }
    @Step("Прочитать логин владельца кабинета") public String getOwnerEmail() {
        return wait.until(d -> {
            String email = visible(login).getDomProperty("value");
            return email == null || email.isBlank() ? null : email;
        });
    }
    @Step("Выйти из аккаунта") public void logout() { click(logout); }
}
