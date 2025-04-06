# Spring boot + mysql + k8s

## Requirements for running app
### Data base
Before the Spring Boot application can interact with the database, the specified database must exist
```mysql script
    CREATE DATABASE your_database_name;
```
### environments variables to start
* DB_NAME: demoDb
* DB_SERVER: localhost
* DB_PORT: 3306
* DB_USERNAME: root
* DB_PASSWORD: 31323334

## build project
```
    ./mvnw clean install -DskipTests
```

## Crud operation

### get all users
```bash
    curl http://localhost:8080/api/users
```

### create user
```bash
curl --location 'http://localhost:8080/api/users' \
--header 'Content-Type: application/json' \
--data-raw '{
    "firstName": "first2 name",
    "lastName": "last name",
    "email":"mail@mail.com"
}'
```

## working with k8s
## Dockers instructions
### create image command

```bash
    docker build . -t evaddev/sb-mysql-users:latest -t evaddev/sb-mysql-users:1.0.0
```

### check image
```bash
    docker images | grep sb-mysql-users
```

### Push the Docker Image to a Registry

should do login before push
repository must be created at docker hub before
```bash
    docker push evaddev/sb-mysql-users --all-tags
```
##  Working with local cluster
### start minikube
```bash
    minikube status
```
### to check
```bash
    minikube status
```

go k8s folder on project directory
```bash
   cd k8s
```
### deploy your configmap:
```bash
    kubectl apply -f mysql-configmap.yaml
```
### deploy secrets
```bash
    kubectl apply -f .\secrets.yaml
```

### deploy my-sql deployment
```bash
    kubectl apply -f mysql-deployment.yaml
```

### validate
```bash
    kubectl get all
```
### check mysql
#### ennter to service
````bash
    kubectl exec -it deploy/demo-app-mysql -- /bin/bash
```

#### run my sql command line inside container
```bash
    mysql --user=$MYSQL_USER --password=$MYSQL_PASSWORD
```

#### sql script to test
```bash
    select CURRENT_DATE()
```
#### tonnel to use load balancer
```
    minikube tunnel
```

### deploy spring application
```
    kubectl apply -f .\app-deployment.yaml
```

### logs
```bash
    kubectl logs pod/[POD_NAME]
```

### Stop and clean resources
 ```bash
    kubectl delete deploy [NAME]
    kubectl delete  [SERVICE_NAME]
    kubectl delete secrets  [SECRETS_NAME]
 ```

How I see on my local
```
kubectl delete deploy demo-app-mysql
kubectl delete deploy demo-app-spring

kubectl delete  service/demo-app-mysql
kubectl delete  service/demo-app-spring
kubectl delete secrets mysql-pass
```

### free local cluster
```
    minikube stop
```

### free memory
```
    minikube delete
```