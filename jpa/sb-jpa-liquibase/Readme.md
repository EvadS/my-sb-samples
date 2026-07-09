



Liquibase in Spring Boot: Full Beginner-to-Advanced Guide
(https://www.youtube.com/watch?v=a1vGvdYKP2Y)


working with databases through Docker containers  

migration tool 
one change set in one changelog file

For Spring Boot, in IntelliJ I added a VM option:
```
    -Duser.timezone=UTC
```

liquibase diff 

uses compiled classes 

```
    mvn liquibase:update
```

сейчас работает 
---------

## Показывает итоговую конфигурацию с подставленными значениями (без запуска контейнеров)
docker compose -f compose.yml config


## Поднять сервисы
docker compose -f compose.yml up -d