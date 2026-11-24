# Stellar Burgers — UI-тесты

13 сценариев выполняются в Google Chrome и Яндекс.Браузере. Каждый запуск получает отдельную браузерную сессию. Тесты входа и кабинета создают пользователя через API; тест регистрации создаёт его через интерфейс. Очистка выполняется в `@AfterEach`.

## Запуск в IDEA или PowerShell

1. Откройте `pom.xml` как Maven-проект. Для Project SDK и Maven Runner выберите JDK 11. В терминале проверьте `mvn -v`: Maven должен использовать Java 11.
2. Установите Google Chrome и Яндекс.Браузер. Для Windows в репозиторий включён `drivers/chromedriver.exe`. Сравните первые три компонента его версии (`MAJOR.MINOR.BUILD`) с версией Chrome. Если они различаются, установите подходящий драйвер из [Chrome for Testing](https://googlechromelabs.github.io/chrome-for-testing/). Для Яндекс.Браузера распакуйте совместимый `yandexdriver.exe` из [YandexDriver](https://github.com/yandex/YandexDriver/releases) в `drivers/`. При необходимости путь к драйверу Chrome можно задать через `-Dwebdriver.chrome.driver`, а к драйверу Яндекса — через `-Dyandex.driver` или `YANDEX_DRIVER`.
3. Из корня проекта проверьте версию драйвера и запустите весь набор:

   ```powershell
   .\drivers\chromedriver.exe --version
   mvn clean test
   ```

   Maven последовательно выполняет весь набор в Chrome и Яндекс.Браузере. Оба браузера запускаются с видимым окном, развёрнутым на весь экран. При нестандартной установке передайте `"-Dyandex.binary=полный путь к браузеру"`. При запуске отдельного JUnit-теста из IDEA по умолчанию используется Chrome; для Яндекс.Браузера добавьте `-Dbrowser=yandex` в VM options.

## Отчёт

Скачать файл [allure-report/index.html](allure-report/index.html) и открыть в браузере. Чтобы сформировать такой же автономный отчёт после нового прогона, используйте Allure CLI 2.32:

```powershell
allure generate target/allure-results --clean --single-file -o allure-report
```

## Структура проекта

| Каталог | Содержимое |
| --- | --- |
| `src/main/java/pages` | `BasePage`, `HeaderComponent` и пять страниц. Методы действий возвращают страницу для вызова цепочкой. |
| `src/test/java/tests` | `BaseTest` создаёт и закрывает браузер. Каждый из четырёх классов сценариев создаёт свои Page Object в `setUpPage()` и проверяет результат через JUnit. |
| `src/test/java/util` | `TestUser` готовит и удаляет пользователей через API с помощью REST Assured. |
| `src/test/resources` | Настройки Allure для тестового прогона. |

Матрица прогона: регистрация (2 сценария), вход (4), кабинет и выход (4), разделы конструктора (3). В полном отчёте должно быть 26 результатов: по одному на каждый сценарий и браузер.
