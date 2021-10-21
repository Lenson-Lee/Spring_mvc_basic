package com.spring.mvc.member.repository;

import com.spring.mvc.member.domain.Auth;
import com.spring.mvc.member.domain.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class memberMapperTest {

    @Autowired
    MemberMapper memberMapper;

    @Test
    @DisplayName("회원가입에 성공해야 한다.")
    void regTest(){
        Member member = new Member();

//        <!--  Pw의 경우 암호화 되어서 코드가 길어지니까 넉넉히 150 넣는다.  -->
        member.setAccount("abc3334321");

        //원본 비번(시큐리티 라이브러리 사용예정)
        String rawPw = "5678!";
        //비밀번호 인코딩(암호화 알고리즘이 적용된 시큐리티 라이브러리 객체) 시큐리티 받아야 사용 가능
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        //암호화된 비밀번호 완성! 평문 비밀번호를 알 수 없게 된다!
        String encodePw = encoder.encode(rawPw);


//        member.setPassword("5678!");
        member.setPassword(encodePw);   //시큐리티 라이브러리를 통해 비밀번호 보호
        member.setName("박명희");
        member.setEmail("def4321@gmail.com");
        member.setAuth(Auth.COMMON);

        memberMapper.register(member);

    }

    @Test
    @DisplayName("아이디, 이메일 중복확인에 성공해야 한다.")
    void duplicateTest() {
        String inputAccount = "abc4321";

        Map<String, Object> datas = new HashMap<>();
        datas.put("type", "account");
        datas.put("keyword", inputAccount);

        int result = memberMapper.isDuplicate(datas);

        assertTrue(result == 1);
    }

    @Test
    @DisplayName("로그인 검증을 수행해야 한다.")
    void loginTest() {
        //로그인 시도 아이디(암호화한 아이디로 써야한다. 검증데이터(평문 데이터)는 없는 데이터로 쳐야한다.)
        String inputId = "abc3334321";
        //로그인 시도 패스워드(DB에서는 이렇게 저장된게 아니라 암호화되있어서 equals 불가능)
        String inputPw = "5678!";

        //일단 가입되어있는지부터 조회해야한다.
        //로그인 시도 아이디를 기반으로 회원정보를 조회
        Member member = memberMapper.getUser(inputId);

        if (member != null) {
            //db에 저장된 비번
            String dbPw = member.getPassword();

            //암호화된 비번을 디코딩해서 비교
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

            //인코더객체만들고 인코더.매치스(아이디, 비번) 하면 맞으면 t 틀리면 f.....
            //encoder.matches : 아이디 비번 여부 확인
            if (encoder.matches(inputPw, dbPw)) {
                System.out.println("로그인 성공! ======================================================================================");
            } else {
                System.out.println("비밀번호가 틀렸습니다.");
            }

        } else {
            System.out.println("회원가입을 먼저 진행하세요.");
        }
    }
}