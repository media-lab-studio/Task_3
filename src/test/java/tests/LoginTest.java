package tests;

import io.qameta.allure.Step;
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

    @BeforeEach
    @Step("Подготовить страницы и данные сценария")
    public void setUpPage() {
        mainPage = new MainPage(driver);
        header = new HeaderComponent(driver);
        registrationPage = new RegistrationPage(driver);
        recoveryPage = new RecoveryPage(driver);
        loginPage = new LoginPage(driver);
        user = new TestUser("QaPass123");
        user.create();
    }

    @Test
    @DisplayName("Вход с главной страницы")
    public void testLoginFromMainPage() {
        mainPage.openBaseUrl()
                .clickLoginButton();
        loginPage.enterEmail(user.email)
                .enterPassword(user.password)
                .clickSubmitButton();
        assertTrue(mainPage.isOrderButtonDisplayed(), "После входа должна отображаться кнопка оформления заказа");
    }

    @Test
    @DisplayName("Вход через личный кабинет")
    public void testLoginFromAccount() {
        mainPage.openBaseUrl();
        header.clickAccountLink();
        loginPage.enterEmail(user.email)
                .enterPassword(user.password)
                .clickSubmitButton();
        assertTrue(mainPage.isOrderButtonDisplayed(), "После входа должна отображаться кнопка оформления заказа");
    }

    @Test
    @DisplayName("Вход из формы регистрации")
    public void testLoginFromRegistration() {
        registrationPage.openPage()
                .clickLoginLink();
        loginPage.enterEmail(user.email)
                .enterPassword(user.password)
                .clickSubmitButton();
        assertTrue(mainPage.isOrderButtonDisplayed(), "После входа должна отображаться кнопка оформления заказа");
    }

    @Test
    @DisplayName("Вход из формы восстановления пароля")
    public void testLoginFromRecovery() {
        recoveryPage.openPage()
                .clickLoginLink();
        loginPage.enterEmail(user.email)
                .enterPassword(user.password)
                .clickSubmitButton();
        assertTrue(mainPage.isOrderButtonDisplayed(), "После входа должна отображаться кнопка оформления заказа");
    }
}
