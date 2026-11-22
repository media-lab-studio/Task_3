package pages;

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
    public LoginPage openPage() {
        System.out.println("Открыть страницу входа");
        openUrl(BASE_URL + "/login");
        return this;
    }

    /**
     * Ввести email.
     */
    public LoginPage enterEmail(String email) {
        System.out.println("Ввести email");
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailInput)).sendKeys(email);
        return this;
    }

    /**
     * Ввести пароль.
     */
    public LoginPage enterPassword(String password) {
        System.out.println("Ввести пароль");
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInput)).sendKeys(password);
        return this;
    }

    /**
     * Нажать «Войти».
     */
    public LoginPage clickSubmitButton() {
        System.out.println("Нажать «Войти»");
        wait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();
        return this;
    }

    /**
     * Проверить отображение формы входа.
     */
    public boolean isLoginFormDisplayed() {
        System.out.println("Проверить отображение формы входа");
        return wait.until(ExpectedConditions.visibilityOfElementLocated(submitButton)).isDisplayed();
    }
}
