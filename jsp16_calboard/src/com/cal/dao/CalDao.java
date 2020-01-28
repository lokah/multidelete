package com.cal.dao;


import static com.cal.db.JDBCTemplate.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.cal.dto.CalDto;

public class CalDao {

	public int insert(CalDto dto) {
		// TODO Auto-generated method stub
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		String sql = " INSERT INTO CALBOARD " + 
				" VALUES(CALBOARDSEQ.NEXTVAL,?,?,?,?,SYSDATE); ";

		try {

			pstm = con.prepareStatement(sql);
			pstm.setString(1, dto.getId());
			pstm.setString(2, dto.getTitle());
			pstm.setString(3, dto.getContent());
			pstm.setString(3, dto.getMdate());

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
	
	
}
