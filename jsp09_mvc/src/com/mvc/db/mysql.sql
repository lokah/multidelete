DROP SEQUENCE MVCSEQ;
DROP TABLE MVCBOARD;

CREATE SEQUENCE MVCSEQ;

CREATE TABLE MVCBOARD(
SEQ NUMBER PRIMARY KEY,
WRITER VARCHAR2(2000) NOT NULL,
TITLE VARCHAR2(4000) NOT NULL,
CONTENT VARCHAR2(400) NOT NULL,
REGDATE DATE NOT NULL

)


INSERT INTO MVCBOARD VALUES(MVCSEQ.NEXTVAL, '관리자','TEST제목','TEST 내용', SYSDATE);


SELECT *
FROM MVCBOARD;