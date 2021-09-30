package com.spring.mvc.score.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ScoreController {

    //점수 프로그램 화면 요청
    @GetMapping("/score/list") //<a href = "">태그는 전부 get 이다.
    public String scoreList() {
        return "score/score-list";
    }

}
