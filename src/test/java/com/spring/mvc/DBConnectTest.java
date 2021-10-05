package com.spring.mvc;



import org.junit.jupiter.api.Test;

import java.sql.*;

public class DBConnectTest {

    //DB접속정보 설정
    private String userId = "hr";
    private String userPw = "hr";
    //DB 주소(회사마다 url 패턴이 있다. 구글에 oracle jdbc url 검색 후 웬만하면 공식문서 보고 이해 안가면 설명 블로그 보기)
    //Where the URL is of the form:
    //  jdbc:oracle:<drivertype>:<user>/<password>@<database>
    private String dbUrl = "jdbc:oracle:thin:@localhost:1521:xe";//1521은 포트 설정안하면 기본으로. :xe는 무료 버전이라는 뜻, localhost 다른 컴에서 쓸거면 해당 ip 치고 상대방은 방화벽 열어줘야한다.
    private String drivdr = "oracle.jdbc.driver.OracleDriver";//대소문자 주의

    @Test   //@jupiter 에 있는 테스트 써야 한다.
    void connectTest() {

        try {
            //1. 드라이버 클래스 동적 로딩
            Class.forName(drivdr);
            //오류이유: String 형을 받기로 했는데 오타 나면 못 찾을까봐 오류남. 예외처리해야함 alt enter tru/catch로 둘러싸기

            //2. 연결정보 생성
            Connection conn = DriverManager.getConnection(dbUrl, userId, userPw);
            System.out.println("DB연결 성공!"); //알림창 뜨면 실제로 연결 성공한거다.

            String sql = "SELECT first_name FROM employees";

            //3. SQL 실행객체 생성
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();//표 리턴한다. employees 는 행이 여러개 다중행이어서 (아마 107개 나올거다.) 읽기위해서는 반복문을 돌려야 한다.

            while(rs.next()) {
                String name = rs.getString("first_name");
                System.out.println("name = " + name);
            }


        } catch (ClassNotFoundException e) {
            System.out.println("드라이버 클래스를 찾을 수 없습니다.");
            e.printStackTrace();
        } catch (SQLException throwables) {
            System.out.println("SQL 에러!");
            throwables.printStackTrace();
        }
    }
}
