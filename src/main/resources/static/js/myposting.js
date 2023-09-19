// 게시글 삭제 비동기
function delete_func(index) {
	
	// pno 값 확인하기
	console.log(index);
	
	// 게시글 삭제할지 확인
	const answer = window.confirm('게시글 삭제할겨?')
	if (!answer) {
		
		// 삭제안하면 함수 탈출
		return;
	} else {
		const xhr = new XMLHttpRequest();
		xhr.open('DELETE', '/delete/' + index)
		xhr.send()
		xhr.onload = function() {
			if (xhr.status === 200 || xhr.status === 201) {
				
				// 게시글이 삭제되면 확인
				alert('게시글이 삭제되었습니다.')
				
				// 삭제완료 후 다시 리스트 뿌려주기
				location.href = '/myposting?nickname=' + user
			} else {
				console.error('오류', xhr.status)
			}
		}
	}
}

$(".files").change(function(e) {
	let list = new Array();
	console.log('들어옴')
	let file = e.target.files[0];
	let filesArr = Array.prototype.slice.call(file);

	let img = $(this).find("img");
	console.log(img.attr("src"));
	console.log(img.val())
	
	filesArr.forEach(function(f) {
		list.push(f);
	})
	
	if (!file.type.match("image.*")) {
		img.attr("src", "${pageContext.request.contextPath}/img/noImage.png");
	} else {
		let reader = new FileReader();
		let input1 = document.getElementById('board_file1');
		let input = input1.files[0];
		let test =  $('input[id="board_file1"]').val();
		let src = test.substring(test.lastIndexOf("\\")+1);
		reader.onload = function(e) {
			img.attr("src", e.target.result)
		}
		reader.readAsDataURL(file);
		
	    let formData = new FormData(); //Form 객체 만들기
        formData.append('input', input); //이미지 넣기
		formData.append('user', user);
		
		let xhr = new XMLHttpRequest();
//		let data = {user: user, src: src};
		xhr.open('POST', '/changeImg');
		console.log(formData);
		xhr.send(formData);
		xhr.onload = function(){
			if(xhr.status == 201 || xhr.statux == 200){
				console.log(xhr.response);
			}
		}
	}

});
