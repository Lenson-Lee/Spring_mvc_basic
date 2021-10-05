package com.spring.mvc.score.repository;

import com.spring.mvc.score.domain.Score;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//메모리에 성적 정보들을 모아서 저장해야 한다.
@Repository("mr") //스프링에 저장소 빈으로 등록 + @Component 혹은 Repository(좀 더 저장소의 의미가 명확하다)
@Log4j2
public class MemoryScoreRepository implements ScoreRepository { //빨간줄 뜨면 메서드 구현 해야한다.

    private static Map<Integer, Score> scoreMap = new HashMap<>(); //static: 정보가 저장소 여러군데가 아닌 한 곳에 모여야 하기 때문에 사용

    static {
        scoreMap.put(1, new Score("김철수", 99, 78, 67));
        scoreMap.put(2, new Score("박영희", 85 , 89, 72));
        scoreMap.put(3, new Score("팽숙영", 55 , 62, 83));
    }
    @Override
    public void save(Score score) {
        score.calcTotal();
        scoreMap.put(score.getStuNum(), score);
        log.info(scoreMap);
    }

    @Override
    public List<Score> findAll() {
        //우리가 받은 점수 객체는 Map 이니까 Score 깩체를 전부 뽑아내서 List 에 담아 리턴할 것이다.
        List<Score> scores = new ArrayList<>(); //WOW 복습 갈겨야함 진짜루..
        for (int stuNum : scoreMap.keySet()) {
            Score score = scoreMap.get(stuNum);
            scores.add(score);
        }

        return scores;
    }

    @Override
    public Score findOne(int stuNum) {
        return scoreMap.get(stuNum);
    }

    @Override
    public void remove(int stuNum) {
        scoreMap.remove(stuNum);
    }
}
