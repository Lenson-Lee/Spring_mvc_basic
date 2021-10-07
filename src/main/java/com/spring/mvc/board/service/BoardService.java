package com.spring.mvc.board.service;

import com.spring.mvc.board.domain.Board;
import com.spring.mvc.board.repository.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<Board> getArticles() {
        List<Board> articles = boardMapper.getArticles();

        return articles;    //컨트롤러에게 리턴 -> 컨트롤러가 jsp에 리턴?
    }


    //게시글 상세조회
    public Board getContent(int boardNo){   //컨트롤러한테 받아와야함
        Board content = boardMapper.getContent(boardNo);
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
}
