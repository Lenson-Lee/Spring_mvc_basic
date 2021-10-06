package com.spring.mvc.score.controller;

import com.spring.mvc.score.domain.Score;
import com.spring.mvc.score.repository.ScoreMapper;
import com.spring.mvc.score.repository.ScoreRepository;
import com.spring.mvc.score.service.ScoreService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@Log4j2 //로그 출력을 도와주는 기능 sout 대신 log.info 사용
//@RequiredArgsConstructor    //final 필드를 초기화하는 생성자를 자동으로 생성해준다 (밑에 주석한 @Autowired 문을 생략할 수 있게 된다.)
public class ScoreController {

    private final ScoreRepository scoreRepository;    //객체가 없으니 생성자를 주입해야한다. alt insert 생성자. final: 무조건 레파지토리가 있어야 한다고 인식하게 되어서 생략 가능(밑의 @Auto)
                                                    //스프링이 실행시점에 자동으로 만들어준다. 객체에 대한 정보가 미리 기존에 등록이 되어있어야 사용이 가능하다. @Component(MemoryScoreRepository에 넣었음)
    private final ScoreService scoreService;
//    @Autowired  //스프링에게 스코어컨트롤러에서 스코어레파지토리를 직접 만들지 않고 자동으로 넣어(위임)해달라고 요청, final시에 자동으로 인식되어 생략 가능. @RequiredArgsConstructor
//    public ScoreController(ScoreRepository scoreRepository) {
//        this.scoreRepository = scoreRepository;
//    }
    private final ScoreMapper scoreMapper;  //앞으로 레파지토리 안쓰고 마이바티스 쓰겠다는 뜻. 생성자매개변수 추가로 오류 해결

    @Autowired
    public ScoreController(@Qualifier("jr") ScoreRepository scoreRepository, ScoreService scoreService, ScoreMapper scoreMapper) {
        //this.scoreRepository = scoreRepository;
        this.scoreRepository = scoreRepository;
        this.scoreService = scoreService;
        this.scoreMapper = scoreMapper;
    }

    //점수 프로그램 화면 요청
    @GetMapping("/score/list") //<a href = "">태그는 전부 get 이다.
    public String scoreList(Model model) {
        List<Score> scores = scoreMapper.findAll();
        model.addAttribute("scoreList", scores);
        return "score/score-list";
    }

    //점수 정보 저장 요청
    @PostMapping("/score/register")
    public String register(Score score) {//요청파라미터 세 종류 기억하기!!
        //System.out.println("점수 등록 요청! -");  //log ing 로깅. 로그에는 원러ㅐ 앞에 시간이 나와야한다. => 롬복의 log.info 사용
        log.info("점수 등록 요청! -" + score);
        scoreService.register(score);   //컨트롤러(홀에서 매니저에게 주문) - 서비스(매니저가 주방에 요청) - 레지스터(주방): 주방에서 손님 주문까지 받아서 케어하는건 넘 힘드니까.!
        //score.calcTotal();  //스코어가 불안정하니 여기서 미리 평균을 만들고 세이브를 시키자.   (Score.java 평균 함수 참조)

        //저장로직 어쩌고저쩌고 주루룩 적으면 객체지향이 아니니까~ 다른 애한테 위임해야지~~! 할 일은 분산시켜야지~~ => repository에서 작동하고 여기선 명령만 한다.
        //scoreRepository.save(score);    //여기서부턴 레파지토리의 책임~ => Service에서 레파지토리에게 명령

        //레지스터 등록 요청이 끝나면 목록 리스트 요청이 가도록 재요청기능 : redirect 요청 리다이렉트
        //응용: 불법사이트 들어가면 못 나가도록 리다이렉트 갈겨놔서 벗어날 수 없거나 가짜 사이트를 만들어서 사기 칠 때도 정보를 입력하게 한 후 리다이렉트로 해커에게 전송되도록.

        return "redirect:/score/list";  //redirect:https://www.naver.com 하면 네이버로 이동하는 거다.   //주방에서 요리가 끝나면 자동으로 서빙되도록
    }

    //점수정보 삭제 요청 처리 a 태그는 GET get이면 a 태그에 href 작성시 ?을 통해 #=@, #는 @다 를 입력.
    @GetMapping("/score/delete")
    public String delete(int stuNum) {
        log.info("점수 삭제 요청! - ");
        scoreMapper.remove(stuNum);
        return "redirect:/score/list";
    }

    //점수 상세보기 요청
    @GetMapping("score/detail")
    String detail(@RequestParam("stuNum") int sn, Model model) {
        log.info("/score/detail GET: " + sn);
        Score score = scoreMapper.findOne(sn);
        model.addAttribute("score", score);

        return "score/detail";
    }


}
