## Relex 2024 internship for JAVA BACKEND

---
### Pushkin Nikita / Пушкин Никита

---
### Видео тестирования:

Video of the testing:

### Основной функционал:

    POST регистрация (все сотрудники)
    POST вход (все сотрудники)
    POST стать админом (все сотрудники)
    PATCH уволить сотрудника (админ)
    POST создать продукт
    DELETE удалить продукт
    GET получить всю историю сбора продуктов за ? дней у всех сотрудников json (админ)
    GET получить всю историю сбора продуктов за ? дней у одного сотрудника json (админ)
    POST собрать продукт
    GET посмотреть сколько осталось собрать

### Технологии

    Spring Boot
    Maven
    Spring Security Starter
    JWT token
    PostgreSQL
    Liquibase for migration control
    JPA
    Jakarta Validators for validation
    ModelMapper
    Docker for storing DB (created docker-compose)
    SLF4J for logging
    Lombok
    Spring Mail

### Описание

В этом проекте существует разделение пользователей по ролям: admin и user.

Пользователям со статусом user доступна регистрация, вход, запрос на добавление роли администратора, сбор продукта, добавление продукта и просмотр инфо сколько осталось ещё собрать.

Пользователям со статусом admin доступна получение истории производства продукта json и увольнение сотрудников.

### Запуск
Конфигурация Spring Boot Application

Перед запуском поднять докер-контейнер с бд помощью docker-compose

Для тестирования необходим десктоп-клиент Postman.`