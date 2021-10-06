package com.spring.mvc.score.repository;

import com.spring.mvc.score.domain.Score;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper //마이바티스 SQL 처리 인터페이스
public interface ScoreMapper {

    //점수 저장 기능
    void save(Score score);

    //전체 점수(한 명이 아닌 여러명이라 배열 list 사용) 정보 조회 기능
    List<Score> findAll();

    //개별 점수 정보 조회 기능: 개별 정보 인식을 위해서는 학번 필요.
    Score findOne(int stuNum);

    //점수 정보 삭제 기능
    void remove(int stuNum);

}
