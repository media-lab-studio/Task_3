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

   При нестандартной установке передайте `"-Dyandex.binary=полный путь к браузеру"`. Для запуска с окнами добавьте `-Dheadless=false`. В IDEA те же параметры задаются в Run Configuration.

## Отчёт

Скачать файл [allure-report/index.html](allure-report/index.html) и открыть в браузере. Чтобы сформировать такой же автономный отчёт после нового прогона, используйте Allure CLI 2.32:

```powershell
allure generate target/allure-results --clean --single-file -o allure-report
```

`src/test/java/stellar/pages` содержит Page Object и общую шапку; `support` — фабрику браузеров и API для тестовых пользователей; `tests` — проверки регистрации, входа, кабинета и конструктора.
