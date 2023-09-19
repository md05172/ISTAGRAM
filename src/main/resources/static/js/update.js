/*
프로젝트 팀명:ISTAGRAM(인스타그램 흉내)
작성자: 김종훈(팀장)
*/
//회원정보 수정
document.querySelector('#update').addEventListener('click',function(){
      const email = document.querySelector('#email')
      const nickname = document.querySelector('#nickname')
      const info = document.querySelector('#info')
    
      //자바스크립트 객체   
      const jObj={"email":email.value,
         "nickname":nickname.value,
         "info":info.value}
      const xhr = new XMLHttpRequest();
      xhr.open('PUT','/userUpdate/'+email)
      
      xhr.setRequestHeader('content-type', 'application/json;charset=utf-8');   //body에 형식을 갖는 header
      
      const data = JSON.stringify(jObj)            //자바객체를 문자열로 직렬화한 것.
      xhr.send(data)
      xhr.onload=function(){               //요청에 대한 응답받았을 때 이벤트 onload 핸들러 함수
      if(xhr.status === 200 || xhr.status ===201){
            alert('회원정보 수정완료.')
            
      }else {
         console.error('오류',xhr.status)
      }
   }
})
