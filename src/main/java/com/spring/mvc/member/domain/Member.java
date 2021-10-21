package com.spring.mvc.member.domain;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    private String account; //계정명
    private String password; //비밀번호
    private String name; //사용자이름
    private String email; //이메일
    private Auth auth; //권한 String의 경우는 잘못 써도 에러가 안나서 몇 개 정해져있는 문자열의 경우(혹은 골드,vip같은 등급) 마치 클래스처럼 사용한다. => 열거형 어스생성
    private Date regDate; //가입일자

}