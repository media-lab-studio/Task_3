package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.*;
import util.TestUser;

public class LoginTest extends BaseTest {

    private MainPage mainPage;
    private HeaderComponent header;
    private RegistrationPage registrationPage;
    private RecoveryPage recoveryPage;
    private LoginPage loginPage;
    private TestUser testUser;

    @BeforeEach
    public void setUpPage() {
        mainPage = new MainPage(driver());
        header = new HeaderComponent(driver());
        registrationPage = new RegistrationPage(driver());
        recoveryPage = new RecoveryPage(driver());
        loginPage = new LoginPage(driver());
        testUser = prepareRegisteredUser();
    }

    @Test
    @DisplayName("Вход с главной страницы")
    public void testLoginFromMainPage() {
        mainPage.openBaseUrl()
                .clickLoginButton();
        LoginSteps.loginAndVerifyOrderButtonVisible(loginPage, mainPage, testUser);
    }

    @Test
    @DisplayName("Вход через личный кабинет")
    public void testLoginFromAccount() {
        mainPage.openBaseUrl();
        header.clickAccountLink();
        LoginSteps.loginAndVerifyOrderButtonVisible(loginPage, mainPage, testUser);
    }

    @Test
    @DisplayName("Вход из формы регистрации")
    public void testLoginFromRegistration() {
        registrationPage.openPage()
                .clickLoginLink();
        LoginSteps.loginAndVerifyOrderButtonVisible(loginPage, mainPage, testUser);
    }

    @Test
    @DisplayName("Вход из формы восстановления пароля")
    public void testLoginFromRecovery() {
        recoveryPage.openPage()
                .clickLoginLink();
        LoginSteps.loginAndVerifyOrderButtonVisible(loginPage, mainPage, testUser);
    }
}
