package com.mvc.biz;

import java.util.List;


import com.mvc.dao.MyDao;
import com.mvc.dto.MyDto;
import com.mvc.dao.MyDaoImpl;
public class MyBizImpl implements MyBiz {
	private MyDao dao = new MyDaoImpl();
	
	
	@Override
	public List<MyDto> selectList() {
		// TODO Auto-generated method stub
		return dao.selectList();
	}

	@Override
	public MyDto selectOne(int seq) {
		// TODO Auto-generated method stub
		return dao.selectOne(seq);
	}

	@Override
	public int insert(MyDto dto) {
		// TODO Auto-generated method stub
		return dao.insert(dto);
	}

	@Override
	public int update(MyDto dto) {
		// TODO Auto-generated method stub
		return dao.update(dto);
	}

	@Override
	public int delete(int seq) {
		// TODO Auto-generated method stub
		return dao.delete(seq);
	}

}
