package stellar.tests;

import org.junit.jupiter.api.DisplayName;
import stellar.support.*;
import static org.junit.jupiter.api.Assertions.*;

class RegistrationTest extends BaseTest {
    @InBrowsers @DisplayName("Регистрация с паролем из шести символов")
    void minimumPassword(Browser browser) {
        start(browser);
        user = new TestUser("Qa1234");
        registration.open().register(user.name, user.email, user.password);
        assertTrue(login.isOpen(), "После регистрации должна открыться форма входа");
        login.login(user.email, user.password);
        assertTrue(home.canOrder(), "Новый пользователь должен войти со своим паролем");
    }

    @InBrowsers @DisplayName("Ошибка при пароле из пяти символов")
    void shortPassword(Browser browser) {
        start(browser);
        user = new TestUser("Qa123");
        registration.open().register(user.name, user.email, user.password);
        assertEquals("Некорректный пароль", registration.getPasswordError());
    }
}
