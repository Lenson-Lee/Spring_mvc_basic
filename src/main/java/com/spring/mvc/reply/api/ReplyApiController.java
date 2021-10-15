package com.spring.mvc.reply.api;

import com.spring.mvc.reply.domain.Reply;
import com.spring.mvc.reply.service.ReplyService;
import lombok.RequiredArgsConstructor;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reply")
@Log4j2
@RequiredArgsConstructor
public class ReplyApiController {

        private final ReplyService replyService;

        //댓글 목록 조회 요청 처리//리턴은 무조건 리스포스 엔티티라고 생각하기
    //RestApi 설계
    @GetMapping("/{boardNo}")
    public ResponseEntity<List<Reply>> list(
            //경로를통해 변수를 이용하겠다 @PathVariable
            @PathVariable int boardNo) {
        log.info("/api/v1/reply/" + boardNo + "GET!");
        List<Reply> replyList = replyService.getList(boardNo);

        return new ResponseEntity<>(replyList, HttpStatus.OK);

    }

}
