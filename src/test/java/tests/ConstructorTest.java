package tests;

import io.qameta.allure.Step;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.MainPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConstructorTest extends BaseTest {

    private static final String SECTION_SAUCES = "Соусы";
    private static final String SECTION_BUNS = "Булки";
    private static final String SECTION_FILLINGS = "Начинки";

    private MainPage mainPage;

    @BeforeEach
    public void setUpPage() {
        mainPage = new MainPage(driver());
    }

    @Test
    @DisplayName("Переход к булкам")
    public void testBuns() {
        mainPage.openBaseUrl()
                .clickSectionTab(SECTION_SAUCES);
        assertActiveSection(SECTION_SAUCES);
        mainPage.clickSectionTab(SECTION_BUNS);
        assertActiveSection(SECTION_BUNS);
    }

    @Test
    @DisplayName("Переход к соусам")
    public void testSauces() {
        mainPage.openBaseUrl()
                .clickSectionTab(SECTION_SAUCES);
        assertActiveSection(SECTION_SAUCES);
    }

    @Test
    @DisplayName("Переход к начинкам")
    public void testFillings() {
        mainPage.openBaseUrl()
                .clickSectionTab(SECTION_FILLINGS);
        assertActiveSection(SECTION_FILLINGS);
    }

    @Step("Проверить, что активен раздел «{section}»")
    private void assertActiveSection(String section) {
        assertEquals(section, mainPage.getActiveSection(section), "Должен открыться раздел «" + section + "»");
    }
}
