package tests;

import io.qameta.allure.Step;
import pages.LoginPage;
import pages.MainPage;
import util.TestUser;

import static org.junit.jupiter.api.Assertions.assertTrue;

final class LoginSteps {
    private LoginSteps() {
    }

    @Step("Войти и проверить доступность оформления заказа")
    static void loginAndVerifyOrderButtonVisible(LoginPage loginPage, MainPage mainPage, TestUser user) {
        loginPage.enterEmail(user.email)
                .enterPassword(user.password)
                .clickSubmitButton();
        assertTrue(mainPage.isOrderButtonDisplayed(), "После входа должна отображаться кнопка «Оформить заказ»");
    }
}
