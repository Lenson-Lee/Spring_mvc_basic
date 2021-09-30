package com.spring.mvc.v2;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/*
 *   스프링 MVC 프레임워크의 컨트롤러는 웹 브라우저의 요청을 받아 처리하며 응답할 화면(VIiw)을 선택하는 역할을 한다.
 *
 *   스프링이 이 클래스를 컨트롤러로 인식하게 하려면 @Controller 아노테이션을 붙여야 한다.(서블릿 대체)
 */

@Controller
public class BasicController {

    //요청 처리 메서드

    //해당 경로로 URL 요청이 오면 이 메서드가 처리하겠다(주소창에 써서 들어오면 일단 Get 이다.)
    @GetMapping("/basic/test")
    public String test() {
        System.out.println("test 요청이 들어왔어요!");
        //return "/WEB-INF/views/check.jsp";  //return 값은 내가 이 요청이 끝난 후 응답할 페이지 경로를 뜻한다.
        return "check";  //Mvc 에서 리졸버 해서 이렇게 가능해짐.
    }

    //요청 URL: /basic    으로 접근했을 때 basic.jsp 가 열리도록 요청 메서드를 작성하시오
    @GetMapping("/basic")
    public String basic() {
        //return "/WEB-INF/views/basic.jsp";
        return "basic";
    }

    //요청 파라미터 받기 (요청시에 브라우저에서 넘어오는 데이터) 받기
    @GetMapping("/basic/join")
    public String join(HttpServletRequest request) {
        System.out.println("/basic/join 요청이 들어옴");
        //input type의 Name을 적으면 된다.
        String userId = request.getParameter("userId");
        String userName = request.getParameter("userName");

        //int 로 바꾸는게 귀찮다면 스프링!
        int userAge = Integer.parseInt(request.getParameter("userAge"));

        System.out.println("userId = " + userId);
        System.out.println("userName = " + userName);
        return "";
    }

    //parseInt 없이 하는법
    //파라미터에 원하는 형을 적으면 된다. 주의할 점은 input tupe의 name을 써야 한다.
    //원하는 이름으로 입력하고 싶을 시에는 @RequestParam("input tupe의 name") String 원하는이름) 처럼 쓴다.
    //@PostMapping: 주소창에 입력정보 숨기기. HTML form에 method = "POST" 추가
    //checkbox의 경우 배열(대신 List 사용이 편하다) 로 받는다. @RequestParam("hobbys")ArrayList<String> hobbys)

    @PostMapping("/basic/join2")
    public String join(@RequestParam("userId") String id, String userPw, String userName,int userAge,
                       @RequestParam("hobbys")ArrayList<String> hobbys) {
        System.out.println("/basic/join2 요청이 들어옴");

        System.out.println("userId = " + id);
        System.out.println("userName = " + userName);
        System.out.println("userAge = " + userAge);
        System.out.println("hobbys = " + hobbys);

        return "";
    }

    //매개변수 정보가 많으면 하나하나 적기 힘드니 v2.User 클래스를 이용하겠음
    @PostMapping("/basic/join3")
    public String join(User user, Model model) {
        System.out.println("아이디 =" + user.getUserId());
        System.out.println("이름 =" + user.getUserName());
        System.out.println("나이 =" + user.getUserAge());
        System.out.println("취미 =" + user.getHobbys());

        model.addAttribute("pw", user.getUserPw());
        return "request/result";
    }

    //화면쪽으로 서버의 데이터를 전달하는 방법
    @GetMapping("/model")
    public String modelBasic(Model model, int age) {
        //클라이언트 쪽으로 데이터를 greet 라는 이름으로 메롱을 담아서 전달
        model.addAttribute("greet","메롱");
        model.addAttribute("myAge",age);
        int birthYear = LocalDate.now().getYear()-age+1;
        model.addAttribute("myBirth", birthYear);

        return "request/model_study";
    }


    // ======================= 퀴즈 =======================

    @GetMapping("/res-quiz")
    public String resQuiz() {
        return "request/res-quiz";
    }

    @PostMapping("/response/quiz")
    public String quiz(String userId, String userPw, Model model) {
        System.out.println("로그인 요청 들어옴!");

        model.addAttribute("account", userId);

        if (userId.equals("kim123") && userPw.equals("kkk1234")){
            return "request/success";
        } else {
            return "request/fail";
        }
    }

}
