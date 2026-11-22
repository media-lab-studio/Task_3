package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {

    public static final String BASE_URL = "https://qa-stellarburgers.education-services.ru";

    protected final WebDriver driver;
    protected final WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    /**
     * Открыть страницу с заданным url
     *
     * @param url страницы
     */
    public void openUrl(String url) {
        System.out.println("Открывается страница с URL: " + url);
        driver.get(url);
    }

}
