package com.spring.mvc.board.domain;

import lombok.*;

import java.util.Date;

@Setter @Getter @ToString @AllArgsConstructor   //필수 4대장
@NoArgsConstructor  //기본생성자


public class Board {

    private int boardNo; //글번호
    private String writer; //작성자
    private String title; //글제목
    private String content; //글내용
    private int viewCnt; //조회수
    private Date regDate; //글작성시간

    private boolean newFlag; //신규 게시물 여부

    // ## 주의할점: content필드의 setter가 setcontent,getcontent니까
    // 불린값인 newFlag 의경우는 setNewFlag, getter는 boolean값의 경우는 getter가 아니라 isNewFlag라고 한다.
}
