package stellar.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.*;

public class RecoveryPage extends BasePage {
    private final By login = By.linkText("Войти");
    public RecoveryPage(WebDriver driver) { super(driver); }
    @Step("Открыть восстановление пароля") public void open() { driver.get(SITE + "/forgot-password"); }
    @Step("Перейти из восстановления ко входу") public void signIn() { click(login); }
}
