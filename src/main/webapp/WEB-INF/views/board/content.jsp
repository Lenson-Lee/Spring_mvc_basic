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
</head>

<body>

    <div class="container">
        <div class="row">
            <div class="offset-md-1 col-md-10">
                <h1>${article.boardNo}번 게시물 내용</h1>

                <p>
                    # 글번호: ${article.boardNo}<br>
                    # 작성자: ${article.writer}<br>
                    # 제목: ${article.title}<br>
                    # 내용: <br>
                    <textarea rows="5" cols="30" disabled>${article.content}</textarea>
                </p>

                <a href="/board/list">글 목록보기</a>&nbsp;

                <a href="#">글 수정하기</a>


            </div>
        </div>

        <!-- 첨부파일 영역 -->
        <div class="row">
            <div class="attach-file-list"></div>
        </div>

        <!-- 댓글 영역 -->

        <div id="replies" class="row">
            <div class="offset-md-1 col-md-10">
                <!-- 댓글 쓰기 영역 -->
                <div class="card">
                    <div class="card-body">
                        <div class="row">
                            <div class="col-md-9">
                                <div class="form-group">
                                    <label for="newReplyText" hidden>댓글 내용</label>
                                    <textarea rows="3" id="newReplyText" name="replyText" class="form-control"
                                        placeholder="댓글을 입력해주세요."></textarea>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label for="newReplyWriter" hidden>댓글 작성자</label>
                                    <input id="newReplyWriter" name="replyWriter" type="text" class="form-control"
                                        placeholder="작성자 이름" style="margin-bottom: 6px;">
                                    <button id="replyAddBtn" type="button" class="btn btn-dark form-control">등록</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div> <!-- end reply write -->

                <!--댓글 내용 영역-->
                <div class="card">
                    <!-- 댓글 내용 헤더 -->
                    <div class="card-header text-white m-0" style="background: #343A40;">
                        <div class="float-left">댓글 (<span id="replyCnt">0</span>)</div>
                    </div>

                    <!-- 댓글 내용 바디 -->
                    <div id="replyCollapse" class="card">
                        <div id="replyData">
                            <!-- 
                        < JS로 댓글 정보 DIV삽입 > 
                     -->
                        </div>

                        <!-- 댓글 페이징 영역 -->
                        <ul class="pagination justify-content-center">
                            <!-- 
                        < JS로 댓글 페이징 DIV삽입 > 
                     -->
                        </ul>
                    </div>
                </div> <!-- end reply content -->
            </div>
        </div> <!-- end replies row -->
    </div> <!-- end content container -->

</body>

</html>