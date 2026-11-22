package stellar.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.*;

public class LoginPage extends BasePage {
    private final By email = field("Email");
    private final By password = By.cssSelector("input[type=password]");
    private final By submit = By.xpath("//button[.='Войти']");

    public LoginPage(WebDriver driver) { super(driver); }
    @Step("Открыть страницу входа") public void open() { driver.get(SITE + "/login"); }
    @Step("Проверить видимость формы входа") public boolean isOpen() { return visible(submit).isDisplayed(); }
    @Step("Войти с данными тестового пользователя") public void login(String address, String secret) {
        visible(email).sendKeys(address);
        visible(password).sendKeys(secret);
        click(submit);
    }
}
