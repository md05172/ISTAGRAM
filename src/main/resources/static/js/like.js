document.querySelectorAll('button[class="my_checked"]').forEach(a=>{
   
   // pno value 를 찾기위한 요소 확인
   const i = a.previousElementSibling
   
   // 요소에 담긴 value 값을 저장
   const pno = i.value
   
   // session 에 저장된 닉네임 호출
   const nickname = user;
   
   // img 태그요소 지정값 저장
   const img = document.querySelector('img[class="my_img'+pno+'"]')
   
   // 클릭시 사용할 함수
   a.addEventListener('click',function(){
      
   
   // 버튼의 value 가 '' 값일 경우 (초기값)
   if(a.value==''){
      const jObj = { "nickname": nickname, "pno": pno }

      const xhr = new XMLHttpRequest()
      xhr.open('POST', '/insert/likes')
      const jStr = JSON.stringify(jObj)
      xhr.send(jStr)
      xhr.onload = function() {
         if (xhr.status === 200 || xhr.status === 201) {
         } else {
            console.error('오류', xhr.status)
         }
      }
      // img src 를 새로 설정
      img.setAttribute('src','/resources/img/heart.png');
      
      // 버튼에 value값을 넣어서 고정시키기
      a.value='active';
   }else{
      // 버튼의 value 가 active 일 경우(변경된 값)
      const dxhr = new XMLHttpRequest();
      
      // delete 쿼리문 사용시 2개의 값을 받기위해 연결
      dxhr.open('DELETE','/delete/likes/'+nickname+'/'+pno);
      dxhr.send();
      dxhr.onload = function(){
         if(dxhr.status ===200 || dxhr.status ===201){
         }else{
            console.error('오류',dxhr.status)
         }
      }
      // img 태그 src 새로 설정(변경 전 이미지로)
      img.setAttribute('src','/resources/img/beanheart.png');
      
      // 버튼의 value 를 빈값으로 다시 기본값 설정
      a.value='';
   }
      
   })
   
})