package com.spring.mvc.board.repository;

import com.spring.mvc.board.domain.Board;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

//테스트시에 스프링 컨테이너를 사용한다.
@SpringBootTest
class BoardMapperTest {

    @Autowired  //주입?
    BoardMapper boardMapper;

    @Test
    @DisplayName("300개의 게시물을 등록해야 한다.")
    void bulkInsert(){
        for (int i = 1; i <= 300; i++) {
            Board board = new Board();
            board.setTitle("테스트 제목 " + i);
            board.setContent("테스트내용이다다다다 " + i);
            board.setWriter("User" + i);

            boardMapper.insertArticle(board);
        }
        System.out.println("게시물 등록 성공!");   //sql 들가서 SELECT * FROM board 해보면 300개 테이블 떠야함
    }

}