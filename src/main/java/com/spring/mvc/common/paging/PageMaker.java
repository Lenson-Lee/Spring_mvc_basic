package com.spring.mvc.common.paging;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 서버 측에서 클라이언트에게 각종 페이지 정보를 응답하기 위해
 * 페이징 데이터(이전, 다음버튼 활성화 여부, 시작, 끝페이지 번호 정보 등)을 생성하는 클래스
 * 여기서 페이지 알고리즘 다 만들어서 jsp 에 보낼 예정
 */

@Getter @Setter @ToString
public class PageMaker {

    private int beginPage; //시작페이지 번호
    private int endPage; //끝페이지 번호
    private boolean prev, next; //이전,다음활성화 버튼 여부

    private Page page; //현재 요청 페이지 정보
    private int totalCount; //전체 게시물 수

    //한 화면에 배치할
    private static final int PAGE_COUNT = 5;

    //기본 생성자 직접 만들어보겠음^^~
    //page, totalCount 는 받아와야해서 직접 만든거다.
    public PageMaker(Page page, int totalCount) {
        this.page = page;
        this.totalCount = totalCount;

        //끝 페이지 수 계산. 게시글 수가 아니라 페이지 숫자 몇 개 보여줄지 구하는 알고리즘 식이다.
        // 올림 (현재 페이지 번호 / 한 화면에 보여줄 페이지 수(amount 가 10이어도 이거는 5~20 이라는 뜻)
        //          * 한 화면에 보여질 페이지 수

        //올림하기위해 소수가 나와야 해서 double 형변환해줬는데 문제는 ceil 리턴 타입이 더블이어서 1.4를
        //올림하면 2.0이 되어버린다.. 그래서 int 캐스팅 필요
        //현재 페이지 27이면 30페이지까지 나와야 한다.

        this.endPage = (int) Math.ceil((double) page.getPageNum() / PAGE_COUNT) * PAGE_COUNT;

        //시작 페이지 수 계산
        this.beginPage = this.endPage - PAGE_COUNT + 1;

        /**
         * 페이지 마지막 구간 끝페이지 보정
         * 총 게시물 수가 284개이고 한 화면당 10페이지씩 배치하고 있다면
         * 페이지 마지막 구간은 21~30이 아닌 21~29가 되어야 함. => 앞 구간은 괜찮은데 맨 뒷구간이 잘릴 수도 있다는 것.
         * 따라서 마지막 구간에서 endPage 재보정이 필요함.
         *
         * 보정 공식 : 올림값 ( 총 게시물 수/ 한 페이지당 보여줄 게시물 수(페이지수가 아닌 amount )
         */

        int realEnd = (int)Math.ceil((double)totalCount / page.getAmount());

        //보정은 마지막 구간에서만 일어나야 한다. 280개 글에서 17페이지 -> 16-20, 리얼앤드 = 28 => 리얼앤드 < 앤드페이지
        if (realEnd < endPage){
            this.endPage = realEnd;
        }

        //이전 버튼 활성화 여부
        this.prev = this.beginPage > 1; //True

        //다음 버튼 활성화 여부
        this.next = this.endPage < realEnd;

    }


}
