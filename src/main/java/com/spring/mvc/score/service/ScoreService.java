package com.spring.mvc.score.service;

import com.spring.mvc.score.domain.Score;
import com.spring.mvc.score.repository.ScoreMapper;
import com.spring.mvc.score.repository.ScoreRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

//컨트롤러와 저장소 중간에서 데이터 중간처리를 담당하는 클래스
@Log4j2
@Service //@Component 도 되지만 서비스가 따로 있다.

//@RequiredArgsConstructor  레파지토리가 이제 둘 이 되어서 내가 직접 골라서 알려줘야함
public class ScoreService {

    //컨트롤러대신 레파지토리랑 대화
    private final ScoreRepository scoreRepository;  //@RequiredArgsConstructor 스프링에게 생성자 달라고..?
    private final ScoreMapper scoreMapper;
    @Autowired
    public ScoreService(@Qualifier("jr") ScoreRepository scoreRepository, ScoreMapper scoreMapper) {
        this.scoreRepository = scoreRepository;
        this.scoreMapper = scoreMapper;
    }

    //점수 정보 저장전에 해야할 처리
    public void register(Score score) {
        score.calcTotal();
        scoreMapper.save(score); //컨트롤러는 요청처리에 집중할 수 있다. 중간처리를 집중하지 않아도 되어서 좋다. 컨트롤러 - 서비스 -레파지토리 순으로 명령 내린다.

    }

}
