package tests;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.openqa.selenium.WebDriver;
import util.TestUser;
import util.UiLog;

import java.util.logging.Logger;

/**
 * Базовый класс UI-тестов: управляет жизненным циклом браузера и тестового пользователя.
 */
public abstract class BaseTest {

    private static final Logger LOG = UiLog.forClass(BaseTest.class);
    private static final String DEFAULT_TEST_PASSWORD = "QaPass123";

    private WebDriver driver;
    private TestUser user;

    @BeforeEach
    @Step("Запустить браузер")
    public void setUp() {
        String browser = System.getProperty("browser", "chrome");
        configureAllure(browser);
        LOG.info("Запуск браузера: " + browser);
        driver = BrowserFactory.create(browser);
    }

    @AfterEach
    @Step("Удалить тестовые данные и закрыть браузер")
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
    @Step("Подготовить уникальные данные пользователя")
    protected TestUser prepareUser(String password) {
        user = new TestUser(password);
        return user;
    }

    /**
     * Подготовить пользователя и сразу зарегистрировать его через API.
     *
     * @return зарегистрированный пользователь
     */
    @Step("Подготовить зарегистрированного пользователя")
    protected TestUser prepareRegisteredUser() {
        TestUser preparedUser = prepareUser(DEFAULT_TEST_PASSWORD);
        preparedUser.create();
        return preparedUser;
    }

    private void configureAllure(String browser) {
        Allure.parameter("Браузер", browser);
        Allure.getLifecycle().updateTestCase(test -> test.setHistoryId(test.getHistoryId() + "-" + browser));
    }
}
