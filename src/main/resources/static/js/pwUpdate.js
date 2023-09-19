/*
프로젝트 팀명:ISTAGRAM(인스타그램 흉내)
작성자: 김종훈(팀장)
*/
document.getElementById('pwch').addEventListener('click', function() {

   // 요소 이름설정
   const chpw0 = document.querySelector('#pw')
   const chpw1 = document.querySelector('#pw1')
   const chpw2 = document.querySelector('#pw2')

   // 변경할 값 동일한지 확인
   if (chpw1.value != chpw2.value) {
      alert('패스워드다름')
      return;
   } else {
      if (chpw1.value === '') {
         alert('비밀번호를 입력하세요');
         document.querySelector('#pw1').focus();
         return;
      } if (chpw1.value.length < 8) {
         alert('비밀번호 글자는 8자 이상이어야 합니다.');
         document.querySelector('#pw1').focus();
         return;
      }
      // 데이터 보낼 값 저장
      const pw = document.querySelector('#pw2').value
      console.log('pw : ' + pw)

      // 객체 data 보낼준비
      const jObj = { "pw": pw, "email": email }

      const xhr = new XMLHttpRequest();

      // 메소드 호출
      xhr.open('PUT', '/update/' + email);

      // 직렬화
      const data = JSON.stringify(jObj)
      // data 전송
      xhr.send(data)
      xhr.onload = function() {
         if (xhr.status === 200 || xhr.status === 201) {
            // 성공시 alert 창 띄우고 값 비우기
            alert('회원정보 수정완료.')
            chpw0.value = '';
            chpw1.value = '';
            chpw2.value = '';
         } else {
            console.error('오류', xhr.status)
         }
      }
   }
})