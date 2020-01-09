package com.mvc.biz;

import java.util.List;

import com.mvc.dto.MVCDto;
import com.mvc.dao.MVCDao;
import com.mvc.dao.MVCDaoimpl;

public class MVCBizimpl implements MVCBiz {

	private MVCDao dao = new MVCDaoimpl();
	@Override
	public List<MVCDto> selectList() {
		// TODO Auto-generated method stub
		
		
		return dao.selectList();
	}

	@Override
	public MVCDto selectOne(int seq) {
		// TODO Auto-generated method stub
		return dao.selectOne(seq);
	}

	@Override
	public int insert(MVCDto dto) {
		// TODO Auto-generated method stub
		return dao.insert(dto);
	}

	@Override
	public int update(MVCDto dto) {
		// TODO Auto-generated method stub
		return dao.update(dto);
	}

	@Override
	public int delete(int seq) {
		// TODO Auto-generated method stub
		return dao.delete(seq);
	}

	@Override
	public boolean multiDelete(String[] seqs) {
		// TODO Auto-generated method stub
		return dao.multiDelete(seqs);
	}

}
