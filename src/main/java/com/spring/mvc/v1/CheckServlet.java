package com.spring.mvc.v1;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/check")
public class CheckServlet extends HttpServlet {

    @Override
    public void service(ServletRequest req, ServletResponse resp) throws ServletException, IOException {
        System.out.println("나이 확인중...");

        //요청시 전달된 form 데이터를 읽어줌. 외울 필요는 없다.
        String ageStr = req.getParameter("age");
        System.out.println("age = " + ageStr);

        int age = Integer.parseInt(ageStr);

        String msg = (age > 19) ? "성인" : "미성년자";
        System.out.println("msg = " + msg);

        //HTML 랜더링
        resp.setCharacterEncoding("UTF-8");
        //resp.setContentType();

        //HTML 을 그릴 팬
        PrintWriter w = resp.getWriter();
        w.println("<html>");
        w.println(" <head>");
        w.println(" </head>");
        w.println(" <body>");
        for (int i = 0; i <3 ; i++) {
            w.println("    <h1>당신은 " + msg + "입니다.</h1>");
        }
        w.println(" </body>");
        w.println("</html>");
    }
}

