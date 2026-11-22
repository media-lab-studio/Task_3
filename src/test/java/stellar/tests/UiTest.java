package stellar.tests;

import io.qameta.allure.Step;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.TestInfo;
import org.openqa.selenium.WebDriver;
import stellar.pages.*;
import stellar.support.*;
import java.util.logging.Logger;
import static org.junit.jupiter.api.Assertions.assertTrue;

abstract class UiTest {
    private final Logger log = Logger.getLogger(getClass().getName());
    WebDriver driver;
    HomePage home;
    Header header;
    LoginPage login;
    ProfilePage profile;
    RegistrationPage registration;
    TestUser user;

    void start(Browser browser) {
        driver = browser.start();
        log.info(() -> "Открыта сессия " + browser);
        home = new HomePage(driver);
        header = new Header(driver);
        login = new LoginPage(driver);
        profile = new ProfilePage(driver);
        registration = new RegistrationPage(driver);
    }

    void withUser(Browser browser) throws Exception {
        start(browser);
        user = new TestUser("QaPass123");
        user.create();
    }

    @Step("Авторизоваться перед проверкой кабинета") void signIn(Browser browser) throws Exception {
        withUser(browser);
        login.open();
        login.login(user.email, user.password);
        assertTrue(home.canOrder(), "Предусловие: пользователь авторизован");
    }

    @AfterEach @Step("Завершить тест") void tearDown(TestInfo test) throws Exception {
        try { if (user != null) user.cleanUp(); }
        finally {
            if (driver != null) {
                driver.quit();
                log.info(() -> "Закрыта сессия: " + test.getDisplayName());
            }
        }
    }
}
