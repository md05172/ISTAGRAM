/*
프로젝트 팀명:ISTAGRAM(인스타그램 흉내)
작성자: 김종훈(팀장)
*/
//회원 검색
const inputEl = document.querySelector('#find');

 inputEl.addEventListener('keyup',function(){ 
		const find = inputEl.value
		document.querySelector('#search').innerHTML=''
		if(find==''){
			document.querySelector('#searchfind').innerHTML=''
			inputEl.placeholder='검색어를 입력해주세요.'
			
			return
		}
		
		const xhr = new XMLHttpRequest()
		xhr.open('GET','/search/'+find)
		xhr.send()
		xhr.onload=function(){
			if(xhr.status === 200 || xhr.status===201){
				console.log('xhr.response : '+xhr.response)
				const jsonObj = JSON.parse(xhr.response);
				let nick='';
				let items='';
				for(let i=0; i<jsonObj.length;i++){
					nick = jsonObj[i].nickname
					items += '<p><a href="/myposting?nickname='+nick+'">'+jsonObj[i].nickname+'<br>'+jsonObj[i].info+'<br></a><hr>'
				}
				console.log('items = '+items[0])
				document.querySelector('#searchfind').innerHTML=items
				
			}else
				console.error('오류',xhr.status)
			
}
})