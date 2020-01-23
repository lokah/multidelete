package com.bike.dao;
import static com.bike.db.JDBCTemplate.*;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bike.dto.BikeDto;




public class BikeDao {

	public List<BikeDto> selectList(){
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<BikeDto> list = new ArrayList<BikeDto>();
		
		String sql = " SELECT * FROM BIKE_TB " + " ORDER BY CONTENT_ID DESC  ";
		
		try {
			pstm = con.prepareStatement(sql);
			rs = pstm.executeQuery();
			while(rs.next()) {
				
				BikeDto dto = new BikeDto();
				dto.setAddr_gu(rs.getString(1));
				dto.setContent_id(rs.getInt(2));
				dto.setContent_nm(rs.getString(3));
				dto.setNew_addr(rs.getString(4));
				dto.setCradle_count(rs.getInt(5));
				dto.setLongitude(rs.getDouble(6));
				dto.setLatitude(rs.getDouble(7));
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

	public BikeDto selectOne(int content_id) {
		
		Connection con = getConnection();
		PreparedStatement pstm = null;
		
		//Statement stmt = null;
		ResultSet rs = null;
		String sql = " SELECT * FROM BIKE_TD " + " WHERE CONTENT_ID = ?  ";

		BikeDto dto = null;

		try {
			pstm =con.prepareStatement(sql);
			//stmt = con.createStatement();
			pstm.setInt(1,content_id);
			rs = pstm.executeQuery();
			//rs = stmt.executeQuery(SELECT_ONE_SQL+seq);
			while (rs.next()) {

				dto = new BikeDto();
				dto.setAddr_gu(rs.getString(1));
				dto.setContent_id(rs.getInt(2));
				dto.setContent_nm(rs.getString(3));
				dto.setNew_addr(rs.getString(4));
				dto.setCradle_count(rs.getInt(5));
				dto.setLongitude(rs.getDouble(6));
				dto.setLatitude(rs.getDouble(7));

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

	public int insert(List<BikeDto> list) {
		
		
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		String sql = " INSERT INTO BIKE_TB " + " VALUES(?, ?, ?, ?, ?, ?, ?) ";

		try {

			pstm = con.prepareStatement(sql);
			
			for(int i=0;i<list.size();i++) {
				BikeDto dto = list.get(i);
				
			
			pstm.setString(1, dto.getAddr_gu());
			pstm.setInt(2, dto.getContent_id());
			pstm.setString(3, dto.getContent_nm());
			pstm.setString(4, dto.getNew_addr());
			pstm.setInt(5, dto.getCradle_count());
			pstm.setDouble(6, dto.getLongitude());
			pstm.setDouble(7, dto.getLatitude());
			
			
			pstm.addBatch();
			}
			System.out.println("3 query 준비"+ sql);
			int[] result = pstm.executeBatch();
			
			for(int i=0;i<result.length;i++) {
				
				if(result[i]==-2) {
					
					res++;
				}
			}
			
			if(res==list.size()) {
				
				commit(con);
			}else {
				rollback(con);
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

	
	


	public int delete() {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		String sql = " DELETE FROM BIKE_TB ";
		int res = 0;
		try {

			pstm = con.prepareStatement(sql);

			//pstm.setInt(1, content_id);

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
