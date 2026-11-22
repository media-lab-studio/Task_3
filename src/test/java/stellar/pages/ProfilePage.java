package stellar.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProfilePage extends Page {
    private final By login = field("Логин");
    private final By logout = By.xpath("//button[.='Выход'] | //button[.='Выйти']");
    public ProfilePage(WebDriver driver) { super(driver); }
    @Step("Проверить логин владельца кабинета") public void checkOwner(String email) {
        wait.until(ExpectedConditions.attributeToBe(login, "value", email));
        assertEquals(email, visible(login).getDomProperty("value"));
    }
    @Step("Выйти из аккаунта") public void logout() { click(logout); }
}
