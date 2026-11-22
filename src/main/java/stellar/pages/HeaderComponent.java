package stellar.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.*;

public class HeaderComponent extends BasePage {
    private final By account = By.cssSelector("header a[href='/account']");
    private final By constructor = By.linkText("Конструктор");
    private final By logo = By.cssSelector("header [class*='header__logo'] a");

    public HeaderComponent(WebDriver driver) { super(driver); }
    @Step("Перейти в личный кабинет") public void account() { click(account); }
    @Step("Нажать «Конструктор»") public void constructor() { click(constructor); }
    @Step("Нажать логотип Stellar Burgers") public void logo() { click(logo); }
}
