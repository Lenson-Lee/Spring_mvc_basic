package com.spring.mvc.board.service;

import com.spring.mvc.board.domain.Board;
import com.spring.mvc.board.repository.BoardMapper;
import com.spring.mvc.common.paging.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    //DB 와 연관되어야한다.
    private final BoardMapper boardMapper;  //final 은 무조건 생성자 매개변수 추가 해야하는데 하단에 설명 추가..

    /*
    @Autowired 하나만 있으면 @RequiredArgsConstructor 로 대체 가능

    public BoardService(BoardMapper boardMapper) {
        this.boardMapper = boardMapper;
    }
    */

    //게시물 목록 가져오기
    public List<Board> getArticles(Page page) {
        List<Board> articles = boardMapper.getArticles(page);

        //3분 이내 신규글 new 마크 붙이기 -> 컨트롤러에 주기 전에 new 마크 처리를 하고 준다
        for (Board article : articles) {
            //각 게시물들의 등록시간 읽어오기(밀리초 1000분의 1초)
            long regTime = article.getRegDate().getTime();   //LocalData Index?? 를 마이바티스가 지원을 잘 안함 -> getTime 으로 밀리단위 초를 얻을 수 있다.

            //현재시간 읽어오기(밀리초)
            long now = System.currentTimeMillis();

            if (now - regTime < 60 * 60 * 6 * 1000) {
                article.setNewFlag(true);
            }   //컨트롤러가 true값을 list jsp 로 보내준다. jsp에서는 제목 옆에 new 마크를 띄우면 된다.
        }

        //3분 이내 신규글 new 마크 붙이기 -> 컨트롤러에 주기 전에 new 마크 처리를 하고 준다
        for (Board article : articles) {
            //각 게시물들의 등록시간 읽어오기(밀리초 1000분의 1초)
            long regTime = article.getRegDate().getTime();   //LocalData Index?? 를 마이바티스가 지원을 잘 안함 -> getTime 으로 밀리단위 초를 얻을 수 있다.

            //현재시간 읽어오기(밀리초)
            long now = System.currentTimeMillis();

            if (now - regTime < 60 * 60 * 6 * 1000) {
                article.setNewFlag(true);
            }   //컨트롤러가 true값을 list jsp 로 보내준다. jsp에서는 제목 옆에 new 마크를 띄우면 된다.
        }

        return articles;    //컨트롤러에게 리턴 -> 컨트롤러가 jsp에 리턴?
    }


    //총 게시물 수 조회
    public int getCount() {
        return boardMapper.getTotalCount();
    }

    //게시글 상세조회
    @Transactional  //알아서 롤백 해준다
    public Board getContent(int boardNo){   //컨트롤러한테 받아와야함
        Board content = boardMapper.getContent(boardNo);
        //조회수 증가
        boardMapper.upViewCount(boardNo);

        return content;
    }

    //게시물 등록하기
    public boolean insert(Board board) {    //서비스는 보드객체를 받을 수 없어서 컨트롤러에서 받아야함.
        return boardMapper.insertArticle(board);
        //성공하면 트루 반환. 중간 징검다리 역할
    }

    //게시글 삭제
    public boolean remove(int boardNo) {
        return boardMapper.deleteArticle(boardNo);
    }

    //게시물 수정
    public boolean modify(Board board) {
        return boardMapper.modifyArticle(board);
    }
}
