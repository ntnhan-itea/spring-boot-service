docker run --name spring-boot-mysql -e MYSQL_DATABASE=mySqlSpringBootDb -e MYSQL_ROOT_PASSWORD=123 -p 3306:3306 -d mysql:8.1.0

Access H2-DB: http://localhost:8080/h2-console
