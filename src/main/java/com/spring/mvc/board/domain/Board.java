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

}
