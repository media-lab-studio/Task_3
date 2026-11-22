package stellar.tests;

import org.junit.jupiter.api.DisplayName;
import stellar.support.Browser;
import io.qameta.allure.Step;
import static org.junit.jupiter.api.Assertions.*;

class AccountTest extends BaseTest {
    @InBrowsers @DisplayName("Переход в свой кабинет") void openAccount(Browser browser) throws Exception {
        signIn(browser);
        header.account();
        assertEquals(user.email, profile.getOwnerEmail(), "Должен открыться кабинет текущего пользователя");
    }

    @Step("Открыть кабинет тестового пользователя") private void openProfile(Browser browser) throws Exception {
        signIn(browser);
        header.account();
        assertEquals(user.email, profile.getOwnerEmail());
    }

    @InBrowsers @DisplayName("Из кабинета по ссылке «Конструктор»") void constructorLink(Browser browser) throws Exception {
        openProfile(browser);
        header.constructor();
        assertTrue(home.isOpen(), "Ссылка должна открыть конструктор");
    }
    @InBrowsers @DisplayName("Из кабинета по логотипу") void logoLink(Browser browser) throws Exception {
        openProfile(browser);
        header.logo();
        assertTrue(home.isOpen(), "Логотип должен открыть конструктор");
    }
    @InBrowsers @DisplayName("Выход закрывает доступ к кабинету") void logout(Browser browser) throws Exception {
        openProfile(browser);
        profile.logout();
        assertTrue(login.isOpen(), "После выхода должна открыться форма входа");
        home.open();
        header.account();
        assertTrue(login.isOpen(), "После выхода кабинет должен запрашивать вход");
    }
}
