/*
프로젝트 팀명:ISTAGRAM(인스타그램 흉내)
작성자: 김종훈(팀장)
*/
const jsonStr = document.querySelector('#json')

document.querySelector('#getOne').addEventListener('click', function() {
	const email = document.querySelector('#email').value
	if (email == "") {
		alert('이메일 입력은 필수입니다.')
		return
	}else{
		alert('이메일 조회 완료.')
	}
	
	const xhr = new XMLHttpRequest()		//비동기 통신 객체 생성
	xhr.open('GET','/userUpdate/'+email)			//전송 보낼 준비 (url과 method)
	xhr.send()								//요청 전송. body와 함께 보낼 때가 있습니다.
	xhr.onload=function(){					//요청에 대한 응답받았을 때 이벤트 onload 핸들러 함수
		if(xhr.status === 200 || xhr.status ===201){
           	const jsonObj = JSON.parse(xhr.response);
			const isOk = jsonObj.isOk
			//변환된 자바스크립트 객체로 input 에 값 출력시키기
			 if(isOk=='fail') {
                    	alert('조회된 회원이 없습니다.!')
             }else{
				const imember = jsonObj.imember
                document.getElementById('email').value = imember.email;
                document.getElementById('nickname').value = imember.nickname;
                document.getElementById('pw').value = imember.pw;
                document.getElementById('info').value = imember.info;
              }
                    			
                    			
		}else {
			console.error('오류',xhr.status)
		}
	}
})



document.querySelector('#update').addEventListener('click',function(){
		const email = document.querySelector('#email').value
		const nickname = document.querySelector('#nickname').value
		const info = document.querySelector('#info').value
		const pw1 = document.querySelector('#pw1').value                 
		const pw2 = document.querySelector('#pw2').value                 
		if(pw1!=pw2){
			alert('다시 입력해주세요')
			document.querySelector('#pw1').focus()
			return
		}
		//자바스크립트 객체	
		const jObj={"email":email,
			"nickname":nickname,
			"pw":pw1,
			"info":info}
		const xhr = new XMLHttpRequest();
		xhr.open('PUT','/userUpdate/'+email)
		xhr.setRequestHeader('content-type', 'application/json;charset=utf-8');	//body에 형식을 갖는 header
		const data = JSON.stringify(jObj)				//자바객체를 문자열로 직렬화한 것.
		xhr.send(data)
		xhr.onload=function(){					//요청에 대한 응답받았을 때 이벤트 onload 핸들러 함수
		if(xhr.status === 200 || xhr.status ===201){
				alert('회원정보 수정완료.')
				location.href='/'
		}else {
			console.error('오류',xhr.status)
		}
	}
})