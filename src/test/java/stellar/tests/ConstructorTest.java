package stellar.tests;

import org.junit.jupiter.api.DisplayName;
import stellar.support.Browser;

class ConstructorTest extends UiTest {
    @InBrowsers @DisplayName("Переход к булкам") void buns(Browser browser) {
        start(browser);
        home.open();
        home.select("Соусы");
        home.checkSection("Соусы");
        home.select("Булки");
        home.checkSection("Булки");
    }
    @InBrowsers @DisplayName("Переход к соусам") void sauces(Browser browser) {
        start(browser);
        home.open();
        home.select("Соусы");
        home.checkSection("Соусы");
    }
    @InBrowsers @DisplayName("Переход к начинкам") void fillings(Browser browser) {
        start(browser);
        home.open();
        home.select("Начинки");
        home.checkSection("Начинки");
    }
}
