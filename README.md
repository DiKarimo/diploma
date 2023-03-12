# Тестирование веб-сервиса для покупки туров

## Процедура запуска автотестов веб-сервиса для покупки туров

### Подготовка

Перед началом работы выполните следующие действия:
1. Убедитесь, что на вашем локальном комьютере установлен браузер Google Chrome, если нет, то установите его по [ссылке](https://www.google.com/intl/ru_ru/chrome/);
2. Установите [IntelliJ IDEA](https://www.jetbrains.com/ru-ru/idea/download/#section=windows) для вашей ОС;
3. Установите [Docker Desktop](https://www.docker.com/) для вашей ОС.

Все продукты распостраняются бесплатно.

### Запуск автотестов

1. Запустите IntelliJ IDEA;
2. Откройте терминал и введите команду для клонирования репозитория:`git clone https://github.com/DiKarimo/diploma`;
3. Нажмите на клавишу <Enter>;
4. Запустите Docker Desktop;
5. В терминале IntelliJ IDEA введите команду для запуска необходимых контейнеров:`docker-compose up`.

#### Тестирование с поддержкой MySQL

1. Начните новую сессию в терминале IntelliJ IDEA — `Local (2)`. Для этого нажмите на кнопку ![кнопка](../../../Users/123/Downloads/2023-03-12_21-21-58.png);
2. Введите команду для запуска тестироваемого приложения:`java "-Dspring.datasource.url=jdbc:mysql://localhost:3306/app" -jar artifacts/aqa-shop.jar`;
3. Убедитесь, что приложение доступно по адресу:`http://localhost:8080/`;
4. Начните новую сессию в терминале IntelliJ IDEA — `Local (3)` и введите команду для запуска тестов:`.\gradlew clean test "-Ddb.url=jdbc:mysql://localhost:3306/app"`;
5. Введите команду для создания отчета:`./gradlew allureServe`;
6. После завершения работы закройте `Local (3)` и `Local (2)`.

#### Тестирование с поддержкой PostgreSQL

1. Начните новую сессию в терминале IntelliJ IDEA — `Local (2)`;
2. Введите команду для запуска тестироваемого приложения:`java "-Dspring.datasource.url=jdbc:postgresql://localhost:5432/app" -jar artifacts/aqa-shop.jar`;
3. Убедитесь, что приложение доступно по адресу:`http://localhost:8080/`;
4. Начните новую сессию в терминале IntelliJ IDEA — `Local (3)` и введите команду для запуска тестов:`.\gradlew clean test "-Ddb.url=jdbc:postgresql://localhost:5432/app"`;
5. Введите команду для создания отчета:`./gradlew allureServe`;
6. После завершения работы закройте `Local (3)` и `Local (2)`.

### Завершение работы

1. Начните новую сессию в терминале IntelliJ IDEA;
2. Введите команду для остановки контейнеров:`docker-compose down`.







