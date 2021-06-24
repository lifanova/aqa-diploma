### Дипломный проект


**Процедура запуска**

Так как проект выполнялся в ОС Windows 8, то возможно было использование только Docker Toolbox, что накладывает следующие особенности:  
1. в файле application.properties в значении свойства spring.datasource.url указан хост 192.168.99.100 - при запуске в Windows 10 нужно заменить это значение на localhost.  

**Шаги**  

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
`gradlew test --info -Dselenide.headless=true`

5. После окончания тестирования остановить контейнер  
`docker-compose -f docker-compose-postgres.yml down`

**Генерация отчетов Allure**  
`gradlew clean test allureReport`  
`gradlew allureServe`




