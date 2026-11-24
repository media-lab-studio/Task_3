package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.UiLog;

import java.time.Duration;
import java.util.logging.Logger;

public abstract class BasePage {

    public static final String BASE_URL = "https://qa-stellarburgers.education-services.ru";

    protected final WebDriver driver;
    protected final Logger log = UiLog.forClass(getClass());
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
    @Step("Открыть страницу {url}")
    public void openUrl(String url) {
        log.info(() -> "Открывается страница с URL: " + url);
        driver.get(url);
    }

}
