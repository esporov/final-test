# Приложение для заказа и доставки еды
## Стэк технологий
Java 11, Spring Boot, Spring Data JPA, Spring Security(не подключено), Spring Web, LiquiBase, PostgreSQL, Hibernate, 
Lombok, RabbitMQ, Swagger, Slf4j, JUnit, Mockito
## Проект состоит из 3 основных сервисов
### Order-service (сервис для заказа еды)
#### Основная функциональность:
- Создание заказа
- Получить заказ по UUID
- Обновление статуса заказа по кухне
- Обновление статуса заказа по доставке
- localhost:8083/order-service/swagger-ui.html
### Restaurant-service (приготовление блюд)
#### Основная функциональность:
- Обновление статуса по кухне на ACCEPTED
- Обновление статуса по кухне на DENIED
- Обновление статуса по кухне на IN_WORK
- Обновление статуса по кухне на DONE
- Поиск блюд по id
- localhost:8083/order-service/swagger-ui.html
### Delivery-service (доставка заказов)
#### Основная функциональность:
- Получить курьера по id
- Получить список курьеров по статусу
- Обновить статус курьера
- localhost:8080/order-service/swagger-ui.html
### Тестирование
- postman файл в папке postman (удивительно)
### Запуск проекта
1. В среде разработки запустить luquibase:update (migration module) для инициализации sql скриптов
2. Запустить команду по созданию RabbitMQ контейнера 
- docker run -t  --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3-management
3. Вероятно придется поменять в application.yaml у модулей настройки БД (логин, пароль)

### Примечания:
Не получилось обработать ситуацию, когда нет свободных курьеров.
