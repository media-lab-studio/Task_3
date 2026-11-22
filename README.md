# Stellar Burgers — UI-тесты

13 сценариев выполняются в Google Chrome и Яндекс.Браузере. Каждый запуск получает отдельную браузерную сессию. Тесты входа и кабинета создают пользователя через API; тест регистрации создаёт его через интерфейс. Очистка выполняется в `@AfterEach`.

## Запуск в IDEA или PowerShell

1. Откройте `pom.xml` как Maven-проект. Для Project SDK и Maven Runner выберите JDK 11. В терминале проверьте `mvn -v`: Maven должен использовать Java 11.
2. Установите Chrome и Яндекс.Браузер. Скачайте [YandexDriver](https://github.com/yandex/YandexDriver/releases) для своей версии браузера и распакуйте его. Для Chrome драйвер подбирает Selenium Manager; при необходимости можно передать `-Dwebdriver.chrome.driver=...`.
3. Укажите путь к YandexDriver и запустите весь набор:

   ```powershell
   $env:YANDEX_DRIVER = 'C:\drivers\yandexdriver.exe'
   mvn clean test
   ```

   Maven последовательно выполняет весь набор в Chrome и Яндекс.Браузере. При нестандартной установке передайте `"-Dyandex.binary=полный путь к браузеру"`. Для запуска с окнами добавьте `-Dheadless=false`. При запуске отдельного JUnit-теста из IDEA по умолчанию используется Chrome; для Яндекс.Браузера добавьте `-Dbrowser=yandex` в VM options.

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
