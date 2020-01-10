package com.login.biz;

import java.util.List;

import com.login.dao.MyDao;
import com.login.dao.MyDaoImpl;
import com.login.dto.MyDto;

public class MyBizImpl implements MyBiz {

	
	private MyDao dao = new MyDaoImpl();
	
	@Override
	public List<MyDto> selectList() {
		// TODO Auto-generated method stub
		return dao.selectList();
	}

	@Override
	public List<MyDto> selectEnabled() {
		// TODO Auto-generated method stub
		return dao.selectEnabled();
	}

	@Override
	public int updateRole(int myno, String role) {
		// TODO Auto-generated method stub
		return dao.updateRole(myno, role);
	}

	@Override
	public MyDto login(String myid, String mypw) {
		// TODO Auto-generated method stub
		return dao.login(myid, mypw);
	}

	@Override
	public MyDto idChk(String myid) {
		// TODO Auto-generated method stub
		return dao.idChk(myid);
	}

	@Override
	public int insertUser(MyDto dto) {
		// TODO Auto-generated method stub
		return dao.insertUser(dto);
	}

	@Override
	public MyDto selectUser(int myno) {
		// TODO Auto-generated method stub
		return dao.selectUser(myno);
	}

	@Override
	public int updateUser(MyDto dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteUser(int myno) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	
}
