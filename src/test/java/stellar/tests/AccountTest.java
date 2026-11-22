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
    @InBrowsers @DisplayName("Из кабинета по логотипу") void logoLink(Browser browser) throws Exception {
        openProfile(browser);
        header.logo();
        home.checkOpen();
    }
    @InBrowsers @DisplayName("Выход закрывает доступ к кабинету") void logout(Browser browser) throws Exception {
        openProfile(browser);
        profile.logout();
        login.checkOpen();
        home.open();
        header.account();
        login.checkOpen();
    }
}
