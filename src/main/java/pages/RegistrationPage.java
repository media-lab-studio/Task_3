package pages;

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
    public RegistrationPage openPage() {
        System.out.println("Открыть форму регистрации");
        openUrl(BASE_URL + "/register");
        return this;
    }

    /**
     * Ввести имя.
     */
    public RegistrationPage enterName(String name) {
        System.out.println("Ввести имя");
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameInput)).sendKeys(name);
        return this;
    }

    /**
     * Ввести email.
     */
    public RegistrationPage enterEmail(String email) {
        System.out.println("Ввести email");
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailInput)).sendKeys(email);
        return this;
    }

    /**
     * Ввести пароль.
     */
    public RegistrationPage enterPassword(String password) {
        System.out.println("Ввести пароль");
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInput)).sendKeys(password);
        return this;
    }

    /**
     * Нажать «Зарегистрироваться».
     */
    public RegistrationPage clickSubmitButton() {
        System.out.println("Нажать «Зарегистрироваться»");
        wait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();
        return this;
    }

    /**
     * Перейти по ссылке «Войти».
     */
    public RegistrationPage clickLoginLink() {
        System.out.println("Перейти по ссылке «Войти»");
        wait.until(ExpectedConditions.elementToBeClickable(loginLink)).click();
        return this;
    }

    /**
     * Прочитать ошибку пароля.
     */
    public String getPasswordErrorMessage() {
        System.out.println("Прочитать ошибку пароля");
        return wait.until(ExpectedConditions.visibilityOfElementLocated(passwordErrorMessage)).getText();
    }
}
