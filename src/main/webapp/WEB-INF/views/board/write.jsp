<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
   <meta charset="UTF-8">
   <title>Insert title here</title>


   <style>
      .fileDrop {
         width: 800px;
         height: 400px;
         border: 1px dashed gray;
         display: flex;
         justify-content: center;
         align-items: center;
         font-size: 1.5em;
      }

      .uploaded-list {
         display: flex;
      }

      .uploaded-list a {
         display: flex;
         flex-direction: column;
      }

      .uploaded-list a img {
         width: 100px;
         height: 100px;
         display: block;
      }

      .uploaded-list .thumbnail-box {
         display: flex;
      }
   </style>

   <%@ include file="../include/static-head.jsp" %>

</head>

<body>

   <%@ include file="../include/header.jsp" %>

   <h2>게시글 등록</h2>

   <form action="/board/write" method="post">
      <div class="input-box">

         <p>

            # 작성자: <input class="form-control" type="text" name="writer"><br>
            # 제목: <input class="form-control" type="text" name="title"><br>
            # 내용: <br>
            <textarea class="form-control" rows="5" cols="30" name="content"></textarea>
            <br>

         </p>

      </div>
      <button type="submit">등록</button>
   </form>

   <a href="/board/list">글 목록보기</a>

</body>

</html>