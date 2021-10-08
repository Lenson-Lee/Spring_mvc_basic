package com.spring.mvc.common.paging;

import com.spring.mvc.board.repository.BoardMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PageMakerTest {

    @Autowired
    BoardMapper boardMapper;

    @Test
    @DisplayName("현재 페이지 번호와 총 게시물 수에 따라 페이지 정보를 정확하게 생성해야 한다.")
    void pageMakeTest(){
        Page page = new Page(27, 10);
        int total = boardMapper.getTotalCount();

        PageMaker maker = new PageMaker(page, total);
        System.out.println("===================================");
        System.out.println(maker);

//        assertEquals(28, maker.getEndPage());

    }

}