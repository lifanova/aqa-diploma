### Дипломный проект

### Процедура запуска

1. Запустить docker-контейнер с СУБД  

а) Postgres:
`docker-compose -f docker-compose-postgres.yml up -d`  

б) MySQL:  
`docker-compose -f docker-compose-mysql.yml up -d`  

2. Запустить Node.js  

`npm start`  

3. Запустить SUT  

`java -jar artifacts\aqa-shop.jar`



