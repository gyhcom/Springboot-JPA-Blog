# Springboot-JPA-Blog
### 의존성
 - Spring Boot DevTools
 - Lombok
 - Spring Data JPA
 - Spring Security
 - Spring Web
 - Oauth2
###데이터베이스
oracle cloud를 이용한 mysql
접속 정보
Ip:130.162.133.248:3306
Port:3306
Db:gyhBlog
```
### yml 설정
server:
  port: 8000
  servlet:
    context-path: /
    encoding:
      charset: UTF-8
      enabled: true
      force: true
    
spring:
  mvc:
    view:
      prefix: /WEB-INF/views/
      suffix: .jsp
      
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://130.162.133.248:3306/gyhBlog?serverTimezone=Asia/Seoul
    username: gyh
    password: rkddusgh12
  jpa:
    open-in-view: true
    hibernate:
      ddl-auto: update
      naming:
        physical-strategy: org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl
      use-new-id-generator-mappings: false
    show-sql: true
    properties:
      hibernate.format_sql: true

  jackson:
    serialization:
      fail-on-empty-beans: false
cos:
  key: gyh1234
```