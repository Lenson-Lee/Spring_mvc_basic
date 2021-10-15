package com.spring.mvc.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //자동으로 jsp 를 안찾고 서버에 바로 보낸다. 까보면 @Controller랑 ResponseBody들어있어서 그런듯?
@RequestMapping("/api/v2")
public class ApiControllerV2 {

    /**
     *  #ResponseEntity
     *  - 스프링 REST API가 응답할 때(우리가 만들고 있는게 rest api이다. )
     *    응답데이터뿐만 아니라 응답상태코드, 응답 헤더 등을 조작해서 전송할 수 있게 해주는 객체
     */

    //이제부터 public String이라 쓰지 않는다.
    //ResponseEntity<형태 적기> 를 통해 쓸 거다.
    @GetMapping("/hello")
    public ResponseEntity<String> hello(String p) {

        if(p.equals("hi")) {
            return new ResponseEntity<String>("나도 안녕~", HttpStatus.OK);//내가 오류를 지정할수있다?
        } else {
            return new ResponseEntity<String>("인사해줘", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
