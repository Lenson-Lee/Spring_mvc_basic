package com.spring.mvc.reply.repository;

import com.spring.mvc.reply.domain.Reply;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReplyMapperTest {

    @Autowired
    ReplyMapper replyMapper;

    @Test
    @DisplayName("특정 글번호 게시글에 댓글 20개를 삽입해야 한다.")
    void insertTest(){
        for (int i = 1; i < 21 ; i++) {
            Reply reply = new Reply();
            reply.setBoardNo(312);  //내 게시글목록중 최신글에 넣어봤음
            reply.setReplyText("테스트 댓글 " + i);
            reply.setReplyWriter("짹짹이" + i);

            replyMapper.save(reply);

        }
    }

    @Test
    @DisplayName("특정 게시물의 댓글 목록을 조회할 수 있어야 한다.")
    void getListTest() {
        List<Reply> list = replyMapper.getList(312);

        assertTrue(list.size() == 20);
    }

    @Test
    @DisplayName("특정 게시물을 수정할 수 있어야 한다.")
    void updateTest() {
//        Reply reply = replyMapper.read(15);
//        reply.setReplyText("수정된 댓글");
//
//        replyMapper.update(reply);
//
//        assertEquals(reply.getReplyText(), replyMapper.read(15).getReplyText());
    }

    @Test
    @DisplayName("특정 게시물을 삭제할 수 있어야 한다.")
    @Transactional @Rollback    //삭제 후 테스트가 끝나면 롤백이 된다.
//    @Transactional @Rollback
    void deleteTest() {
        replyMapper.delete(312);
        replyMapper.delete(311);

        assertTrue(replyMapper.getList(311).size() == 16);
    }

}