# Spring Boot 3 - Quartz Scheduler Implementation Example

This is a simple example of how to implement Quartz Scheduler in a Spring Boot application.
This example will show you how to schedule a job.

### mysql database
```
docker run --name docker-mysql -e MYSQL_ROOT_PASSWORD=123456 -p 3306:3306 -d mysql
```

msyql username:root
mysql password:123456

### Install dependencies
```bash
mvn clean install
```

### Run project
```bash
mvn spring-boot:run 
```

### Build project
```bash
mvn clean package
```
