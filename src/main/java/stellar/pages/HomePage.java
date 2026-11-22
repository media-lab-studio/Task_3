package stellar.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.*;

public class HomePage extends BasePage {
    private final By signIn = By.xpath("//button[.='Войти в аккаунт']");
    private final By order = By.xpath("//button[.='Оформить заказ']");
    private final By heading = By.xpath("//h1[.='Соберите бургер']");
    private final By activeTab = By.cssSelector("[class*='tab_tab_type_current'] span");
    private final By ingredientList = By.cssSelector("[class*='ingredients__menuContainer']");
    private final By ingredientCard = By.cssSelector("a[href^='/ingredient/']");

    public HomePage(WebDriver driver) { super(driver); }
    @Step("Открыть конструктор") public void open() { driver.get(SITE + "/"); }
    @Step("Нажать «Войти в аккаунт»") public void signIn() { click(signIn); }
    @Step("Проверить видимость конструктора") public boolean isOpen() { return visible(heading).isDisplayed(); }
    @Step("Проверить доступность оформления заказа") public boolean canOrder() { return visible(order).isDisplayed(); }

    @Step("Выбрать раздел «{section}»") public void select(String section) {
        visible(ingredientCard);
        click(By.xpath("//div[contains(@class,'tab_tab')]/span[.='" + section + "']"));
    }

    @Step("Прочитать активную вкладку после прокрутки к разделу «{section}»") public String getActiveSectionAt(String section) {
        By sectionHeading = By.xpath("//h2[.='" + section + "']");
        return wait.withMessage("Список не прокручен к разделу «" + section + "»")
                .until(d -> {
                    WebElement list = d.findElement(ingredientList);
                    WebElement title = d.findElement(sectionHeading);
                    boolean aligned = Boolean.TRUE.equals(((JavascriptExecutor) d).executeScript(
                            "const [list,title] = arguments; " +
                            "return Math.abs(title.getBoundingClientRect().top - list.getBoundingClientRect().top) < 3;",
                            list, title));
                    return aligned ? d.findElement(activeTab).getText() : null;
                });
    }
}
