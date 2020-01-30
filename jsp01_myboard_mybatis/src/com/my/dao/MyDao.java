package com.my.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.my.dto.MyDto;

public class MyDao { // extends JDBCTemplate {

	public List<MyDto> selectList() {

		// 1.driver 연결
		// 
		//
		
		String resource = "com/my/db/mybatis-config.xml";
		InputStream inputStream = null;
		try {
			inputStream = Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		
		SqlSession session = sqlSessionFactory.openSession();
		List<MyDto> list = session.selectList("com.my.mapper.selectList");
		session.close();
		
		
		return list;
	}

	public MyDto selectOne(int myno) {

		// 1.driver 연결
		
		String resource = "com/my/db/mybatis-config.xml";
		InputStream inputStream = null;
		try {
			inputStream = Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SqlSessionFactory sqlSessionFactory  = new SqlSessionFactoryBuilder().build(inputStream);
		
		SqlSession session = sqlSessionFactory.openSession();
		MyDto dto = session.selectOne("com.my.mapper.selectOne", myno);
		session.close();
		
		
		return dto;
	}

	public int insert(MyDto dto) {
		// 1.driver 연결
		
		String resource = "com/my/db/mybatis-config.xml";
		InputStream inputStream = null;
		try {
			inputStream = Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SqlSessionFactory sqlSessionFactory  = new SqlSessionFactoryBuilder().build(inputStream);
		
		SqlSession session = sqlSessionFactory.openSession();
		
			int res = session.insert("com.my.mapper.insert", dto);
			
			if(res>0) {
		session.commit();
			}
		session.close();
		
		
		
		
		return res;
	}

	public int update(MyDto dto) {

		String resource = "com/my/db/mybatis-config.xml";
		InputStream inputStream = null;
		try {
			inputStream = Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SqlSessionFactory sqlSessionFactory  = new SqlSessionFactoryBuilder().build(inputStream);
		
		SqlSession session = sqlSessionFactory.openSession(true);
		
		int res = session.update("com.my.mapper.update", dto);
		
				
			session.close();
			
		return res;
	}

	public int delete(int myno) {

		String resource = "com/my/db/mybatis-config.xml";
		InputStream inputStream = null;
		try {
			inputStream = Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SqlSessionFactory sqlSessionFactory  = new SqlSessionFactoryBuilder().build(inputStream);
		
		SqlSession session = sqlSessionFactory.openSession();
		
		int res = session.delete("com.my.mapper.delete", myno);
		
		if(res>0) {
	session.commit();
		}
	session.close();
		return res;
	}

}
