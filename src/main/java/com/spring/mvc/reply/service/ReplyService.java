package com.spring.mvc.reply.service;

import com.spring.mvc.common.paging.Page;
import com.spring.mvc.common.paging.PageMaker;
import com.spring.mvc.reply.domain.Reply;
import com.spring.mvc.reply.repository.ReplyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ReplyService {

    private final ReplyMapper replyMapper;

    //댓글 목록 조회
    public Map<String, Object> getList(int boardNo, Page page) {
        //밑에서부터 replyMap.put까지 누가봐도 잡일인데 컨트롤러보단 서비스에서 하는게 좋지 않을까?
        PageMaker maker
                = new PageMaker(page, getCount(boardNo));
        //비동기는 무조건 하나만 가져갈 수 있어서 모델을 못 가져온다 => 페이지넘버랑 리플라이리스트?두 개를 합쳐야 한다.
        //두개로 클래스 만들기 좀 그렇다면 맵을 만든다. object는 공통부모
        Map<String, Object> replyMap = new HashMap<>();
        replyMap.put("replyList", replyMapper.getList(boardNo,page));
        replyMap.put("maker", maker);

        return replyMap;
    }

    //댓글 총 개수 조회
    public int getCount(int boardNo) {
        return replyMapper.getCount(boardNo);
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
