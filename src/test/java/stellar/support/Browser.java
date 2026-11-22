package stellar.support;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.*;
import java.io.File;

public enum Browser {
    CHROME, YANDEX;

    @Step("Запустить браузер {this}") public WebDriver start() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--window-size=1440,1000", "--disable-search-engine-choice-screen");
        if (Boolean.parseBoolean(System.getProperty("headless", "true"))) options.addArguments("--headless=new");
        if (this == CHROME) return new ChromeDriver(options);

        options.setBinary(System.getProperty("yandex.binary", System.getenv("LOCALAPPDATA") + "/Yandex/YandexBrowser/Application/browser.exe"));
        String executable = System.getProperty("yandex.driver", System.getenv("YANDEX_DRIVER"));
        if (executable == null) throw new IllegalStateException("Укажите путь к YandexDriver через YANDEX_DRIVER или -Dyandex.driver");
        ChromeDriverService service = new ChromeDriverService.Builder().usingDriverExecutable(new File(executable)).build();
        return new ChromeDriver(service, options);
    }
}
