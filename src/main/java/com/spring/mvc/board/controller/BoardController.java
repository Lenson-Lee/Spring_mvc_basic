package com.spring.mvc.board.controller;

import com.spring.mvc.board.domain.Board;
import com.spring.mvc.board.service.BoardService;
import com.spring.mvc.common.paging.Page;
import com.spring.mvc.common.paging.PageMaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/board")   //자동으로 매핑할 때 앞에 /board가 붙음
public class BoardController {

    private final BoardService boardService;

    /*
    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }
     */

    //게시물 목록 요청
    @GetMapping("/list")
    public String list(Page page, Model model) {
        log.info("/board/list GET 요청 발생!");
        List<Board> articles = boardService.getArticles(page);
        model.addAttribute("articles", articles);
        model.addAttribute("maker", new PageMaker(page, boardService.getCount()));//총 개시물수는 DB갔다 와야 해서 서비스에서 받는다. => 서비스에서 코드 생성

        return "board/list";//board  폴더의 list.jsp 에 보내야함.
    }

    //상세조회
    @GetMapping("/content")
    public String content(Model model, int boardNo) {
        log.info("/board/content GET 요청 발생! 글번호: " + boardNo);
        Board getContent = boardService.getContent(boardNo);
        //jsp에게 주기 위해 모델이 필요하다.
        model.addAttribute("article", getContent);  //content.jsp 보면 다 article이다.
        return "board/content";
    }

    //게시물 작성폼에 연결 (화면만 여는거라 DB 필요없음)
    @GetMapping("/write")
    public String intoWrite(Board board){
        log.info("board/write Post 요청 발생!");
        return "board/write";
    }
    //게시글 작성
    @PostMapping("/write")
    public String write(Board board, RedirectAttributes ra) {  //작성자, 내용 등 세가지가 들어가서 도메인으로 받는다.
        log.info("/board/write POST 요청! " + board);
        if (boardService.insert(board)) {
            //불린값이라 if 로 감쌌다. 종착지가 list.jsp @Post 는 리다이렉트 할 때는 model 대신 RedirectAttributes 사용
            ra.addFlashAttribute("msg", "success");
        } else {
            ra.addAttribute("msg", "fail");
        }
        return "redirect:/board/list";
    }

    //삭제
    @GetMapping("/delete")
    public String delete(int boardNo) {
        log.info("/board/delete Get " + boardNo);
        boardService.remove(boardNo);
        return "redirect:/board/list";
    }

    //글 수정 화면 요청하기
    @GetMapping("/modify")
    public String modify(int boardNo, Model model) {
        log.info("/board/modify GET! - " + boardNo);

        Board content = boardService.getContent(boardNo);   //재사용 하면 되지롱~~
        model.addAttribute("article", content);

        return "/board/modify";
    }

    //글 수정 완료처리 요청
    @PostMapping("/modify")
    public String modify(Board board) {
        log.info("/board/modify POST! - " + board);

        //로그를 보면 update 에서 글 제목, 내용은 잘 넘어오는데 보드넘버가 안넘어온다..
        //Post 는 무조건 input 으로 넘어오는데.. 사용자 몰래 넘겨야 한다 -> input type = hidden

        boardService.modify(board);
        return "redirect:/board/content?boardNo=" + board.getBoardNo();
        //수정완료 알림은 리다이랙트 애트리뷰트에 담아서 서버에 띄운다..?
    }
}

