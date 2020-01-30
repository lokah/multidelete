package com.cal.dao;


import static com.cal.db.JDBCTemplate.*;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cal.dto.CalDto;


public class CalDao {

	public int insertCalBoard(CalDto dto) {
		// TODO Auto-generated method stub
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		String sql = " INSERT INTO CALBOARD " + 
				" VALUES(CALBOARDSEQ.NEXTVAL,?,?,?,?,SYSDATE) ";

		try {

			pstm = con.prepareStatement(sql);
			pstm.setString(1, dto.getId());
			pstm.setString(2, dto.getTitle());
			pstm.setString(3, dto.getContent());
			pstm.setString(4, dto.getMdate());

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
	
	
	public List<CalDto> selectCalList(String id, String yyyyMMdd){
		String sql = " SELECT * FROM CALBOARD " +
				" WHERE ID = ? AND SUBSTR(MDATE,1,8)=? ";
		
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<CalDto> list = new ArrayList<CalDto>();
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, id);
			pstm.setString(2, yyyyMMdd);
			rs = pstm.executeQuery();
			while(rs.next()) {
				
				CalDto dto = new CalDto();
				dto.setSeq(rs.getInt(1));
				dto.setId(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setMdate(rs.getString(5));
				dto.setRegdate(rs.getDate(6));
				list.add(dto);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
			close(rs);
			close(pstm);
			close(con);
		}
		
		return list;
	}
	
	public List<CalDto> getCalViewList(String id, String yyyyMM){
		
		String sql = " SELECT * FROM( " + 
				" SELECT(ROW_NUMBER() OVER(PARTITION BY SUBSTR(MDATE,1,8) ORDER BY MDATE)) RN, " + 
				" SEQ,ID,TITLE,CONTENT,MDATE,REGDATE " + 
				" FROM CALBOARD " + 
				" WHERE ID = ? AND SUBSTR(MDATE,1,6)= ? " + 
				" ) " + 
				" WHERE RN BETWEEN 1 AND 3 ";
		
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<CalDto> list = new ArrayList<CalDto>();
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, id);
			pstm.setString(2, yyyyMM);
			rs = pstm.executeQuery();
			while(rs.next()) {
				
				CalDto dto = new CalDto();
				dto.setSeq(rs.getInt(2));
				dto.setId(rs.getString(3));
				dto.setTitle(rs.getString(4));
				dto.setContent(rs.getString(5));
				dto.setMdate(rs.getString(6));
				dto.setRegdate(rs.getDate(7));
				list.add(dto);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
			close(rs);
			close(pstm);
			close(con);
		}
		
		return list;
	}
	
	public int getCalCount(String id, String yyyyMMdd) {
		
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		int res=0;
		
		String sql = " SELECT COUNT(*) FROM CALBOARD "
				+ " WHERE ID=? AND SUBSTR(MDATE,1,8) = ? ";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, id);
			pstm.setString(2, yyyyMMdd);
			rs = pstm.executeQuery();
			while(rs.next()) {
				res=rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close(rs);
			close(pstm);
			close(con);
			
		}
		
		
		return 0;
	}
	
}
