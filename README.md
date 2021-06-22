### Дипломный проект

**Структура проекта**


**Процедура запуска**

1. Запустить docker-контейнер с СУБД  

а) Postgres:  
`docker-compose -f docker-compose-postgres.yml up -d`  

б) MySQL:  
`docker-compose -f docker-compose-mysql.yml up -d`  

2. Запустить Node.js  

`npm start`  

3. Запустить SUT  

`java -jar artifacts\aqa-shop.jar`

4. Запустить автотесты  
`gradleview test --info -Dselenide.headless=true`

5. После окончания тестирования остановить контейнер  
`docker-compose -f docker-compose-postgres.yml down`

**Генерация отчетов Allure**  
`gradlew clean test allureReport`  
`gradlew allureServe`




