package com.spring.mvc.reply.repository;

import com.spring.mvc.reply.domain.Reply;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReplyMapper {

    //댓글 입력 기능
    boolean save(Reply reply);

    //댓글 수정
    boolean update(Reply reply);

    //댓글 삭제(특정댓글의 pk를 주어 삭제)
    boolean delete(int replyNo);

    //댓글 목록 조회(셀렉트 프롬 tbl_reply where boardNo = nn
    List<Reply> getList(int boardNo);

    Reply read(int replyNo);


}
