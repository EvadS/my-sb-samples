

Running the Application

go to the root directory of the application and type the following command to run it -
```bash
    mvn spring-boot:run
```


## samples
### Employe
Base mySql crud

### Notes
base mysql crud + jpa audit

### Date Time working
Databases support various data types to store date and time information. The most commonly used ones are:

DATE to save a date without time information,
TIME to store a time without a date, and
TIMESTAMP to store date and time information.

#### Java date time types
* Типы из API даты и времени (Date and Time API):
Это такие классы, как java.time.LocalDate, java.time.LocalDateTime, java.time.OffsetTime, java.time.OffsetDateTime, java.time.ZonedDateTime, java.time.Duration. Современные приложения должны использовать именно их вместо устаревших типов.
* SQL-специфичные типы: java.sql.Date, java.sql.Time и java.sql.Timestamp.
* устаревшие типы: java.util.Date и java.util.Calendar.


swagger tutorial
https://bell-sw.com/blog/documenting-rest-api-with-swagger-in-spring-boot-3/

http://localhost:8080/v3/api-docs
http://localhost:8080/swagger-ui/index.html

