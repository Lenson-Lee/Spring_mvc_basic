package com.spring.mvc.score.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScoreTest {

    @Test
    @DisplayName("4가지 정보를 입력하면 학번, 총점, 평균이 잘 구해져야 한다.")
    void domainTest() {
        //첫번째 학생
        Score s1 = new Score("김철수", 90, 76, 88);
        //두번째 학생
        Score s2 = new Score("박영희", 60, 96, 100);

        System.out.println("===========================================");
        System.out.println("s1 = " + s1);
        System.out.println("s2 = " + s2);
    }

}