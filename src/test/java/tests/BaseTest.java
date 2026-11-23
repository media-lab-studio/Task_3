package tests;

import io.qameta.allure.Allure;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.openqa.selenium.WebDriver;
import util.TestUser;

import java.util.logging.Logger;

/**
 * Базовый класс UI-тестов: управляет жизненным циклом браузера и тестового пользователя.
 */
public abstract class BaseTest {

    static {
        LoggingConfig.configure();
    }

    private static final Logger LOG = Logger.getLogger(BaseTest.class.getName());

    private WebDriver driver;
    private TestUser user;

    @BeforeEach
    public void setUp() {
        String browser = System.getProperty("browser", "chrome");
        configureAllure(browser);
        LOG.info("Запуск браузера: " + browser);
        driver = BrowserType.from(browser).createDriver();
    }

    @AfterEach
    public void tearDown(TestInfo testInfo) {
        LOG.info("Завершение теста: " + testInfo.getDisplayName());
        try {
            if (user != null) {
                user.delete();
            }
        } finally {
            if (driver != null) {
                LOG.info("Закрытие браузера");
                driver.quit();
            }
        }
    }

    /**
     * Драйвер текущего теста. Доступен наследникам только для создания Page Object.
     */
    protected WebDriver driver() {
        return driver;
    }

    /**
     * Подготовить пользователя, который будет удалён после теста.
     *
     * @param password пароль пользователя
     * @return созданный пользователь
     */
    protected TestUser prepareUser(String password) {
        user = new TestUser(password);
        return user;
    }

    /**
     * Подготовить пользователя и сразу зарегистрировать его через API.
     *
     * @param password пароль пользователя
     * @return зарегистрированный пользователь
     */
    protected TestUser prepareRegisteredUser(String password) {
        TestUser preparedUser = prepareUser(password);
        preparedUser.create();
        return preparedUser;
    }

    private void configureAllure(String browser) {
        Allure.parameter("Браузер", browser);
        Allure.getLifecycle().updateTestCase(test -> test.setHistoryId(test.getHistoryId() + "-" + browser));
    }
}
