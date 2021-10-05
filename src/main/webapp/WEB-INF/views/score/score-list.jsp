<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>성적관리 프로그램</title>
<style>
    label {
        display: block;
    }

    .score-list > li {
        margin-bottom: 10px;
    }

    .score-list > li:first-child {
        font-size: 1.2em;
        color: blue;
        font-weight: 700;
        border-bottom: 1px solid skyblue;
    }

    .del-btn {
        width: 10px;
        height: 10px;
        background: crimson;
        color: #fff;
        border-radius: 5px;
        text-decoration: none;
        font-size: 0.7em;
        padding: 6px;
    }
    .del-btn:hover {
        background: pink;
    }
</style>
</head>
<body>
    <h1>시험 점수 등록</h1>
    <form action="/score/register" method="POST">
        <label>
            # 이름: <input type="text" name="name">
        </label>
        <label>
            # 국어: <input type="text" name="kor">
        </label>
        <label>
            # 영어: <input type="text" name="eng">
        </label>
        <label>
            # 수학: <input type="text" name="math">
        </label>
        <label>
            <button type="submit">확인</button>
        </label>
        <label>
            <button type="button" id="go-home">홈화면으로</button>
        </label>

    </form>

    <hr>

    <ul class="score-list">
        <li>총 학생 수: ${scoreList.size()}명</li>

        <c:forEach var="s" items="${scoreList}">
            <li>
                # 학번: ${s.stuNum}, 이름: <a href="/score/detail?stuNum=${s.stuNum}">${s.name}</a>, 국어: ${s.kor}점, 영어: ${s.eng}점, 
                수학: ${s.math}점, 총점: ${s.total}점, 평균: ${s.average}점
                <a class="del-btn" href="/score/delete?stuNum=${s.stuNum}">삭제</a>
            </li>
        </c:forEach>
    </ul>
    <script>
        const home = document.querySelector("go-home");
        home.addEventListener('click', e => {
            location.href = '/'; //링크 이동 기능. /에 공백이면 홈을뜻함

        });
    </script>
</body>
</html>