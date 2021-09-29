<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title></title>

<style>
    label {
        display: block;
    }
</style>
</head>
<body>
    <h1>BasicController를 이용한 요청 처리</h1>
    <p>컨트롤러 테스트중입니다.</p>

    <!-- 폼 액션: 액션주소로 받음 -->
    <form action="/basic/join3" method= "POST">    
        <label>
            # 아이디: <input type="text" name="userId">
        </label>
        <label>
            # 비밀번호: <input type="password" name="userPw">
        </label>
        <label>
            # 이름: <input type="text" name="userName">
        </label>
        <label>
            # 나이: <input type="text" name="userAge">
        </label>
        <label>
            # 취미(name이 동일해야 같은 취급, value에 넣는 값을 반환):
            <input type="checkbox" name="hobbys" value="축구"> 헬스
            <input type="checkbox" name="hobbys" value="독서"> 독서
            <input type="checkbox" name="hobbys" value="잠자기"> 잠자기
            <input type="checkbox" name="hobbys" value="뜨개질"> 뜨개질
        </label>

        <button type="submit">회원가입</button>
    </form>
</body>
</html>