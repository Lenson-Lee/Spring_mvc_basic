<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<h1>check.jsp파일입니다.</h1>

<form action="/jsp/validate.jsp">
    <label>
        <input type="text" name="age" placeholder="나이를 숫자로 입력!">
    </label>
    <button type="submit">확인</button>
</form>

</body>
</html>