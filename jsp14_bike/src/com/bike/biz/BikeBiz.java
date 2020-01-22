package com.bike.biz;

import java.util.List;

import com.bike.dto.BikeDto;

import com.bike.dao.BikeDao;

public class BikeBiz {
	
	private BikeDao dao = new BikeDao();

	
	public List<BikeDto> selectList(){
		
		return null;
	};

	public BikeDto selectOne(int content_id) {
		
		return null;
	};

	public int insert(BikeDto dto) {
		
		return 0;
	};

	public int update(BikeDto dto) {
		
		return 0;
	};

	public int delete(int content_id) {
		
		return 0;
	};
}
