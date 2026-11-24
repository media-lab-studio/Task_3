package tests;

import io.qameta.allure.Step;
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
        mainPage = new MainPage(driver());
        loginPage = new LoginPage(driver());
        registrationPage = new RegistrationPage(driver());
    }

    @Test
    @DisplayName("Регистрация с паролем из шести символов")
    public void testRegistrationWithMinimumPassword() {
        TestUser user = prepareUser("Qa1234");
        submitRegistrationForm(user);
        assertTrue(loginPage.isLoginFormDisplayed(), "После регистрации должна открыться форма входа");
        LoginSteps.loginAndVerifyOrderButtonVisible(loginPage, mainPage, user);
    }

    @Test
    @DisplayName("Ошибка при пароле из пяти символов")
    public void testShortPasswordError() {
        TestUser user = prepareUser("Qa123");
        submitRegistrationForm(user);
        assertEquals("Некорректный пароль", registrationPage.getPasswordErrorMessage(),
                "Под полем пароля должна отображаться ошибка");
    }

    @Step("Заполнить и отправить форму регистрации")
    private void submitRegistrationForm(TestUser user) {
        registrationPage.openPage()
                .enterName(user.name)
                .enterEmail(user.email)
                .enterPassword(user.password)
                .clickSubmitButton();
    }
}
