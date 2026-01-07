
## user registration
```bash
curl --location 'localhost:8080/register' \
--header 'Content-Type: application/json' \
--data '{
    "username":"admin",
    "password" : "123456"
}'
```

## user auth 
```bash
curl --location 'localhost:8080/authenticate' \
--header 'Content-Type: application/json' \
--data '{
     "username":"admin",
    "password" : "123456"

}'
```