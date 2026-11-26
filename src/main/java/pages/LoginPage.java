package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    // ======================================== Локаторы элементов страницы ========================================

    // Поле «Email»
    private final By emailInput = By.xpath("//label[normalize-space()='Email']/following-sibling::input");

    // Поле «Пароль»
    private final By passwordInput = By.cssSelector("input[type=password]");

    // Кнопка «Войти»
    private final By submitButton = By.xpath("//button[.='Войти']");

    // ============================================= Методы страницы =============================================

    /**
     * Открыть страницу входа.
     */
    @Step("Открыть страницу входа")
    public LoginPage openPage() {
        log.info("Открыть страницу входа");
        openUrl(BASE_URL + "/login");
        return this;
    }

    /**
     * Ввести email.
     */
    @Step("Ввести email")
    public LoginPage enterEmail(String email) {
        log.info("Ввести email");
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailInput)).sendKeys(email);
        return this;
    }

    /**
     * Ввести пароль.
     */
    @Step("Ввести пароль")
    public LoginPage enterPassword(String password) {
        log.info("Ввести пароль");
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInput)).sendKeys(password);
        return this;
    }

    /**
     * Нажать «Войти».
     */
    @Step("Нажать «Войти»")
    public LoginPage clickSubmitButton() {
        log.info("Нажать «Войти»");
        wait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();
        return this;
    }

    /**
     * Проверить отображение формы входа.
     */
    @Step("Проверить отображение формы входа")
    public boolean isLoginFormDisplayed() {
        log.info("Проверить отображение формы входа");
        return wait.until(ExpectedConditions.visibilityOfElementLocated(submitButton)).isDisplayed();
    }
}
