package com.spring.mvc.api;

import com.spring.mvc.board.domain.Board;
import com.spring.mvc.board.service.BoardService;
import com.spring.mvc.common.paging.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api/v1")
public class ApiControllerV1 {

    //이건 예제고 v2 보고 공부하기!

    @GetMapping("/hello")
    @ResponseBody   //클라이언트에게 순수데이터 리턴(JSON)    http://localhost:8182/api/v1/hello
    public String hello() {
        return "안녕";    //안녕.jsp 가 열려야 하는데 페이지를 찾을 수 없어 404 뜬다. 컨트롤러여서 안녕.jsp를 컴파일 한다(= 서버측에서 SSR이 작동한다는 뜻.)
    }

    //JSON은 스트링과 리스트배열이 똑같이나온다.
    @GetMapping("/hobby")
    @ResponseBody
    public String[] hobby() {
        return new String[] {"음악감상", "축구", "꽃꽂이"};
    };
    @GetMapping("/major")
    @ResponseBody
    public List<String> major() {
        return Arrays.asList("정보보안", "컴퓨터공학", "수학과", "성악과");
    }

    //자바 객체 Board 가 클라이언트에 전송되면 { @@ : @@} 형식으로 나온다.
    @GetMapping("/article")
    @ResponseBody
    public Board board() {
        Board board = new Board();
        board.setBoardNo(600);
        board.setTitle("JSON제목");
        board.setContent("하이~테스트중!");
        board.setWriter("김제이슨");
        return board;
    }

    //Map 형태를 날리면 키:값 객체형태로 나타난다.
    @GetMapping("/food")
    @ResponseBody
    public Map<String, String>food() {
        Map<String, String> foods = new HashMap<>();
        foods.put("한식", "육개장");
        foods.put("양식", "파스타");
        foods.put("중식", "탕수육");
        return foods;
    }

    @Autowired //의존성 주입
    private BoardService boardService;

    //[] 속에 { : } 가 들어간다.
    @GetMapping("/b-list")
    @ResponseBody
    public List<Board> list() {
        return boardService.getArticles(new Page(1,20));
    }
}
