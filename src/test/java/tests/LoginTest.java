package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.HeaderComponent;
import pages.LoginPage;
import pages.MainPage;
import pages.RecoveryPage;
import pages.RegistrationPage;
import util.TestUser;

import static org.junit.jupiter.api.Assertions.assertTrue;

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
        testUser = prepareRegisteredUser("QaPass123");
    }

    @Test
    @DisplayName("Вход с главной страницы")
    public void testLoginFromMainPage() {
        mainPage.openBaseUrl()
                .clickLoginButton();
        loginAsCurrentUser();
    }

    @Test
    @DisplayName("Вход через личный кабинет")
    public void testLoginFromAccount() {
        mainPage.openBaseUrl();
        header.clickAccountLink();
        loginAsCurrentUser();
    }

    @Test
    @DisplayName("Вход из формы регистрации")
    public void testLoginFromRegistration() {
        registrationPage.openPage()
                .clickLoginLink();
        loginAsCurrentUser();
    }

    @Test
    @DisplayName("Вход из формы восстановления пароля")
    public void testLoginFromRecovery() {
        recoveryPage.openPage()
                .clickLoginLink();
        loginAsCurrentUser();
    }

    @io.qameta.allure.Step("Войти и проверить доступность оформления заказа")
    private void loginAsCurrentUser() {
        loginPage.enterEmail(testUser.email)
                .enterPassword(testUser.password)
                .clickSubmitButton();
        assertTrue(mainPage.isOrderButtonDisplayed(), "После входа должна отображаться кнопка «Оформить заказ»");
    }
}
