/*
프로젝트 팀명:ISTAGRAM(인스타그램 흉내)
작성자: 김종훈(팀장)
*/

'use strict'
let websocket

//DM (1:1채팅)이 왔을때 몇개가 왔는지 표시해줌
function msgCount() {
	let data = {};
	data.msgreceivernick = id;
	let temp = JSON.stringify(data);
	let xhr = new XMLHttpRequest();
	// ImemberRestController.java ↓
	xhr.open('POST', '/getCnt');
	xhr.send(temp);
	let cnt = 0;
	xhr.onload = function() {
		if (xhr.status == 201 || xhr.status == 200) {
			cnt = xhr.response;
			console.log("msgCount() = " + cnt);
			if (cnt != 0) {
				document.getElementById('msgCnt').innerHTML = cnt;
			}
		}
	}
}

//DM(1:1채팅) 개수 초기화
function clickDM() {
	let xhr = new XMLHttpRequest();
	let data = {};
	data.msgreceivernick = id;
	let temp = JSON.stringify(data);
	// ImemberRestController.java ↓
	xhr.open('POST', '/updateMsg');
	xhr.send(temp);
	xhr.onload = function() {
		if (xhr.status == 201 || xhr.status == 200) {
			console.log(xhr.response);
			let result = JSON.parse(xhr.response);
			if (result.scnt == 0) {
				//DM (1:1채팅)을 누르면 지금까지온 문자 갯수가 사라진다
				document.getElementById('msgCnt').style.display = 'none';
			}
			location.href = "/chat";
		}

	}
}

//채팅을 위한 소켓 설정
function socket_func() {
	websocket = new WebSocket("ws://localhost:8085/ws/chatt");	//소켓통신 url을 인자로 전달
	//객체가 생성되면서 통신을 시작함
	websocket.onopen = on_open;	//연결될때
	websocket.onclose = on_close;	//연결 끊겼을때
	websocket.onmessage = on_message;	//응답받았을때

	//웹소켓이 시작됐을때 처음 한번만 실행하는 메소드
	function on_open() {
		console.log("소켓 통신이 열림");
		if (id != null)
			websocket.send("open/" + id)
	}

	//연결이 끊겼을때 실행하는 메소드
	function on_close() {
		console.log("소켓 통신이 닫힘");
	}

	//데이터를 받으면 실행되는 메소드
	function on_message(message) {
		msgCount()
		console.log("result = " + message.data)
		let result = JSON.parse(message.data);
		if (result.msgreceivernick == id) {
			console.log('result.sendNick = ' + result.msgsendnick)
			console.log('result.msgreceivernick = ' + result.msgreceivernick)
			let cnt = msgCount(result.msgsendnick);
			console.log('cnt = ' + cnt);
		}
		let view = document.getElementById('DM').innerHTML;
		console.log("받은 데이터 = " + message.data);
		console.log("result = " + result);
		console.log("result = " + result.msgContent);
		console.log("result.sendNick == id" + result.sendNick == id);
		let time = new Date();
		let hour = time.getHours();
		let minutes = time.getMinutes();
		if (result.sendNick == id) {
			view += `<div><p class="my">${result.msgContent}</P></div><div style="clear:both"></div>`;
		} else {
			view += `<div class="ot"><div class="otin"><p class="other">${result.msgContent}</P></div><div class="otin"><span>${hour > 12 ? hour % 12 : hour} : ${minutes<10? '0:'+minutes : minutes} ${hour >= 12 ? '오후' : '오전'}</span></div></div><div style="clear:both"></div>`;
		}
		document.getElementById("DM").innerHTML = view;
		const container = document.getElementById('DM')
		console.log('=====' + container.scrollHeight)
		container.scrollTo(0, container.scrollHeight);
	}
}
if (id != null) socket_func();

function logout() {
	websocket.close();
	location.href = "/logout";
}

//DM(1:1채팅) 보내기
function send() {
	let data = {};
	let ms = document.getElementById("DM").innerHTML;
	let tg = document.getElementById("target").innerHTML;
	console.log("tg = " + tg)
	let msg = document.getElementById("chatMsg").value;
	data.msgsendnick = id;
	data.msgContent = msg;
	data.msgreceivernick = tg;
	let temp = JSON.stringify(data);
	console.log(temp);
	let time = new Date();
	let hour = time.getHours();
	let minutes = time.getMinutes();

	ms += `<div class="mysend"><span>${hour > 12 ? hour % 12 : hour} : ${minutes<10? '0:'+minutes : minutes} ${hour >= 12 ? '오후' : '오전'}</span><p class="my">${msg}</P></div><div style="clear:both"></div>`;
	//본인 채팅은 오른쪽에 표시된다
	document.getElementById("DM").innerHTML = ms;
	websocket.send('msg/' + temp);
	//채팅을 보내고 나면 입력칸 빈칸처리
	document.getElementById("chatMsg").value = '';
	//채팅 내용이 많아질 시 스크롤처리
	const container = document.getElementById('DM')
	console.log('=====' + container.scrollHeight)
	container.scrollTo(0, container.scrollHeight);
}
msgCount();

