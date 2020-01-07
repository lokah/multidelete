package com.md.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.md.db.JDBCTemplate;
import com.md.dto.MDDto;

public class MDDao extends JDBCTemplate {

	public List<MDDto> selectList() {

		Connection con = getConnection();
		Statement stmt = null;
		ResultSet rs = null;

		String sql = " SELECT SEQ, WRITER, TITLE, CONTENT, REGDATE FROM MDBOARD ORDER BY SEQ DESC ";
		List<MDDto> list = new ArrayList<MDDto>();

		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {

				MDDto dto = new MDDto();
				dto.setSeq(rs.getInt(1));
				dto.setWriter(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setRegdate(rs.getDate(5));
				list.add(dto);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
			close(rs);
			close(stmt);
			close(con);
		}

		return list;
	}

	public MDDto selectOne(int seq) {
		
		Connection con = getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		String sql = " SELECT SEQ, WRITER, TITLE, CONTENT, REGDATE FROM MDBOARD WHERE SEQ = " + seq ;
		
		MDDto dto = null;
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				
				dto = new MDDto();
				dto.setSeq(rs.getInt(1));
				dto.setWriter(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setRegdate(rs.getDate(5));
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
			close(rs);
			close(stmt);
			close(con);
		}

		return dto;

	}
	
	public int insert(MDDto dto) {
		
		
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		String sql = " INSERT INTO MDBOARD VALUES(MDSEQUENCE.NEXTVAL, ?,?,?,SYSDATE) ";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, dto.getWriter());
			pstm.setString(2, dto.getTitle());
			pstm.setString(3, dto.getContent());
			
			res = pstm.executeUpdate();
			if(res>0) {
				commit(con);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
			close(pstm);
			close(con);
		}
		
		
		return res;
	}

}
