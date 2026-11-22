package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HeaderComponent extends BasePage {

    public HeaderComponent(WebDriver driver) {
        super(driver);
    }

    // ======================================== Локаторы элементов страницы ========================================

    // Личный кабинет в шапке
    private final By accountLink = By.cssSelector("header a[href='/account']");

    // Ссылка «Конструктор»
    private final By constructorLink = By.linkText("Конструктор");

    // Логотип Stellar Burgers
    private final By stellarLogo = By.cssSelector("header [class*='header__logo'] a");

    // ============================================= Методы страницы =============================================

    /**
     * Нажать «Личный кабинет».
     */
    public HeaderComponent clickAccountLink() {
        System.out.println("Нажать «Личный кабинет»");
        wait.until(ExpectedConditions.elementToBeClickable(accountLink)).click();
        return this;
    }

    /**
     * Нажать «Конструктор».
     */
    public HeaderComponent clickConstructorLink() {
        System.out.println("Нажать «Конструктор»");
        wait.until(ExpectedConditions.elementToBeClickable(constructorLink)).click();
        return this;
    }

    /**
     * Нажать логотип Stellar Burgers.
     */
    public HeaderComponent clickStellarLogo() {
        System.out.println("Нажать логотип Stellar Burgers");
        wait.until(ExpectedConditions.elementToBeClickable(stellarLogo)).click();
        return this;
    }
}
