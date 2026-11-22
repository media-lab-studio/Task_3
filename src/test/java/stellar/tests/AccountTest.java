package stellar.tests;

import org.junit.jupiter.api.DisplayName;
import stellar.support.Browser;

class AccountTest extends UiTest {
    @InBrowsers @DisplayName("Переход в свой кабинет") void openAccount(Browser browser) throws Exception {
        signIn(browser);
        header.account();
        profile.checkOwner(user.email);
    }

    private void openProfile(Browser browser) throws Exception {
        signIn(browser);
        header.account();
        profile.checkOwner(user.email);
    }

    @InBrowsers @DisplayName("Из кабинета по ссылке «Конструктор»") void constructorLink(Browser browser) throws Exception {
        openProfile(browser);
        header.constructor();
        home.checkOpen();
    }
}
