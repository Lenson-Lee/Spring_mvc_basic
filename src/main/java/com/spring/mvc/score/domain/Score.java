package com.spring.mvc.score.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter @Getter @ToString
//@AllArgsConstructor 는 외부에서 매개변수 7개를 전부 쓴다는 뜻인데 현실에서는 클라이언트에서 4개만 오기 때문에 쓸 수 없고
//현실에서는 클라이언트에서 4개만 오기 때문에 쓸 수 없고 수동으로 생성자를 만들어 쓰기.
public class Score {

    private int stuNum; //학번
    private String name; //이름
    private int kor; //국어점수
    private int eng; //영어점수
    private int math; //수학점수
    private int total; //총점
    private double average; //평균

    //순차적 학번 부여 정적필드 (static 모든 객체가 공유하는 변수여서 첫번째 seq = 1, 두번쨰 seq = 첫번째 + 1 , 세번째 seq = 두번째 seq + 1
    private static int seq;

    public Score() {
        this.stuNum = ++seq;
        this.total = this.kor + this.eng + this.math;
        this.average = Math.round((this.total / 3.0) * 100) / 100.0;    //Math.round(반올림) 은 long 을 리턴해서 long / int = 정수 이기 때문에 long / double 을 해야 실수가 나온다.
    }

    public Score(String name, int kor, int eng, int math) { //평균, 총점은 받아올 수 없기 때문..
        this(); //나의 생성자 중에 ()와 형태가 같은 다른 애 부르기 -> 여기서는 Score()임.

        this.name = name;
        this.kor = kor;
        this.eng = eng;
        this.math = math;
    }
}

