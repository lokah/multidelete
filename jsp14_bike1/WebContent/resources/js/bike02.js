$(function(){
	
	parseJsonTest();
	
	
});


function parseJsonTest(){
	
	
	//(1) $.getJSON(url,callback)

	//첫번째 매개변수로 JSON 파일을 로드한다. 

	//두번째 매개변수(콜백함수)에서 JSON 파일을 이용하여 로드된 데이터를 처리한다. 
	//콜백함수는 로드된 데이터를 인자로 넘겨받는다.(JSON 데이터를 참조하기 위해 data 변수를 사용하고 있다.)
	$.getJSON("resources/json/bike.json", function(data){
		
		$.ajax({
			
			url:"bike.do?command=second_db",
			method:"post",
			data:{"obj":JSON.stringify(data)},//stringify 메소드는 JSON 객체를 String 객체로 변환시켜준다.
			success:function(mag){
				if(mag==1163){
					
					//table에 표시하자
					$.each(data,function(key,val){
						
						//스트링 객체를 첫번째 매개 변수로 받고, 두번째 매개변수인 콜백함수에서도 key와 val을 인자로 받아 처리한다.
						//두번 반복
						if(key=="DESCRIPTION"){
							
							$("table").attr("border", "1");
							$("thead").append(
									
							"<tr>"+"<th>"+val.ADDR_GU+"</th>"+
							"<th>"+val.CONTENT_ID+"</th>"+
							"<th>"+val.CONTENT_NM+"</th>"+
							"<th>"+val.NEW_ADDR+"</th>"+
							"<th>"+val.CRADLE_COUNT+"</th>"+
							"<th>"+val.LONGITUDE+"</th>"+
							"<th>"+val.LATITUDE+"</th>"+
							"</tr>");
						}else if(key=="DATA"){
							var list = val;
							for(var i=0;i<list.length;i++){//val.length해도 된다.
								
								var str = list[i];
								$("tbody").append(
								"<tr>"+
								"<td>"+str.addr_gu+"</td>"+
								"<td>"+str.content_id+"</td>"+
								"<td>"+str.content_nm+"</td>"+
								"<td>"+str.new_addr+"</td>"+
								"<td>"+str.cradle_count+"</td>"+
								"<td>"+str.longitude+"</td>"+
								"<td>"+str.latitude+"</td>"+
								"<input type='hidden' name='bike' value='"+str.addr_gu+"/"+
								str.content_id+"/"+
								str.content_nm+"/"+
								str.new_addr+"/"+
								str.cradle_count+"/"+
								str.longitude+"/"+
								str.latitude+"'>"+
								"</tr>"
								
								
								
								)
							}
						}
						
					});
				}else{
					alert("저장 실패");
				}
				
				
			},
			error:function(){
				
				alert("통신 실패");
				
			}
			
			
		})
		
	})
}