package com.login.biz;

import java.util.List;

import com.login.dto.MyDto;

public interface MyBiz {


	
	//관리자(admin)기능
	
	//회원 전체 정보(탈퇴회원 포함)
	public List<MyDto> selectList();
	//2. 회원 전체 정보(가입된 회원들만) myenabled=y
	public List<MyDto> selectEnabled();
	//3.회원 등급 조정
	public int updateRole(int myno, String role);
	
	//사용자(user)기능
	//로그인
	public MyDto login(String myid, String mypw);
	//회원가입
	//중복 체크, 
	public MyDto idChk(String myid);
	//회원 가입
	public int insertUser(MyDto dto);
	
	//내 정보 조회
	public MyDto selectUser(int myno);
	//정보 수정
	public int updateUser(MyDto dto);
	//회원 탈퇴 :update=my
	public int deleteUser(int myno);
	

}
