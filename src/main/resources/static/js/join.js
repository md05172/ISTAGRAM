/*
프로젝트 팀명:ISTAGRAM(인스타그램 흉내)
작성자: 김종훈(팀장)
*/
//이메일과 비밀번호 유효성 검사
let regEmail = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/;
let regPassword =/^[a-zA-Z\\d`~!@#$%^&*()-_=+]{8,24}$/

let isValid = true;
document.querySelector("#email").addEventListener('keyup',function() {
	if(this.value == '') {
		document.querySelector('#idOk').innerHTML = '';
	} else {
		const email = document.querySelector('#email').value
		const xhr = new XMLHttpRequest() //비동기 통신 객체 생성
		xhr.open('GET', '/api/idCheck/' + email) //전송 보낼 준비 (url과 method)
		xhr.send() //요청 전송. body와 함께 보낼 때가 있습니다.
		xhr.onload = function() { //요청에 대한 응답받았을 때 이벤트 onload 핸들러 함수
			if (xhr.status === 200 || xhr.status === 201) {
				//json문자열 -> 자바스크립트 객체
				const jsobj = JSON.parse(xhr.response);
				console.log(jsobj)
				console.log(jsobj.count)

				isValidOk = jsobj.count //서버에 전달한 count값으로 isValidOk 저장
				if (isValidOk == 0) {
					document.querySelector('#idOk').innerHTML = '사용 가능한 아이디 입니다.'
					document.querySelector('#idOk').style.color = 'green'

				} else {
					document.querySelector('#idOk').innerHTML = '사용 중인 아이디 입니다.'
					document.querySelector('#idOk').style.color = 'red'
					isValid = false;
				}
			} else {
				console.error('오류', xhr.status)
			}
		}
	}
})

document.querySelector("#repw").addEventListener('keyup',function() {
	let repw = document.querySelector('#repw').value;
	console.log(repw);
	let pw = document.querySelector('#pw').value;
	console.log(pw);
	if(repw == '') {
		document.querySelector('#checkpw').innerHTML = '';			
	} else {
		if(repw == pw){
			document.querySelector('#checkpw').innerHTML = '비밀번호가 같습니다';
			document.querySelector('#checkpw').style.color = 'green';
		} else {
			document.querySelector('#checkpw').innerHTML = '비밀번호가 같냐??';
			document.querySelector('#checkpw').style.color = 'red';
			isValid = false;
		}
	}
})

function func_join() {
	let check = true;
    const frm = document.forms[0]
    const email = frm.email
    const name = frm.nickname
    const pw = frm.pw
	
   
   console.log(frm);
   console.log(email)
   console.log(name)
   console.log(frm.email.value)
   
     if(email.value=='') {
        alert('이메일은 필수 입력입니다.')
        email.focus()
        check=false
   //4)이메일은 기호 @ 와 . 을 포함해야 합니다.  .은 마지막 위치 아니어야 합니다.
   //  -> 잘못된 이메일형식체크
    } else
    if(name.value=='') {
        alert('닉네임은 필수 입력입니다.')
        name.focus()
        check=false
    } else
    
    
     if(email.value.indexOf('@')==-1 || email.value.indexOf('.') == -1 ||
     email.value.endsWith('.') ) {
      alert('이메일 형식 체크 실패입니다.')
      email.focus()
      check=false
   //5)
    //실제 이메일 주소는 형식이 위의 조건보다 복잡합니다. 계정문자열에 특수기호는 - _ . 만 포함. 
    //                  도메인이름에 기호는 사용못합니다. naver.com O na-ver.com X  333naver.com X(숫자로 시작)
    //복잡한 조건의 유효성검사를 정규식표현으로 할수 있습니다.
    //정규표현식은 전화번호,이메일,패스워드, 한글,영문 입력체크에 활용.
   } else
   
    if(regEmail.test(email.value)===false) {      //test메소드는 정규표현식과 입력문자열 비교
        alert('정규표현식 검사 이메일 형식이 아닙니다.')
      email.focus()
      check=false
    }else 
    
    if(pw.value.length < 4 ) {
        alert('패스워드는 4글자 이상입니다.')
        pw.focus()
        check=false
    }
    
     if(check) {   //유효성 통과하여 제출합니다. 
     	alert('회원가입 성공');
        frm.submit();
    }
}

document.querySelector("#nickname").addEventListener('keyup',function() {
   if(this.value == '') {
      document.querySelector('#nickOk').innerHTML = '';
   } else {
      const nick = document.querySelector('#nickname').value
      const xhr = new XMLHttpRequest() //비동기 통신 객체 생성
      xhr.open('GET', '/api/nickCheck/' + nick) //전송 보낼 준비 (url과 method)
      xhr.send() //요청 전송. body와 함께 보낼 때가 있습니다.
      xhr.onload = function() { //요청에 대한 응답받았을 때 이벤트 onload 핸들러 함수
         if (xhr.status === 200 || xhr.status === 201) {
            const jsobj = JSON.parse(xhr.response);
         
            
            if (jsobj == '0') {
               document.querySelector('#nickOk').innerHTML = '사용 가능한 닉네임 입니다.'
               document.querySelector('#nickOk').style.color = 'green'

            } else {
               document.querySelector('#nickOk').innerHTML = '사용 중인 닉네임 입니다.'
               document.querySelector('#nickOk').style.color = 'red'
               isValid = false;
            }
         } else {
            console.error('오류', xhr.status)
         }
      }
   }
})
