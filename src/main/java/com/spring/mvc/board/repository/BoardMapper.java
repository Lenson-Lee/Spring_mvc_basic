package com.spring.mvc.board.repository;

import com.spring.mvc.board.domain.Board;
import com.spring.mvc.common.paging.Page;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
//테스트 주도 기법: 하나씩 만들때마다 테스트 하는게 좋다. ctrl shift t : 테스트생성
public interface BoardMapper {

    //1. 게시물 목록 조회(페이지정보, 갯수정보 필요)
    // 매퍼 인터페이스는 웬만해서 하나를 넣어줘야한다. 두개는 골치아파진다.
    // => 하나를 포장해서 두개를 보내면 되겠다! => 도메인에서
    List<Board> getArticles();

    //1-2. 페이징 적용(기존코드에 문제가 생기지 않도록 ( 아직 서비스랑 컨트롤을 수정하지 않아서 복사해서 씀)
    List<Board> getArticles(Page page);

    //총 겜시물 수 조회
    int getTotalCount();

    //2. 게시물 상세 조회
    Board getContent(int boardNo);

    //3. 게시물 등록(게시물 등록 성공하면 트루)
    boolean insertArticle(Board board);

    //4. 게시물 삭제
    boolean deleteArticle(int boardNo);

    //5. 게시물 수정
    boolean modifyArticle(Board board);


    //6. 조회수 상승
    void upViewCount(int boardNo);
}
