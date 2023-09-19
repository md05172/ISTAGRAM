/*
프로젝트 팀명:ISTAGRAM(인스타그램 흉내)
작성자: 김종훈(팀장)
*/
$('button[class="btn btn-default"]').on('click', function(e) {
	console.log(e);
	console.log("들어옴");
	let content = $(this).prev().val();
	console.log(content);
	let pno = $(this).next().show().val();
	console.log($(this).next().show().val());
	let name = nickname;
	console.log(pno);
	
	let temp = {};	//js -> {} = 오브젝트
					//js -> [] = 배열
					
	temp.content = content;
	temp.pno = pno;
	temp.nickname = name;
		let output = '';
	
	//비동기통신을 위한 셋팅	
	//댓글 작성
	$.ajax({	//ajax선언
		url: '/comment/insert',	//보낼 url (controller에서 받을 url과 똑같아야댐)
		type: 'post',	//타입은 알아서
		contentType: 'application/json',
		data: JSON.stringify(temp),	//보낼 데이터 controller에서 String으로 받는거면 Json으로 바꾸고
							//객체로 받을거면 객체타입으로 보내고
		success: function(data) {	//controller에서부터 값을 받아오면 실행
		
		let db2 = JSON.parse(data)
		
		output = '';
		for(let i=0; i<db2.length; i++){
			let time = new Date(db2[i].wdate);
			const hour = time.getHours() > 12 ? '0' + time.getHours() % 12 : time.getHours();
			const minute = time.getMinutes();
			if(i == 0) {
				output += `작성자 ${db2[i].nickname}   ${db2[i].content}  ${hour}: ${minute}`;
			}
		}
		
		
		document.getElementById('content').value = '';
		document.querySelector('#conn'+pno).innerHTML = output;
		
//		output += "<table>";
//		output += "<tr>";
//		output += "<th>작성자</th>";
//		output += "<th>내용</th>";
//		output += "<th>작성일</th>";
//		output += "</tr>";
//			
//		for(let i = 0; i< db2.length; i++) {
//		output += "<tr>";
//		output += "<td>" + db2[i].nickname + "</td>";
//		output += "<td>" + db2[i].content + "</td>";
//		
//		
//		output += "<td>" + hour + ' : ' + minute + "</td>";

			
			
/*	for (let i = 0; i < db2.length; i++) {
      output += console.log(db2[i].nickname );
      output += console.log(db2[i].content );
      output += console.log(db2[i].wdate );
    }*/
			
		},
		
		error: function(){	//비동기통신이 실패했을때 실행
			console.log('에러')
		}
	});//ajax끝
	


});