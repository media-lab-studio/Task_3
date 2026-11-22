package tests;

import io.qameta.allure.Allure;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import util.TestUser;

import java.io.File;

public abstract class BaseTest {

    protected WebDriver driver;
    protected TestUser user;

    @BeforeEach
    public void setUp() {
        String browser = System.getProperty("browser", "chrome");
        Allure.parameter("Браузер", browser);
        Allure.getLifecycle().updateTestCase(test -> test.setHistoryId(test.getHistoryId() + "-" + browser));
        System.out.println(">>> Запуск браузера: " + browser);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--window-size=1440,1000");
        if (Boolean.parseBoolean(System.getProperty("headless", "true"))) {
            options.addArguments("--headless=new");
        }
        if ("yandex".equals(browser)) {
            options.setBinary(System.getProperty("yandex.binary", System.getenv("LOCALAPPDATA")
                    + "/Yandex/YandexBrowser/Application/browser.exe"));
            String executable = System.getProperty("yandex.driver", System.getenv("YANDEX_DRIVER"));
            if (executable == null) {
                throw new IllegalStateException("Укажите путь к YandexDriver через YANDEX_DRIVER");
            }
            ChromeDriverService service = new ChromeDriverService.Builder()
                    .usingDriverExecutable(new File(executable)).build();
            driver = new ChromeDriver(service, options);
        } else if ("chrome".equals(browser)) {
            driver = new ChromeDriver(options);
        } else {
            throw new IllegalArgumentException("Неизвестный браузер: " + browser);
        }
    }

    @AfterEach
    public void tearDown(TestInfo testInfo) {
        System.out.println("=== Завершение теста: " + testInfo.getDisplayName() + " ===");
        try {
            if (user != null) {
                user.delete();
            }
        } finally {
            if (driver != null) {
                System.out.println(">>> Закрытие браузера");
                driver.quit();
            }
        }
    }
}
