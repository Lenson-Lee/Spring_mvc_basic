<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <style>
        .pagination {
            width: fit-content;
            margin: 15px auto;
        }

        /* 부트스트랩 우선순위가 높아서 내 점수를 높여야 먹힌다. */
        ul.pagination li.p-active a {
            background: rgb(104, 139, 204);
            color: #fff;
        }

        /* 
        .pagination>li {
            justify-content: flex-end;
            margin-right: 5px;
        }

        .pagination>li>a {
            text-decoration: none;
            color: black;
        }

        .pagination>li>a:hover {
            color: rgb(50, 91, 205);
        }

        .pagination>li.active>a {
            font-weight: bold;
            color: orangered;
            font-size: 1.1em;
        } */
    </style>
    <!-- <link rel="stylesheet" href="/css/main.css"> -->

    <%@ include file="../include/static-head.jsp" %>

</head>

<body>

    <!-- <header>
        <h1>
            <a href="/board/list">My First Website</a>
        </h1>
    </header>
    <div class="menu-box">
        <a href="#">로그인</a>
        <a href="#">회원가입</a>
    </div> -->

    <!-- table 속에는 td보단 th가 더 좋다. -->

    <%@ include file="../include/header.jsp" %>

    <div class="write-container">
        <h2>게시글 목록</h2>

        <div class="list-container">
            <div class="amount">
                <a href="/board/list?amount=10">10</a>
                <a href="/board/list?amount=20">20</a>
                <a href="/board/list?amount=30">30</a>
            </div>

            <table border="1">
                <tr>
                    <th>번호</th>
                    <th>작성자</th>
                    <th>제목</th>
                    <th>작성시간</th>
                    <th>조회수</th>
                    <th>비고</th>
                </tr>


                <c:forEach var="article" items="${articles}">
                    <tr class="selectArticle">
                        <td>${article.boardNo}</td>
                        <td>${article.writer}</td>
                        <td>
                            <a href="/board/content?boardNo=${article.boardNo}">${article.title}</a>

                            <c:if test="${article.newFlag}">
                                <span class="badge rounded-pill bg-danger">new</span>
                            </c:if>

                            <c:if test="${article.viewCnt >= 2}">
                                <span class="badge badge-pill badge-info">hit</span>
                            </c:if>

                        </td>

                        <td>
                            <fmt:formatDate value="${article.regDate}" pattern="yyyy-MM-dd a hh:mm:ss" />
                        </td>
                        <td>
                            <a>${article.viewCnt}</a>


                        </td>
                        <td>
                            <a data-board-no="${article.boardNo}" class="del-btn" href="#">[삭제]</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>


            <!-- 페이지 영역 -->
            <ul class="pagination">

                <c:if test="${maker.prev}">
                    <li class="page-item"><a class="page-link" href="/board/list?pageNum=${maker.beginPage - 1}">Previous</a></li>
                </c:if>

                <c:forEach var="i" begin="${maker.beginPage}" end="${maker.endPage}" step="1">
                    <li class="page-item"><a class="page-link" href="/board/list?pageNum=${i}">${i}</a></li>
                </c:forEach>


                <c:if test="${maker.next}">
                    <li class="page-item"><a class="page-link" href="/board/list?pageNum=${maker.endPage + 1}">Next</a></li>
                </c:if>
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
        </div>
    </div>

    <script>
        //게시물 등록 처리 알림

        const msg = '${msg}'; //보드컨트롤러 자바서버에서 타고옴. 게시물이 성공했으면 success, 실패 fail 들어있음
        if (msg === 'success') {
            alert('게시물 등록 성공!');
        } else if (msg === 'fail') {
            alert('게시물 등록 실패!');
        }

        //삭제 버튼 클릭 이벤트
        //삭제 버튼에 바로 달면 첫째줄만 된다. 수많은 삭제여서 버블링, 부모 태그에 써야함
        const delBtn = document.querySelector('del-btn');
        const table = document.querySelector('table');

        table.addEventListener('click', e => {

            if (!e.target.matches('table a.del-btn')) return;

            e.preventDefault(); //a 태그의 링크가 클릭이벤트보다 우선시되어서 막아야 컨펌기능이 실행된다.
            //console.log('삭제버튼 클릭됨!');

            //article.boardNo 변수는 forEach 함수에서 쓰는 태그라 함수가 끝나면서 사라져 사용이 불가능. => dataset 사용
            const boardNo = e.target.dataset.boardNo;

            if (confirm('정말로 삭제하겠습니까?')) {
                location.href = '/board/delete?boardNo=' + boardNo;
            }
        });
    </script>


</body>

</html>