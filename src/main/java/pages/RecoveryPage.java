package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RecoveryPage extends BasePage {

    public RecoveryPage(WebDriver driver) {
        super(driver);
    }

    // ======================================== Локаторы элементов страницы ========================================

    // Ссылка на страницу входа
    private final By loginLink = By.linkText("Войти");

    // ============================================= Методы страницы =============================================

    /**
     * Открыть форму восстановления пароля.
     */
    public RecoveryPage openPage() {
        System.out.println("Открыть форму восстановления пароля");
        openUrl(BASE_URL + "/forgot-password");
        return this;
    }

    /**
     * Перейти по ссылке «Войти».
     */
    public RecoveryPage clickLoginLink() {
        System.out.println("Перейти по ссылке «Войти»");
        wait.until(ExpectedConditions.elementToBeClickable(loginLink)).click();
        return this;
    }
}
