package com.spring.mvc.v2;

import lombok.*;

import java.util.List;

@Setter @Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {

    //반드시 필드명이 name과 같아야 한다. 커맨드 객체라고 부름.
    //세터,게터, 네가지 꼭 넣어야 한다. 규칙임.
    private String userId;
    private String userPw;
    private String userName;
    private int userAge;
    private List<String> hobbys;
}
