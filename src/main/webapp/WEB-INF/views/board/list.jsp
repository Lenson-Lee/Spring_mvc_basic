<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <style>
        .pagination {
            width: 60%;
            margin-top: 10px;
            list-style: none;
            display: flex;
        }

        .pagination>li {
            justify-content: flex-end;
            margin-right: 5px;
        }

        .pagination>li>a {
            text-decoration: none;
            color: black;
        }

        .pagination>li>a:hover {
            color: yellowgreen;
        }

        .pagination>li.active>a {
            font-weight: bold;
            color: orangered;
            font-size: 1.1em;
        }

        .amount {
            width: 30%;
            display: flex;
            justify-content: flex-end;
            margin-bottom: 10px;
        }

        .amount a {
            display: block;
            color: #fff;
            background: #f00;
            width: 50px;
            height: 20px;
            border-radius: 5px;
            margin-right: 5px;
            text-align: center;
            font-weight: 700;
            text-decoration: none;

        }
    </style>
    <link rel="stylesheet" href="/css/main.css">


</head>

<body>




    <h1>게시글 목록</h1>

    <div class="amount">
        <a href="#">10</a>
        <a href="#">20</a>
        <a href="#">30</a>
    </div>

    <table border="1">
        <tr>
            <td>번호</td>
            <td>작성자</td>
            <td>제목</td>
            <td>조회수</td>
            <td>비고</td>
        </tr>


        <c:forEach var="article" items="${articles}">
            <tr>
                <td>${article.boardNo}</td>
                <td>${article.writer}</td>
                <td>
                    <a href="/board/content">${article.title}</a>
                </td>
                <td>${article.viewCnt}</td>
                <td>
                    <a href="/board/delete?boardNo=${article.boardNo}">[삭제]</a>
                </td>
            </tr>
        </c:forEach>

    </table>

    <!-- 페이지 영역 -->
    <ul class="pagination">


    </ul>


    <!-- 검색창 영역 -->
    <div class="search">
        <form action="/board/list" id="search-form">

            <select name="type">
                <option value="title">제목</option>
                <option value="content">내용</option>
                <option value="writer">작성자</option>
                <option value="titleContent">제목+내용
                </option>
            </select>

            <input type="text" name="keyword" placeholder="검색어를 입력!" value="">

            <button type="submit">검색</button>

        </form>
    </div>

    <p>
        <a href="/board/write">게시글 작성하기</a>
    </p>




</body>

</html>