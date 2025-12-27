
Запуск  монго из docker файла 

задаем 
 - пользователь
 - пароль 
 - коллекция с которой работаем 
 - здесь храним данные для базы testdata.json 

```
    docker build -t evads/my-mongo-name:version1.0 .
```

```
    docker run --name my-mongo-container -p 27017:27017 -d evads/my-mongo-name:version1.0
```

connect to docker 
```
    docker exec -it my-mongo-container bash
```

To start the shell, run
```
    mongosh
```
 
```
    mongosh admin -u root -p root
```

To list all databases
```
    show dbs
```

To enter or use a given database:
```
    use databasename
```

use testdb

show collections