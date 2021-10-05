
CREATE TABLE score (
    stu_num NUMBER(10),
    stu_name VARCHAR2(20) NOT NULL,
    kor NUMBER(3) NOT NULL,
    eng NUMBER(3) NOT NULL,
    math NUMBER(3) NOT NULL,
    total NUMBER(3),
--    최대 100.00 : 소숫점 2자리, 총 5자리 수
    average NUMBER(5,2),
    CONSTRAINT pk_score PRIMARY KEY (stu_num)
);

--  연속된 숫자 생성sequence, nextval: 한 번 할 때마다 ++
CREATE SEQUENCE seq_score;

SELECT * FROM score;

INSERT INTO score VALUES(seq_score.nextval, '홍길동',90,90,90,270,90);
INSERT INTO score VALUES(seq_score.nextval, '박길동',80,80,80,240,80);
INSERT INTO score VALUES(seq_score.nextval, '고길동',100,100,100,300,100);
INSERT INTO score VALUES(seq_score.nextval, '왕길동',10,10,10,30,10);
COMMIT;
