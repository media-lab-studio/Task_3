package tests;

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

    @BeforeEach
    public void setUpPage() {
        mainPage = new MainPage(driver);
        header = new HeaderComponent(driver);
        loginPage = new LoginPage(driver);
        profilePage = new ProfilePage(driver);
        user = new TestUser("QaPass123");
        user.create();
        loginPage.openPage()
                .enterEmail(user.email)
                .enterPassword(user.password)
                .clickSubmitButton();
        assertTrue(mainPage.isOrderButtonDisplayed(), "После входа должна отображаться кнопка оформления заказа");
    }

    @Test
    @DisplayName("Переход в личный кабинет")
    public void testOpenAccount() {
        header.clickAccountLink();
        assertEquals(user.email, profilePage.getLoginValue(), "Должен открыться профиль текущего пользователя");
    }

    @Test
    @DisplayName("Переход из кабинета по ссылке «Конструктор»")
    public void testConstructorLink() {
        header.clickAccountLink();
        assertEquals(user.email, profilePage.getLoginValue(), "Должен открыться профиль текущего пользователя");
        header.clickConstructorLink();
        assertTrue(mainPage.isConstructorDisplayed(), "Ссылка должна открыть конструктор");
    }

    @Test
    @DisplayName("Переход из кабинета по логотипу")
    public void testStellarLogo() {
        header.clickAccountLink();
        assertEquals(user.email, profilePage.getLoginValue(), "Должен открыться профиль текущего пользователя");
        header.clickStellarLogo();
        assertTrue(mainPage.isConstructorDisplayed(), "Логотип должен открыть конструктор");
    }

    @Test
    @DisplayName("Выход из аккаунта")
    public void testLogout() {
        header.clickAccountLink();
        assertEquals(user.email, profilePage.getLoginValue(), "Должен открыться профиль текущего пользователя");
        profilePage.clickLogoutButton();
        assertTrue(loginPage.isLoginFormDisplayed(), "После выхода должна открыться форма входа");
        mainPage.openBaseUrl();
        header.clickAccountLink();
        assertTrue(loginPage.isLoginFormDisplayed(), "После выхода кабинет должен запрашивать вход");
    }
}
