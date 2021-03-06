package com.answer.biz;

import java.util.List;

import com.answer.dao.AnswerDao;
import com.answer.dao.AnswerDaoImpl;
import com.answer.dto.AnswerDto;

public class AnswerBizImpl implements AnswerBiz {
	
	private AnswerDao dao = new AnswerDaoImpl();

	@Override
	public List<AnswerDto> selectList() {
		// TODO Auto-generated method stub
		return dao.selectList();
	}

	@Override
	public AnswerDto selectOne(int boardno) {
		// TODO Auto-generated method stub
		return dao.selectOne(boardno);
	}

	@Override
	public int insert(AnswerDto dto) {
		// TODO Auto-generated method stub
		return dao.insert(dto);
	}

	@Override
	public int update(AnswerDto dto) {
		// TODO Auto-generated method stub
		return dao.update(dto);
	}

	@Override
	public int delete(int boardno) {
		// TODO Auto-generated method stub
		return dao.delete(boardno);
	}

	@Override
	public int answerProc(AnswerDto dto) {
		// TODO Auto-generated method stub
		
		int updateRes = dao.answerUpdate(dto.getBoardno());
		int insertRes = dao.answerInsert(dto);
		return updateRes + insertRes;
		
		
	}

	

	
	
}
