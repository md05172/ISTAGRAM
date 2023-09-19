/*
프로젝트 팀명:ISTAGRAM(인스타그램 흉내)
작성자: 김종훈(팀장)
*/
function fCnt(){
	let xhr = new XMLHttpRequest();
	xhr.open('POST', '/getfcnt/' + nickname);
	xhr.send();
	xhr.onload = function(){
		console.log(xhr.response);
		let fitem = JSON.parse(xhr.response);
		if(fitem.length != 0){
			document.getElementById('fCnt').innerHTML = fitem.length;
		}
	}
}

function fCntClear(){
	let xhr = new XMLHttpRequest();
	xhr.open('POST', '/fCntClear/' + nickname);
	xhr.send();
	xhr.onload = function(){
		console.log(xhr.response);
		let fitem = JSON.parse(xhr.response);
		if(fitem.length != 0){
		document.getElementById('fCnt').style.display = 'none';
		let result = "";
		fitem.forEach(function(e){
			result += `<div class="alarmDiv"><p class="alarmP">${e.msg}</p></div>`;
		})
		document.getElementById('notifind').innerHTML = result;
		}
	}
}

let ws;
function socket_follow() {
	ws = new WebSocket("ws://localhost:8085/ws/follow");
	
	ws.onopen = on_open;
	ws.onclose = on_close;
	ws.onmessage = on_message;
	
	function on_open(){
		console.log('follow 연결했으요~')
	}
	
	function on_close(){
		console.log('follow 끊김')
	}
	
	function on_message(data){
		console.log("followconnect.js")
		console.log(data.data);
	}
}

if(nickname != null) {
	socket_follow();
	fCnt();
}