//const inputEll = document.querySelector('#notifi');
//
// inputEll.addEventListener('keyup',function(){ 
//      const notifi = inputEll.value
//      document.querySelector('#noti').innerHTML=''
//      if(notifi==''){
//         document.querySelector('#notifind').innerHTML=''
//         inputEl.placeholder='검색어를 입력해주세요.'
//         
//         return
//      }
//      
//      const xhr = new XMLHttpRequest()
//      xhr.open('GET','/noti/'+notifi)
//      xhr.send()
//      xhr.onload=function(){
//         if(xhr.status === 200 || xhr.status===201){
//            console.log('xhr.response : '+xhr.response)
//            const jsonObj = JSON.parse(xhr.response);
//            let nick='';
//            let items='';
//            for(let i=0; i<jsonObj.length;i++){
//               nick = jsonObj[i].nickname
//               items += '<p><a href="/myposting?nickname='+nick+'">'+jsonObj[i].nickname+'<br>'+jsonObj[i].info+'<br></a><hr>'
//            }
//            console.log('items = '+items[0])
//            document.querySelector('#notifind').innerHTML=items
//            
//         }else
//            console.error('오류',xhr.status)
//         
//}
//})