package com.login.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.login.dto.MyDto;
import static com.login.db.JDBCTemplate.*;

public class MyDaoImpl implements MyDao {

	@Override
	public List<MyDto> selectList() {
		// TODO Auto-generated method stub
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;

		List<MyDto> list = new ArrayList<MyDto>();
		
		String sql = " SELECT MYNO, MYID, MYPW, MYNAME, MYADDR, MYPHONE, MYEMAIL, MYENABLED, MYROLE  " 
				+ " FROM MYMEMBER "
				+ " ORDER BY MYNO DESC ";

		try {
			pstm = con.prepareStatement(sql);
			rs = pstm.executeQuery();
			while (rs.next()) {

				MyDto dto = new MyDto();
				dto.setMyno(rs.getInt(1));
				dto.setMyid(rs.getString(2));
				dto.setMypw(rs.getString(3));
				dto.setMyname(rs.getString(4));
				dto.setMyaddr(rs.getString(5));
				dto.setMyphone(rs.getString(6));
				dto.setMyemail(rs.getString(7));
				dto.setMyenabled(rs.getString(8));
				dto.setMyrole(rs.getString(9));
				list.add(dto);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			close(rs);
			close(pstm);
			close(con);
		}

		return list;
	}

	@Override
	public List<MyDto> selectEnabled() {
		// TODO Auto-generated method stub
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;

		List<MyDto> list = new ArrayList<MyDto>();
		
		String sql = " SELECT MYNO, MYID, MYPW, MYNAME, MYADDR, MYPHONE, MYEMAIL, MYENABLED, MYROLE  " 
				+ " FROM MYMEMBER "
				+ " WHERE MYENABLED = 'Y' ORDER BY MYNO DESC ";

		try {
			pstm = con.prepareStatement(sql);
			rs = pstm.executeQuery();
			while (rs.next()) {

				MyDto dto = new MyDto();
				dto.setMyno(rs.getInt(1));
				dto.setMyid(rs.getString(2));
				dto.setMypw(rs.getString(3));
				dto.setMyname(rs.getString(4));
				dto.setMyaddr(rs.getString(5));
				dto.setMyphone(rs.getString(6));
				dto.setMyemail(rs.getString(7));
				dto.setMyenabled(rs.getString(8));
				dto.setMyrole(rs.getString(9));
				list.add(dto);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			close(rs);
			close(pstm);
			close(con);
		}

		return list;
	}

	@Override
	public int updateRole(int myno, String role) {
		// TODO Auto-generated method stub
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		String sql = " UPDATE MYMEMBER SET MYROLE = ? "
				+ " WHERE MYNO = ? ";
		
		
		return res;
	}

	@Override
	public MyDto login(String myid, String mypw) {
		// TODO Auto-generated method stub
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		MyDto dto = null;
		
		String sql = " SELECT * FROM MYMEMBER "
				+ " WHERE MYID = ? AND MYPW = ? "
				+ " AND MYENABLED = ? ";
		try {
			pstm = con.prepareStatement(sql);
			
			pstm.setString(1, myid);
			pstm.setString(2, mypw);
			pstm.setString(3, "Y");
			
			rs = pstm.executeQuery();
			while(rs.next()) {
				dto = new MyDto();
				dto.setMyno(rs.getInt(1));
				dto.setMyid(rs.getString(2));
				dto.setMypw(rs.getString(3));
				dto.setMyname(rs.getString(4));
				dto.setMyaddr(rs.getString(5));
				dto.setMyphone(rs.getString(6));
				dto.setMyemail(rs.getString(7));
				dto.setMyenabled(rs.getString(8));
				dto.setMyrole(rs.getString(9));
				
			}
					
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return dto;
	}

	@Override
	public MyDto idChk(String myid) {
		// TODO Auto-generated method stub
		
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		MyDto dto = null;
		String sql = " SELECT * FROM MYMEMBER WHERE MYID = ? ";
		
		try {
			pstm = con.prepareStatement(sql);
			
			pstm.setString(1, myid);
			
			
			rs = pstm.executeQuery();
			while(rs.next()) {
				dto = new MyDto();
				dto.setMyno(rs.getInt(1));
				dto.setMyid(rs.getString(2));
				dto.setMypw(rs.getString(3));
				dto.setMyname(rs.getString(4));
				dto.setMyaddr(rs.getString(5));
				dto.setMyphone(rs.getString(6));
				dto.setMyemail(rs.getString(7));
				dto.setMyenabled(rs.getString(8));
				dto.setMyrole(rs.getString(9));
				
			}
					
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstm);
			close(con);
		}
		
		
		return dto;
	}

	@Override
	public int insertUser(MyDto dto) {
		// TODO Auto-generated method stub
		
		Connection con = getConnection();
		PreparedStatement pstm = null;
		
		int res = 0;
		String sql = " INSERT INTO MYMEMBER " + " VALUES(MYMEMBERSEQ.NEXTVAL, ?, ?, ?,?,?,?, 'Y', 'USER') ";

		try {

			pstm = con.prepareStatement(sql);
			pstm.setString(1, dto.getMyid());
			pstm.setString(2, dto.getMypw());
			pstm.setString(3, dto.getMyname());
			pstm.setString(4, dto.getMyaddr());
			pstm.setString(5, dto.getMyphone());
			pstm.setString(6, dto.getMyemail());
			

			res = pstm.executeUpdate();
			if (res > 0) {

				commit(con);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstm);
			close(con);
		}

		return res;
	}

	@Override
	public MyDto selectUser(int myno) {
		// TODO Auto-generated method stub
		
		Connection con = getConnection();
		//PrepraredStatement pstm = null;
		
		Statement stmt = null;
		ResultSet rs = null;
		

		MyDto dto = null;
		String sql = " SELECT MYNO, MYID, MYPW, MYNAME, MYADDR, MYPHONE, MYEMAIL, MYENABLED, MYROLE  "  
				+ " FROM MYMEMBER " + " WHERE MYNO= ";

		try {
			//pstm =con.prepareStatement()
			stmt = con.createStatement();
			//pstm.setInt(1,myno);
			//rs.pstm.executequery();
			
				
					
			rs = stmt.executeQuery(sql+myno);
			while (rs.next()) {

				dto = new MyDto();
				dto.setMyno(rs.getInt(1));
				dto.setMyid(rs.getString(2));
				dto.setMypw(rs.getString(3));
				dto.setMyname(rs.getString(4));
				dto.setMyaddr(rs.getString(5));
				dto.setMyphone(rs.getString(6));
				dto.setMyemail(rs.getString(7));
				dto.setMyenabled(rs.getString(8));
				dto.setMyrole(rs.getString(9));

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			close(rs);
			close(stmt);
			close(con);
		}

		return dto;

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
