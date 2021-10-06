package com.spring.mvc.score.repository;

import com.spring.mvc.score.domain.Score;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository("jr")
@Log4j2

/*
    스프링 없는 원초적인 JDBC의 순수 코드.
    쓸데없는 복붙이 많고 DB 접속정보가 코드에 들어간다.
   
 */
public class JdbcScoreRepository implements ScoreRepository {

    //DB접속정보 설정
    private String userId = "spring3";
    private String userPw = "1234";
    //DB 주소(회사마다 url 패턴이 있다. 구글에 oracle jdbc url 검색 후 웬만하면 공식문서 보고 이해 안가면 설명 블로그 보기)
    //Where the URL is of the form:
    //  jdbc:oracle:<drivertype>:<user>/<password>@<database>
    private String dbUrl = "jdbc:oracle:thin:@localhost:1521:xe";//1521은 포트 설정안하면 기본으로. :xe는 무료 버전이라는 뜻, localhost 다른 컴에서 쓸거면 해당 ip 치고 상대방은 방화벽 열어줘야한다.
    private String driver = "oracle.jdbc.driver.OracleDriver";//대소문자 주의


    @Override
    public void save(Score score) {
        String sql = "INSERT INTO score VALUES (seq_score.nextval, ?, ?, ?, ?, ?, ?)";//이름, 학번,

        Connection conn = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(dbUrl, userId, userPw);

            PreparedStatement pstmt = conn.prepareStatement(sql);

            //?를 채워야 한다.
            pstmt.setString(1, score.getName());
            pstmt.setInt(2, score.getKor());
            pstmt.setInt(3, score.getEng());
            pstmt.setInt(4, score.getMath());
            pstmt.setInt(5, score.getTotal());
            pstmt.setDouble(6, score.getAverage());

            //INSERT, UPDATE, DELETE문은 executeUpdate()메서드 사용
            int resultNum = pstmt.executeUpdate();

            if (resultNum == 0) {
                log.info("데이터 삽입 실패!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Score> findAll() {
        //Map 에서 뽑는게 아닌 실제 DB 에서 뽑아쓰는것.
        List<Score> scoreList = new ArrayList<>();

        Connection conn = null;//conn 선언 및 할당분한, finally 블록에서 사용하기 위해 try 밖에 설정
        try {

            //1. DB 접속을 하기 위한 드라이버 로딩
            Class.forName(driver);

            //2. 연결정보 객체 생성
            conn = DriverManager.getConnection(dbUrl, userId, userPw);

            //3. SQL 실행 객체 생성
            String sql = "SELECT * FROM score";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            //4. SQL 실행 (SQL의 테이블에 정보 뜬다)
            ResultSet rs = pstmt.executeQuery();

            rs.next();//한 번 호출: 제 1행에 위치
            rs.next();//두 번 호출: 제 2행에 위치
            boolean next3 = rs.next();
            //boolean next4 = rs.next();
            //log.info("next4? " + next4);  //false가 나오고 오류가 떠야 하는데 왜 true인지..
            //눈치챘듯이 이렇게 쓰느게 아니다. false 가 나올 때까지 학번에 따라 객체 생성 후 List 에 값을 넣기

            while (rs.next()) {
                scoreList.add(new Score(rs));
//                int stuNum = rs.getInt("stu_num");
//                String name = rs.getString("stu_name");
//                int kor = rs.getInt("kor");
//                int eng = rs.getInt("eng");
//                int math = rs.getInt("math");
//                int total = rs.getInt("total");//컬럼명을쓰는거다!
//                double average = rs.getDouble("average");

                //빨간줄 뜨면 생성자를 생성합니다(Score) 가서 위에 변수들 싹 복붙
//                Score score = new Score(rs);    //노가다 하지마고 생성자 뽑아서 스코어를 넣어주면 된다.
//                //score.setStuNum(stuNum);
//                scoreList.add(score:여기서!!); //>> ctrl alt N 으로 간결하게

            }
//            log.info("이름: " + name);
//            log.info("총점: " + total);
//            log.info("next3? " + next3);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //5. DB 자원 해제
            try {
                if (conn != null) conn.close();   //conn 선언 및 할당분할로 finally 블록에서도 사용가능하도록.
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return scoreList;
    }

    @Override
    //위의 List 와 비슷하지만 위에서는 sql을 받고 sql의 ? 값이 없어서 바로 실행했지만
    //이번 sql은 ? (채워줘야 하는 부분)이 있어 넣어줘야 실행이 되기 때문에 중간에 ? 를 채워주는 과정이 있어야 한다.
    public Score findOne(int stuNum) {
        String sql = "SELECT * FROM score WHERE stu_num=?";//변동되는 부분은 ? 로 입력

        Connection conn = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(dbUrl, userId, userPw);

            PreparedStatement pstmt = conn.prepareStatement(sql);

            //?를 채워야 한다.
            pstmt.setInt(1, stuNum);

            ResultSet rs = pstmt.executeQuery();

            //반복문을 안돌려도 된다. 학번으로 조회하는데 학번은 pk 여서 성적조회가 바로 가능?
            if (rs.next()) {
                return new Score(rs);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    @Override
    public void remove(int stuNum) {
        String sql = "DELETE FROM score WHERE stu_num=?";

        Connection conn = null;

        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(dbUrl, userId, userPw);

            PreparedStatement pstmt = conn.prepareStatement(sql);

            //?를 채워야 한다.
            pstmt.setInt(1, stuNum);

            //INSERT, UPDATE, DELETE문은 executeUpdate()메서드 사용
            int resultNum = pstmt.executeUpdate();

            if (resultNum == 0) {
                log.info("데이터 삭제 실패!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
