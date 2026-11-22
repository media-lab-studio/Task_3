package stellar.tests;

import org.junit.jupiter.api.DisplayName;
import stellar.pages.RecoveryPage;
import stellar.support.Browser;
import io.qameta.allure.Step;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LoginTest extends UiTest {
    @InBrowsers @DisplayName("Вход с главной") void fromHome(Browser browser) throws Exception {
        withUser(browser);
        home.open();
        home.signIn();
        completeLogin();
    }
    @InBrowsers @DisplayName("Вход через личный кабинет") void fromHeader(Browser browser) throws Exception {
        withUser(browser);
        home.open();
        header.account();
        completeLogin();
    }
    @InBrowsers @DisplayName("Вход из формы регистрации") void fromRegistration(Browser browser) throws Exception {
        withUser(browser);
        registration.open();
        registration.signIn();
        completeLogin();
    }
    @InBrowsers @DisplayName("Вход из восстановления пароля") void fromRecovery(Browser browser) throws Exception {
        withUser(browser);
        RecoveryPage recovery = new RecoveryPage(driver);
        recovery.open();
        recovery.signIn();
        completeLogin();
    }
    @Step("Войти и проверить доступ к оформлению заказа") private void completeLogin() {
        login.login(user.email, user.password);
        assertTrue(home.canOrder(), "После входа должно быть доступно оформление заказа");
    }
}
