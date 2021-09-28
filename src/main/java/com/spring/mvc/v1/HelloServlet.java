package com.spring.mvc.v1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
    # 서블릿: 웹 브라우저의 동적인 요청을 처리하여 서버에서 html 을 생성하여 응답하는 클래스
    항상 extends HttpServlet 상속하면 된다.
 */

//URL 부여: 우리서버에 /hello 라고 주소 요청을 하면 내가 처리하겠다~
@WebServlet("/hello")
public class HelloServlet extends HttpServlet {

    public HelloServlet() {
        System.out.println("헬로 서블릿 객체 생성됨!");
    }

    //HTTP 요청이 왔을 때 WAS 에 의해 자동호출되는 메서드.
    //용도: 핵심 로직을 기술하는 곳
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("환영합니다!");
    }
}
