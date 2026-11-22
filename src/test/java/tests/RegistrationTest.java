package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.LoginPage;
import pages.MainPage;
import pages.RegistrationPage;
import util.TestUser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegistrationTest extends BaseTest {

    private MainPage mainPage;
    private LoginPage loginPage;
    private RegistrationPage registrationPage;

    @BeforeEach
    public void setUpPage() {
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        registrationPage = new RegistrationPage(driver);
    }

    @Test
    @DisplayName("Регистрация с паролем из шести символов")
    public void testRegistrationWithMinimumPassword() {
        user = new TestUser("Qa1234");
        registrationPage.openPage()
                .enterName(user.name)
                .enterEmail(user.email)
                .enterPassword(user.password)
                .clickSubmitButton();
        assertTrue(loginPage.isLoginFormDisplayed(), "После регистрации должна открыться форма входа");
        loginPage.enterEmail(user.email)
                .enterPassword(user.password)
                .clickSubmitButton();
        assertTrue(mainPage.isOrderButtonDisplayed(), "После входа должна отображаться кнопка оформления заказа");
    }

    @Test
    @DisplayName("Ошибка при пароле из пяти символов")
    public void testShortPasswordError() {
        user = new TestUser("Qa123");
        registrationPage.openPage()
                .enterName(user.name)
                .enterEmail(user.email)
                .enterPassword(user.password)
                .clickSubmitButton();
        assertEquals("Некорректный пароль", registrationPage.getPasswordErrorMessage(),
                "Под полем пароля должна отображаться ошибка");
    }
}
