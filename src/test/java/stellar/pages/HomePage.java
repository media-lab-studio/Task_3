package stellar.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import static org.junit.jupiter.api.Assertions.*;

public class HomePage extends Page {
    private final By signIn = By.xpath("//button[.='Войти в аккаунт']");
    private final By order = By.xpath("//button[.='Оформить заказ']");
    private final By heading = By.xpath("//h1[.='Соберите бургер']");
    private final By activeTab = By.cssSelector("[class*='tab_tab_type_current'] span");

    public HomePage(WebDriver driver) { super(driver); }
    @Step("Открыть конструктор") public void open() { driver.get(SITE + "/"); }
    @Step("Нажать «Войти в аккаунт»") public void signIn() { click(signIn); }
    @Step("Проверить открытие конструктора") public void checkOpen() { assertTrue(visible(heading).isDisplayed()); }
    @Step("Проверить доступность оформления заказа") public void checkSignedIn() { assertTrue(visible(order).isDisplayed()); }

    @Step("Выбрать раздел «{section}»") public void select(String section) {
        click(By.xpath("//div[contains(@class,'tab_tab')]/span[.='" + section + "']"));
    }

    @Step("Проверить активный раздел «{section}»") public void checkSection(String section) {
        wait.until(ExpectedConditions.textToBe(activeTab, section));
        assertEquals(section, visible(activeTab).getText());
    }
}
