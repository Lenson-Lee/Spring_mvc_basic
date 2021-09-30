<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title></title>
</head>
<body>

    <h1>회원가입에 성공했습니다!</h1>
    <h2>${user.getUserName()}님 환영합니다!</h2>

    <div>
        # 아이디: ${user.getUserId()}<br>
        # 비밀번호: ${pw}<br>
        # 이름: ${user.getUserName()}<br>
        # 나이: ${user.getUserAge()}<br>
        # 취미: ${user.getHobbys()}<br>
    </div>

    <ul>
        <c:forEach var="hobby" items="${user.hobbys}">
            <li>${hobby}</li>
        </c:forEach>
    </ul>
</body>
</html>