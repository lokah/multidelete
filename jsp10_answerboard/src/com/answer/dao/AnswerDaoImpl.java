package com.answer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.answer.dto.AnswerDto;


import static com.answer.db.JDBCTemplate.*;






public class AnswerDaoImpl implements AnswerDao {

	@Override
	public List<AnswerDto> selectList() {
		// TODO Auto-generated method stub
		
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<AnswerDto> list = new ArrayList<AnswerDto>();
		
		try {
			pstm = con.prepareStatement(SELECT_LIST_SQL);
			rs = pstm.executeQuery();
			while(rs.next()) {
				
				AnswerDto dto = new AnswerDto();
				dto.setBoardno(rs.getInt(1));
				dto.setGroupno(rs.getInt(2));
				dto.setGroupseq(rs.getInt(3));
				dto.setTitletab(rs.getInt(4));
				dto.setTitle(rs.getString(5));
				dto.setContent(rs.getString(6));
				dto.setWriter(rs.getString(7));
				dto.setRegdate(rs.getDate(8));
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

	@Override
	public AnswerDto selectOne(int boardno) {
		// TODO Auto-generated method stub
		Connection con = getConnection();
		PreparedStatement pstm = null;
		
		//Statement stmt = null;
		ResultSet rs = null;
		

		AnswerDto dto = null;

		try {
			pstm =con.prepareStatement(SELECT_ONE_SQL);
			//stmt = con.createStatement();
			pstm.setInt(1,boardno);
			rs = pstm.executeQuery();
			//rs = stmt.executeQuery(SELECT_ONE_SQL+seq);
			
			while (rs.next()) {
				dto = new AnswerDto();
				dto.setBoardno(rs.getInt(1));
				dto.setGroupno(rs.getInt(2));
				dto.setGroupseq(rs.getInt(3));
				dto.setTitletab(rs.getInt(4));
				dto.setTitle(rs.getString(5));
				dto.setContent(rs.getString(6));
				dto.setWriter(rs.getString(7));
				dto.setRegdate(rs.getDate(8));
				

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			close(rs);
			close(pstm);
			close(con);
		}

		return dto;

	}

	@Override
	public int insert(AnswerDto dto) {
		// TODO Auto-generated method stub
		
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;

		try {

			pstm = con.prepareStatement(INSERT_SQL);
			pstm.setString(1, dto.getTitle());
			pstm.setString(2, dto.getContent());
			pstm.setString(3, dto.getWriter());
			

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
	public int update(AnswerDto dto) {
		// TODO Auto-generated method stub
		
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;

		try {

			pstm = con.prepareStatement(UPDATE_SQL);

			pstm.setString(1, dto.getTitle());

			pstm.setString(2, dto.getContent());

			pstm.setInt(3, dto.getBoardno());

			
			res = pstm.executeUpdate();
			if(res>0) {
				
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
	public int delete(int boardno) {
		// TODO Auto-generated method stub
		Connection con = getConnection();
		PreparedStatement pstm = null;

		int res = 0;
		try {

			pstm = con.prepareStatement(DELETE_SQL);

			pstm.setInt(1, boardno);

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
