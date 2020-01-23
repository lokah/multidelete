package com.bike.biz;

import java.util.List;

import com.bike.dto.BikeDto;

import com.bike.dao.BikeDao;

public class BikeBiz {
	
	private BikeDao dao = new BikeDao();

	
	public List<BikeDto> selectList(){
		
		return dao.selectList();
	};

	public BikeDto selectOne(int content_id) {
		
		return dao.selectOne(content_id);
	};

	public int insert(List<BikeDto> list) {
		
		return dao.insert(list);
	};

	public int update(BikeDto dto) {
		
		return 0;
	};

	public int delete() {
		
		return dao.delete();
	};
}
