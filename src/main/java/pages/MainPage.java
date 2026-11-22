package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MainPage extends BasePage {

    public MainPage(WebDriver driver) {
        super(driver);
    }

    // ======================================== Локаторы элементов страницы ========================================

    // Кнопка входа на главной
    private final By loginButton = By.xpath("//button[.='Войти в аккаунт']");

    // Кнопка оформления заказа
    private final By orderButton = By.xpath("//button[.='Оформить заказ']");

    // Заголовок конструктора
    private final By constructorHeading = By.xpath("//h1[.='Соберите бургер']");

    // Активная вкладка
    private final By activeTab = By.cssSelector("[class*='tab_tab_type_current'] span");

    // Список ингредиентов
    private final By ingredientList = By.cssSelector("[class*='ingredients__menuContainer']");

    // Карточка ингредиента
    private final By ingredientCard = By.cssSelector("a[href^='/ingredient/']");

    // Вкладка раздела по названию
    private final String SECTION_TAB_XPATH = "//div[contains(@class,'tab_tab')]/span[.='%s']";

    // Заголовок раздела по названию
    private final String SECTION_TITLE_XPATH = "//h2[.='%s']";

    // ============================================= Методы страницы =============================================

    /**
     * Открыть главную страницу.
     */
    @Step("Открыть главную страницу")
    public MainPage openBaseUrl() {
        System.out.println("Открыть главную страницу");
        openUrl(BASE_URL + "/");
        return this;
    }

    /**
     * Нажать «Войти в аккаунт».
     */
    @Step("Нажать «Войти в аккаунт»")
    public MainPage clickLoginButton() {
        System.out.println("Нажать «Войти в аккаунт»");
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
        return this;
    }

    /**
     * Проверить отображение конструктора.
     */
    @Step("Проверить отображение конструктора")
    public boolean isConstructorDisplayed() {
        System.out.println("Проверить отображение конструктора");
        return wait.until(ExpectedConditions.visibilityOfElementLocated(constructorHeading)).isDisplayed();
    }

    /**
     * Проверить отображение кнопки заказа.
     */
    @Step("Проверить отображение кнопки заказа")
    public boolean isOrderButtonDisplayed() {
        System.out.println("Проверить отображение кнопки заказа");
        return wait.until(ExpectedConditions.visibilityOfElementLocated(orderButton)).isDisplayed();
    }

    /**
     * Выбрать раздел {section}.
     */
    @Step("Выбрать раздел {section}")
    public MainPage clickSectionTab(String section) {
        System.out.println("Выбрать раздел {section}");
        wait.until(ExpectedConditions.visibilityOfElementLocated(ingredientCard));
        By tab = By.xpath(String.format(SECTION_TAB_XPATH, section));
        wait.until(ExpectedConditions.elementToBeClickable(tab)).click();
        return this;
    }

    /**
     * Прочитать активную вкладку после прокрутки к разделу {section}.
     */
    @Step("Прочитать активную вкладку после прокрутки к разделу {section}")
    public String getActiveSection(String section) {
        System.out.println("Прочитать активную вкладку после прокрутки к разделу {section}");
        By title = By.xpath(String.format(SECTION_TITLE_XPATH, section));
        wait.until(d -> Boolean.TRUE.equals(((JavascriptExecutor) d).executeScript(
                "return Math.abs(arguments[0].getBoundingClientRect().top - " +
                        "arguments[1].getBoundingClientRect().top) < 3",
                d.findElement(title), d.findElement(ingredientList))));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(activeTab)).getText();
    }
}
