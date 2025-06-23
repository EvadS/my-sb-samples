# Getting Started

### Reference Documentation

intellij + flyway maven plugin 
1. создаем подключение через intellij к необходимой базе 
 View - tool window - database 
ПКМ на необходимой базе generate Flyway migration 
в новом окне
 Actual state - на основе чего мы делаем миграцию
 Target - куда миграция должна быть применена

в данном проекте code first: Actual (Model) - target(DB)


2. Maven plugin 
  настройки подключения src/main/resources/flyway.conf
3. проверить состояние
```bash
 mvn flyway:info  
```
pending - файл миграции не применен
sucess - миграция успешно применена



запуск миграций
mvn flyway:migrate

