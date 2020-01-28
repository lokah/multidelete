package com.cal.dao;

public class Util {

	
	//한자리수를 두자리수로 바꾸기
	public static String isTwo(String msg) {
		
		
		
		return (msg.length()<2)?"0"+msg:msg;
	}
	
}
