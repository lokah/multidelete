package com.answer.dao;

import java.util.List;

import com.answer.dto.AnswerDto;

public interface AnswerDao {

	String SELECT_LIST_SQL = " SELECT * FROM ANSWERBOARD " 
			+ " ORDER BY GROUPNO DESC, GROUPSEQ ";
String SELECT_ONE_SQL = " SELECT * " + " FROM ANSWERBOARD " + " WHERE BOARDNO = ? ";
String INSERT_SQL = " INSERT INTO ANSWERBOARD " 
+ " VALUES(BOARDNOSEQ.NEXTVAL, GROUPNOSEQ.NEXTVAL, 1 , 0, ?, ?, ?,SYSDATE) ";
String UPDATE_SQL = " UPDATE ANSWERBOARD SET " + " TITLE = ?, CONTENT= ? " + " WHERE BOARDNO = ? ";
String DELETE_SQL = " DELETE FROM ANSWERBOARD WHERE BOARDNO = ? ";
	
	
	public List<AnswerDto> selectList();

	public AnswerDto selectOne(int seq);

	public int insert(AnswerDto dto);

	public int update(AnswerDto dto);

	public int delete(int boardno);
	
}
