// 댓글 더보기 누르면 pno값 받아오기
  let idNum=0;
  let count=100;
  const atag1 = document.querySelector('span[class="carousel-control-prev-icon"]')
  const atag2 = document.querySelector('span[class="carousel-control-next-icon"]')
  atag1.className = '';
  atag2.className = '';
      
document.querySelectorAll('button[class="btn btn-primary"]').forEach((btn) =>{
   
   
   btn.addEventListener('click', function() {
	   
	   const testt = $(this).parent();
	   const nickname = testt.children('input:eq(0)').val();	   
	   const hashtag = testt.children('input:eq(2)').val();	   
	   const content = testt.children('input:eq(3)').val();	   
	   
	   const bt = btn.nextElementSibling;
	   
      document.querySelector('#photo_zone').innerHTML='';
      // 버튼의 부모태그를 가져오기
      const copy = btn.parentNode;
      console.log(copy)
      // 노드 복사하기 (deep copy)
      const newNode = copy.cloneNode(true);

      // 복사된 Node id 변경하기
      idNum++;
      newNode.id = 'copyNode' + idNum;
      
      // 복사한 노드 붙여넣기
      newNode.after(copy);
      
      
      count++;
      newNode.id = 'demo'+count;
      
      // 사진이 1개일 경우를 대비해 if 사용을 위한 변수선언
      const ifitem = newNode.querySelector('div[class="carousel-item"]')
      
      // 사진이 1개일 경우 ifitem 요소는 없을거고 ifitem이 있는 경우만 실행
      if(ifitem!=null){      
         
      let test1 = newNode.querySelector('a[data-slide="prev"]')
      let test2 = newNode.querySelector('a[data-slide="next"]')
      
      test1.href="#demo"+count
      test2.href="#demo"+count
      
      }

      if(ifitem==null){
         let test1 = newNode.querySelector('a[data-slide="prev"]')
         let test2 = newNode.querySelector('a[data-slide="next"]')
         test1.remove();
         test2.remove();
      }
      
      commentList(bt.value);
     
      document.getElementById('writer').innerHTML = nickname;
      document.getElementById('pcontent').innerHTML = content;
      document.getElementById('phashtag').innerHTML = hashtag;
     
     //newNode.lastChild.img.style='margin-left:0px;'
     let imgitem = newNode.querySelector('.my_checkbox')
     imgitem.style="margin-left:0px; width:40px; height:40px;"
     
      // 카피한 댓글태그를 비우기
     newNode.querySelector('.container').innerHTML='';
      
      // 카피한 모달버튼태그 지우기
      newNode.querySelector('button[class="btn btn-primary"]').remove();
      
      // 카피완료한 태그를 모달안에 넣어주기
      document.querySelector('#photo_zone').appendChild(newNode);
   })
})


// 댓글 리스트 뿌리기 and 닉네임 동일할 경우 삭제버튼 추가 기능사용
function commentList(pnoNo) {
	const contentList = document.getElementById('contentList');
	contentList.innerHTML = '';
   console.log('commentList pno : '+pnoNo)
   const xhr = new XMLHttpRequest()
   xhr.open('GET', '/commentsList/' + pnoNo)
   xhr.send()
   xhr.onload = function() {
      if (xhr.status === 200 || xhr.status === 201) {
         const list = JSON.parse(xhr.response)
         console.log(xhr.response)
         for (let i = 0; i < list.length; i++) {
            console.log('list[i].cno : '+list[i].cno)
            if(list[i].nickname==user){
               console.log('list.cno : ' + list[i].cno)
            contentList.innerHTML +=
               '<span class="clSpan">' + list[i].nickname + '</span><br>' + '<span class="clSpan2">' + list[i].content + '</span><br>'
            }else{
            contentList.innerHTML +=
               '<span class="clSpan">' + list[i].nickname + '</span>' +'<br>' + '<span class="clSpan2">' + list[i].content + '</span><br>'
            }
         }
      } else {
         console.error('오류', xhr.status)
      }
   }
}



