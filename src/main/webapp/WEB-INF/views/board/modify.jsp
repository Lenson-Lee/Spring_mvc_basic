<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>

    <style>
        .attach-file-list {
            display: flex;
            width: 80%;
            border: 1px dashed gray;
            margin: 20px auto;
            padding: 20px 10px;
        }

        .attach-file-list a {
            display: flex;
            flex-direction: column;
        }

        .attach-file-list a img {
            width: 100px;
            height: 100px;
            display: block;
        }

        .attach-file-list .thumbnail-box {
            display: flex;
        }
    </style>

    <%@ include file="../include/static-head.jsp" %>
</head>

<body>

    <%@ include file="../include/header.jsp" %>

    <div class="container">
        <div class="row">
            <div class="offset-md-1 col-md-10">
                <h1>${article.boardNo}번 게시물 내용</h1>

                <form action="/board/modify" method="POST">
                    <input type="hidden" name="boardNo" value="${article.boardNo}">
                    # 글번호: ${article.boardNo}<br>
                    # 작성자: ${article.writer}<br>
                    # 제목: <input type="text" name="title" value="${article.title}"><br>
                    # 내용: <br>
                    <textarea name="content" rows="5" cols="30">${article.content}</textarea>

                    <br>
                    <button class="btn btn-primary" type="submit">수정</button>

                    <a class="btn btn-warning" href="/board/list">목록</a>
                </form>



            </div>
        </div>
        
    </div> <!-- end content container -->

</body>

</html>