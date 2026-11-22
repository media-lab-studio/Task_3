package stellar.tests;

import org.junit.jupiter.api.DisplayName;
import stellar.pages.RecoveryPage;
import stellar.support.Browser;

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
    private void completeLogin() {
        login.login(user.email, user.password);
        home.checkSignedIn();
    }
}
