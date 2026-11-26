package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegistrationPage extends BasePage {

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    // ======================================== Локаторы элементов страницы ========================================

    // Поле «Имя»
    private final By nameInput = By.xpath("//label[normalize-space()='Имя']/following-sibling::input");

    // Поле «Email»
    private final By emailInput = By.xpath("//label[normalize-space()='Email']/following-sibling::input");

    // Поле «Пароль»
    private final By passwordInput = By.cssSelector("input[type=password]");

    // Кнопка «Зарегистрироваться»
    private final By submitButton = By.xpath("//button[.='Зарегистрироваться']");

    // Ссылка на страницу входа
    private final By loginLink = By.linkText("Войти");

    // Сообщение об ошибке под полем пароля
    private final By passwordErrorMessage = By.cssSelector(".input__error");

    // ============================================= Методы страницы =============================================

    /**
     * Открыть форму регистрации.
     */
    @Step("Открыть форму регистрации")
    public RegistrationPage openPage() {
        log.info("Открыть форму регистрации");
        openUrl(BASE_URL + "/register");
        return this;
    }

    /**
     * Ввести имя.
     */
    @Step("Ввести имя")
    public RegistrationPage enterName(String name) {
        log.info("Ввести имя");
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameInput)).sendKeys(name);
        return this;
    }

    /**
     * Ввести email.
     */
    @Step("Ввести email")
    public RegistrationPage enterEmail(String email) {
        log.info("Ввести email");
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailInput)).sendKeys(email);
        return this;
    }

    /**
     * Ввести пароль.
     */
    @Step("Ввести пароль")
    public RegistrationPage enterPassword(String password) {
        log.info("Ввести пароль");
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInput)).sendKeys(password);
        return this;
    }

    /**
     * Нажать «Зарегистрироваться».
     */
    @Step("Нажать «Зарегистрироваться»")
    public RegistrationPage clickSubmitButton() {
        log.info("Нажать «Зарегистрироваться»");
        wait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();
        return this;
    }

    /**
     * Перейти по ссылке «Войти».
     */
    @Step("Перейти по ссылке «Войти»")
    public RegistrationPage clickLoginLink() {
        log.info("Перейти по ссылке «Войти»");
        wait.until(ExpectedConditions.elementToBeClickable(loginLink)).click();
        return this;
    }

    /**
     * Прочитать ошибку пароля.
     */
    @Step("Прочитать ошибку пароля")
    public String getPasswordErrorMessage() {
        String message = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordErrorMessage)).getText();
        log.info(() -> "Ошибка пароля: " + message);
        return message;
    }
}
