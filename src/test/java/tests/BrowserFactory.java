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
        options.addArguments("--start-maximized");
        String path;
        switch (browser) {
            case "chrome":
                path = System.getProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
                break;
            case "yandex":
                path = System.getProperty("yandex.driver", System.getenv().getOrDefault("YANDEX_DRIVER", "drivers/yandexdriver.exe"));
                options.setBinary(System.getProperty("yandex.binary", System.getenv("LOCALAPPDATA")
                        + "/Yandex/YandexBrowser/Application/browser.exe"));
                break;
            default:
                throw new IllegalArgumentException("Неизвестный браузер: " + browser);
        }
        File executable = new File(path).getAbsoluteFile();
        if (!executable.isFile()) {
            throw new IllegalStateException("Не найден драйвер для " + browser + ": " + executable
                    + ". Распакуйте сюда драйвер, совместимый с установленным браузером.");
        }
        return new ChromeDriver(new ChromeDriverService.Builder().usingDriverExecutable(executable).build(), options);
    }
}
