package stellar.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.*;

public class RegistrationPage extends BasePage {
    private final By name = field("Имя");
    private final By email = field("Email");
    private final By password = By.cssSelector("input[type=password]");
    private final By submit = By.xpath("//button[.='Зарегистрироваться']");
    private final By error = By.cssSelector(".input__error");
    private final By login = By.linkText("Войти");

    public RegistrationPage(WebDriver driver) { super(driver); }
    @Step("Открыть регистрацию") public RegistrationPage open() {
        driver.get(SITE + "/register");
        return this;
    }
    @Step("Перейти из регистрации ко входу") public void signIn() { click(login); }
    @Step("Отправить форму регистрации") public void register(String userName, String address, String secret) {
        visible(name).sendKeys(userName);
        visible(email).sendKeys(address);
        visible(password).sendKeys(secret);
        click(submit);
    }
    @Step("Прочитать ошибку пароля") public String getPasswordError() {
        return visible(error).getText();
    }
}
