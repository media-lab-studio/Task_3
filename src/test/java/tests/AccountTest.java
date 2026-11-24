package tests;

import io.qameta.allure.Step;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.HeaderComponent;
import pages.LoginPage;
import pages.MainPage;
import pages.ProfilePage;
import util.TestUser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AccountTest extends BaseTest {

    private MainPage mainPage;
    private HeaderComponent header;
    private LoginPage loginPage;
    private ProfilePage profilePage;
    private TestUser testUser;

    @BeforeEach
    public void setUpPage() {
        mainPage = new MainPage(driver());
        header = new HeaderComponent(driver());
        loginPage = new LoginPage(driver());
        profilePage = new ProfilePage(driver());
        testUser = prepareRegisteredUser();
        loginPage.openPage();
        LoginSteps.loginAndVerifyOrderButtonVisible(loginPage, mainPage, testUser);
    }

    @Test
    @DisplayName("Переход в личный кабинет")
    public void testOpenAccount() {
        openProfile();
    }

    @Test
    @DisplayName("Переход из кабинета по ссылке «Конструктор»")
    public void testConstructorLink() {
        openProfile();
        header.clickConstructorLink();
        assertConstructorDisplayed();
    }

    @Test
    @DisplayName("Переход из кабинета по логотипу")
    public void testStellarLogo() {
        openProfile();
        header.clickStellarLogo();
        assertConstructorDisplayed();
    }

    @Test
    @DisplayName("Выход из аккаунта")
    public void testLogout() {
        openProfile();
        profilePage.clickLogoutButton();
        assertTrue(loginPage.isLoginFormDisplayed(), "После выхода должна открыться форма входа");
        mainPage.openBaseUrl();
        header.clickAccountLink();
        assertTrue(loginPage.isLoginFormDisplayed(), "Без авторизации личный кабинет недоступен");
    }

    @Step("Проверить, что открылся конструктор")
    private void assertConstructorDisplayed() {
        assertTrue(mainPage.isConstructorDisplayed(), "Должен открыться конструктор");
    }

    @Step("Открыть профиль и проверить email пользователя")
    private void openProfile() {
        header.clickAccountLink();
        assertEquals(testUser.email, profilePage.getLoginValue(), "Должен открыться профиль текущего пользователя");
    }
}
