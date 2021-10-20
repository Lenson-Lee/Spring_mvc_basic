package com.spring.mvc.reply.repository;

import com.spring.mvc.common.paging.Page;
import com.spring.mvc.reply.domain.Reply;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReplyMapper {

    //댓글 입력 기능
    boolean save(Reply reply);

    //댓글 수정
    boolean update(Reply reply);

    //댓글 삭제(특정댓글의 pk를 주어 삭제)
    boolean delete(int replyNo);

    /**
     * 마이바티스 매퍼는 기본적으로 1개의 매개값만 받을 수 있음
     * 그러나 @Parpm을 통해 여러개를 처리할 수 있음.
     * @param boardNo
     * @Param page
     * @return
     */

    //댓글 목록 조회(셀렉트 프롬 tbl_reply where boardNo = nn
    //여기서 변수명과 동일할 필요는 없는데 @Param에 쓴게 xml에서 #{}가 된다.
    List<Reply> getList(
            @Param("boardNo") int boardNo,
            @Param("page") Page page);

    //총 댓글 수 조회
    int getCount(int boardNo);

    //댓글개별조회
    Reply read(int replyNo);


}
