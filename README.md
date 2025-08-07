# Hackaton
Postgraduate course completion work

## Domain-Driven Design
https://miro.com/app/board/uXjVJezNcEM=/

### Run Performance Test
Run the application with the performance profile to enable performance test.
```bash
mvn clean gatling:test -Pperformance
```

### Run Application in Production Mode
Enable docker compose and database by setting the following properties in `application.properties`:
```properties
# Banco de dados
spring.datasource.url=jdbc:mysql://mysql:3306/mydatabase
spring.datasource.username=myuser
spring.datasource.password=secret
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=false
spring.jpa.hibernate.naming.physical-strategy=org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl

# Docker Compose
spring.docker.compose.enabled=true

# Cache
spring.data.redis.host=redis
```