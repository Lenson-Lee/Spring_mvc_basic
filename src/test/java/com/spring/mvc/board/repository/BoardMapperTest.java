package com.spring.mvc.board.repository;

import com.spring.mvc.board.domain.Board;
import com.spring.mvc.common.paging.Page;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//테스트시에 스프링 컨테이너를 사용한다.
@SpringBootTest
class BoardMapperTest {

    @Autowired  //주입?
    BoardMapper boardMapper;

    @Test
    @DisplayName("300개의 게시물을 등록해야 한다.")
    void bulkInsert() {
        for (int i = 1; i <= 300; i++) {
            Board board = new Board();
            board.setTitle("테스트 제목 " + i);
            board.setContent("테스트내용이다다다다 " + i);
            board.setWriter("User" + i);

            boardMapper.insertArticle(board);
        }
        System.out.println("게시물 등록 성공!");   //sql 들가서 SELECT * FROM board 해보면 300개 테이블 떠야함
    }

    //실제 실무에서 테스트 하는 방법
    @Test
    @DisplayName("전체 게시물을 글번호 내림차순으로 조회해야 한다.")
    void selectAll() {
        List<Board> articles = boardMapper.getArticles();

        //못믿겠으니 직접 눈으로 세어 보기
//        System.out.println("=======================================================");
//        for (Board article : articles) {
//            System.out.println(article);
//        }

        //우리는 지금 테스트 라이브러리로 junit 쓰는중 (위 import 보면 알 수 있다.) : xUnit 이다. Assertion 단언하다.
        assertTrue(articles.size() == 300); //articles 의 사이즈가 300일거라 단언하다.  True면 테스트 시에 초록색 통과 표시ㅏ 뜬다.
    }

    //단일조회
    @Test
    @DisplayName("글번호를 통해 1개의 게시물을 상세 조회해야 한다.")
    void selectOne() {
        Board content = boardMapper.getContent(30);

        //(기대값, 실제값)
        assertEquals("User1", content.getWriter());
    }

    //삭제 테스트
    @Test
    @DisplayName("글번호를 통해 게시물을 1개 삭제해야 한다.")
    /*
        테스트 당시에만 지우고 테스트 이후에 롤 백 시켜야 한다.
        롤백하지 않으면 처음 테스트에서 실제 데이터가 지워지는 거라 두번쨰 테스트부터는 지울게 없어서? 오류가 뜬다.
        Transactional, Rollback 없을 때에 sout 해보면 300이 없는거 확인 가능. 이제 299개니까 100으로 해보니 참이 나옴.
     */
    @Transactional  @Rollback(value = true)
    void delete() {
        boolean result = boardMapper.deleteArticle(100);
        Board content = boardMapper.getContent(100);

        System.out.println("content = " + content);
        System.out.println("result = " + result);
        assertTrue(result);
        assertNull(content);
    }

    @Test
    @DisplayName("글번호를 통해 게시물의 제목과 내용을 수정해야 한다.")
    void modify() {
        Board board = new Board();
        board.setBoardNo(50);
        board.setTitle("수정수정수정");
        board.setContent("메롱메롱메롱");

        boolean result = boardMapper.modifyArticle(board);
        Board content = boardMapper.getContent(50);

        assertTrue(result);
        assertEquals("수정수정수정", content.getTitle());
    }

    @Test
    @DisplayName("페이징을 적용하여 게시물이 조회되어야 한다.")
    void pageTest1() {
        int page = 10;
        int amount = 20;
        Page p = new Page();
        p.setPageNum(page);
        p.setAmount(amount);

        List<Board> articles = boardMapper.getArticles(p);

        System.out.println("\n =================================");
        for(Board article : articles) {
            System.out.println(article);
        }
    }


}