package stellar.tests;

import org.junit.jupiter.api.DisplayName;
import stellar.support.Browser;

class AccountTest extends UiTest {
    @InBrowsers @DisplayName("Переход в свой кабинет") void openAccount(Browser browser) throws Exception {
        signIn(browser);
        header.account();
        profile.checkOwner(user.email);
    }


}
