# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.5.5/gradle-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.5.5/gradle-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.5.5/reference/htmlsingle/#boot-features-developing-web-applications)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)

### Additional Links
These additional references should also help you:

* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)



## 스프링 MVC 첫 세팅
1. 우측 상단 gradle눌러서 새로고침 한번
2. src/main/resources폴더로 가서 application.properties 파일에
   `server.port = 8181`으로 수정
3. src/main/java에 MvcApplication클래스 main메서드 실행해서 서버띄우기
4. 한글 인코딩 필터 설정 (main/resources/application.properties)
```
# 서버 포트 변경
server.port = 8181

```

## 스프링 MVC 기본 설정
1. 뷰리졸버 등록
- 메인메서드가 있는 클래스 혹은 config클래스 (@Configuration)에 아래의 내용을 작성
```java
//뷰 리졸버 등록 : 컨트롤러가 리턴한 문자열을 가지고 뷰 파일을 포워딩해주는 객체
@Bean
public InternalResourceViewResolver viewResolver() {
    InternalResourceViewResolver resolver = new InternalResourceViewResolver();
    resolver.setPrefix("/WEB-INF/views/");
    resolver.setSuffix(".jsp");
    return resolver;
}
```

2. 데이터베이스 설정
- C:\oraclexe\app\oracle\product\11.2.0\server\jdbc\lib 에서 ojbc6.jar
- 아래 설정 경로 /WEB-INF/lib에 추가하기
```groovy
//database 관련 라이브러리 추가
//jdbc 라이브러리
compile "org.springframework.boot:spring-boot-starter-jdbc"
//오라클 라이브러리 (11g edition - gradle, maven 라이센스 문제 공식 지원 불가)
compile fileTree(dir: '/src/main/webapp/WEB-INF/lib', include: ['*.jar'])
```

- 스프링에게 DataSource정보 알려주기 (Hikari DataSource)
```java
//각종 설정 정보를 담을 클래스 (빈 등록)
@Configuration
@ComponentScan(basePackages = "com.spring.mvc")
public class RootConfig {

    //DB접속정보 DataSource 등록
    @Bean
    public DataSource dataSource() {

        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName("oracle.jdbc.driver.OracleDriver");
        hikariConfig.setJdbcUrl("jdbc:oracle:thin:@localhost:1521:xe");
        hikariConfig.setUsername("java_web1");
        hikariConfig.setPassword("202104");

        HikariDataSource dataSource = new HikariDataSource(hikariConfig);
        return dataSource;
    }
}
```

## JSP 파일 템플릿
```jsp

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>

</body>
</html>
```