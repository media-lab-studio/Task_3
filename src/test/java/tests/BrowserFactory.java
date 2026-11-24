package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;

final class BrowserFactory {
    private BrowserFactory() { }

    static WebDriver create(String browser) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--window-size=1440,1000");
        if (Boolean.parseBoolean(System.getProperty("headless", "true"))) {
            options.addArguments("--headless=new");
        }
        switch (browser) {
            case "chrome":
                return new ChromeDriver(options);
            case "yandex":
                String path = System.getProperty("yandex.driver", System.getenv("YANDEX_DRIVER"));
                if (path == null || !new File(path).isFile()) {
                    throw new IllegalArgumentException("Укажите путь к YandexDriver в YANDEX_DRIVER или -Dyandex.driver");
                }
                options.setBinary(System.getProperty("yandex.binary", System.getenv("LOCALAPPDATA")
                        + "/Yandex/YandexBrowser/Application/browser.exe"));
                return new ChromeDriver(new ChromeDriverService.Builder()
                        .usingDriverExecutable(new File(path)).build(), options);
            default:
                throw new IllegalArgumentException("Неизвестный браузер: " + browser);
        }
    }
}
