package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.MainPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConstructorTest extends BaseTest {

    private MainPage mainPage;

    @BeforeEach
    public void setUpPage() {
        mainPage = new MainPage(driver());
    }

    @Test
    @DisplayName("Переход к булкам")
    public void testBuns() {
        mainPage.openBaseUrl()
                .clickSectionTab("Соусы");
        assertEquals("Соусы", mainPage.getActiveSection("Соусы"), "Перед переходом к булкам должны открыться соусы");
        mainPage.clickSectionTab("Булки");
        assertEquals("Булки", mainPage.getActiveSection("Булки"), "Должен открыться раздел «Булки»");
    }

    @Test
    @DisplayName("Переход к соусам")
    public void testSauces() {
        mainPage.openBaseUrl()
                .clickSectionTab("Соусы");
        assertEquals("Соусы", mainPage.getActiveSection("Соусы"), "Должен открыться раздел «Соусы»");
    }

    @Test
    @DisplayName("Переход к начинкам")
    public void testFillings() {
        mainPage.openBaseUrl()
                .clickSectionTab("Начинки");
        assertEquals("Начинки", mainPage.getActiveSection("Начинки"), "Должен открыться раздел «Начинки»");
    }
}
