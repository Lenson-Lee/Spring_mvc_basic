package com.spring.mvc.reply.service;

import com.spring.mvc.reply.domain.Reply;
import com.spring.mvc.reply.repository.ReplyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
public class ReplyService {

    private final ReplyMapper replyMapper;

    //댓글 목록 조회
    public List<Reply> getList(int boardNo) {
        return replyMapper.getList(boardNo);
    }

    //댓글 추가
    public boolean register(Reply reply) {
        return replyMapper.save(reply);
    }

    //댓글 수정
    public boolean modify(Reply reply) {
        return replyMapper.update(reply);
    }

    //댓글 삭제
    public boolean remove(int replyNo) {
        return replyMapper.delete(replyNo);
    }

    //댓글 개별 조회
    public Reply get(int replyNo) {
        return replyMapper.read(replyNo);
    }

}
