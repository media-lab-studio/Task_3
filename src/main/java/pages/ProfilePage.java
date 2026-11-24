package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProfilePage extends BasePage {

    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    // ======================================== Локаторы элементов страницы ========================================

    // Логин владельца кабинета
    private final By loginInput = By.xpath("//label[normalize-space()='Логин']/following-sibling::input");

    // Кнопка выхода из кабинета
    private final By logoutButton = By.xpath("//button[.='Выход' or .='Выйти']");

    // ============================================= Методы страницы =============================================

    /**
     * Прочитать логин владельца кабинета.
     */
    @Step("Прочитать логин владельца кабинета")
    public String getLoginValue() {
        log.info("Прочитать логин владельца кабинета");
        return wait.until(d -> {
            String email = d.findElement(loginInput).getDomProperty("value");
            return email == null || email.isBlank() ? null : email;
        });
    }

    /**
     * Выйти из аккаунта.
     */
    @Step("Выйти из аккаунта")
    public ProfilePage clickLogoutButton() {
        log.info("Выйти из аккаунта");
        wait.until(ExpectedConditions.elementToBeClickable(logoutButton)).click();
        return this;
    }
}
