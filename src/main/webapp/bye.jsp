<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>

    <!-- 스크립트릿: 속에 자바코드를 넣으면 먹힌다. 웹 콘솔에서 우클릭- 페이지소스 보기 하면 자바 코드는 안보임 -->
    <%
        int a = 10, b = 20;
    %>

    <% for (int i = 0; i < 3; i++) {%>
    <h1>jsp 테스트 중입니다~ <%= a + b %></h1>
    <% } %>
</body>
</html>