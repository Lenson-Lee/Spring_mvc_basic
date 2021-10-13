package com.spring.mvc.common.paging;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter @Getter @ToString
public class Page {

    private int pageNum; //페이지 번호
    private int amount; //한 페이지당 게시물 수
    private String type; //검색조건
    private String keyword = ""; //검색어


    //기본생성자 알트인서트 + 생성자 => 선택안함
    public Page() {
//        this.pageNum = 1;   //내가 쓴거다! 클라이언트에서 페이지를 받을때 컨트롤러에서 page()가 어마운트를 보낸다?
//        this.amount = 10;   //세터 게터가 필요. 세터 써야한다.
        this(1,10);
    }

    //주소창에 입력해서 고의로 서버 다운시킬 수 있는 가능성 차단
    public void setAmount(int amount) {
        if ( amount <= 0 || amount > 100) {
            this.amount = 10;
            return;
        }
        this.amount = amount;
    }

    public Page(int pageNum, int amount) {
        this.pageNum = pageNum;
        this.amount = amount;
    }

    //SQL 인젝션 공격 혹은 페이지 주소값에 -99같은 음수를 넣는 경우 대비
    public void setPageNum(int pageNum) {
        if (pageNum <= 0) {
            this.pageNum = 1;
            return;
        }
        this.pageNum = pageNum;

    }

}
