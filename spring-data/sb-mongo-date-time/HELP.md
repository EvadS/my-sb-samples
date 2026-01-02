


```
    docker build -t some-mongo .
```


```
    docker run -d -p 27017:27017 some-mongo -e MONGO_INITDB_ROOT_USERNAME=mongouser -e MONGO_INITDB_ROOT_PASSWORD=secretpassword
```





```
docker run -d --name some-mongo \
  -p 27017:27017 \
  -v mongo-data:/data/db \
  -e MONGO_INITDB_ROOT_USERNAME=mongouser \
  -e MONGO_INITDB_ROOT_PASSWORD=secretpassword 
```


```
docker run -d --name my-mongo \
  -p 27017:27017 \
  -v mongo-data:/data/db \
  -e MONGO_INITDB_ROOT_USERNAME=mongouser \
  -e MONGO_INITDB_ROOT_PASSWORD=secretpassword \
  mongo
```

