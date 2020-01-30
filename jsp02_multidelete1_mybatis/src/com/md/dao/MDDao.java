package com.md.dao;

import com.md.dto.MDDto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

//import static com.md.db.JDBCTemplate.*;
public class MDDao extends SqlMapConfig {

	private String namespace = "muldel.";

	public List<MDDto> selectList() {

		List<MDDto> list = null;
		SqlSession session = null;

		try {
			session = getSqlSessionFactory().openSession();
			list = session.selectList(namespace + "selectList");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			session.close();

		}

		return list;
	}

	public int multiDelete(String[] seq) {

		int res = 0;

		SqlSession session = null;
		Map<String, String[]> map = new HashMap<String, String[]>();
		map.put("seqs", seq);
		try {
			session = getSqlSessionFactory().openSession(false);
			res = session.delete(namespace + "muldel", map);

			if (res == seq.length) {

				session.commit();
			}

		} catch (Exception e) {

			e.printStackTrace();
		} finally {

			session.close();
		}

		return res;
	}

	public MDDto selectOne(int seq) {

		SqlSession session = null;

		MDDto dto = new MDDto();
		try {
			session = getSqlSessionFactory().openSession(false);
			dto = session.selectOne(namespace + "selectOne", seq);

		} catch (Exception e) {

			e.printStackTrace();
		} finally {

			session.close();
		}

		return dto;
	}

	public int insert(MDDto dto) {

		// 3.query 준비

		int res = 0;
		SqlSession session = null;

		try {
			session = getSqlSessionFactory().openSession(false);
			res = session.insert(namespace + "insert", dto);
			if (res > 0) {

				session.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return res;
	}

	public int update(MDDto dto) {

		int res = 0;
		SqlSession session = null;

		try {
			session = getSqlSessionFactory().openSession(false);
			res = session.update(namespace + "update", dto);
			if (res > 0) {

				session.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return res;
	}

}
