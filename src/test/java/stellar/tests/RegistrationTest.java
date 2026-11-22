package stellar.tests;

import org.junit.jupiter.api.DisplayName;
import stellar.support.*;

class RegistrationTest extends UiTest {
    @InBrowsers @DisplayName("Регистрация с паролем из шести символов")
    void minimumPassword(Browser browser) {
        start(browser);
        user = new TestUser("Qa1234");
        registration.open();
        registration.register(user.name, user.email, user.password);
        login.checkOpen();
        login.login(user.email, user.password);
        home.checkSignedIn();
    }

}
