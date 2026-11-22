package stellar.tests;

import org.junit.jupiter.api.DisplayName;
import stellar.support.Browser;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ConstructorTest extends UiTest {
    @InBrowsers @DisplayName("Переход к булкам") void buns(Browser browser) {
        start(browser);
        home.open();
        home.select("Соусы");
        assertEquals("Соусы", home.getActiveSectionAt("Соусы"));
        home.select("Булки");
        assertEquals("Булки", home.getActiveSectionAt("Булки"));
    }
    @InBrowsers @DisplayName("Переход к соусам") void sauces(Browser browser) {
        start(browser);
        home.open();
        home.select("Соусы");
        assertEquals("Соусы", home.getActiveSectionAt("Соусы"));
    }
    @InBrowsers @DisplayName("Переход к начинкам") void fillings(Browser browser) {
        start(browser);
        home.open();
        home.select("Начинки");
        assertEquals("Начинки", home.getActiveSectionAt("Начинки"));
    }
}
