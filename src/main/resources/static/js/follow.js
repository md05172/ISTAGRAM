/*
프로젝트 팀명:ISTAGRAM(인스타그램 흉내)
작성자: 김종훈(팀장)
*/
let follow
console.log("target = " + target)
function socket_follow() {
	follow = new WebSocket("ws://localhost:8085/ws/follow");
	
	follow.onopen = on_open;
	follow.onclose = on_close;
	follow.onmessage = on_message;
	
	function on_open(){
		console.log('follow 연결')
	}
	
	function on_close(){
		console.log('follow 끊김')
	}
	
	function on_message(data){
		console.log("follow.js")
		console.log(data.data);
	}
}

if (user != null) socket_follow();

function sendfollow(){
	let data = {};
	console.log('target = ' + target);
	console.log('sender = ' + user);
	data.wreceiver = target;
	data.wsender = user;
	let temp = JSON.stringify(data);	
	follow.send(temp);
	console.log(temp);
}

function followbutton(nickname){
	console.log("dma");
	let xhr = new XMLHttpRequest();
	xhr.open('POST', '/follow/'+nickname);
	xhr.send();
	xhr.onload = function (){
		if(xhr.status == 200 || xhr.status == 201){
			console.log(xhr.response);
			let items = JSON.parse(xhr.response);
			let result = '';
			items.forEach(function(e){
				result += `<div class="follwdiv" style="clear: both">${e.wreceiver}<button onclick="followdelete('${e.wreceiver}')" class="followdeletebutton">삭제</button><div>`;
			})
			document.getElementById('title').innerHTML = '팔로우';
			document.getElementById('showfollow').innerHTML = result;
		}
	}
}

function followerbutton(nickname){
	let xhr = new XMLHttpRequest();
	xhr.open('POST', '/follower/'+nickname);
	xhr.send();
	xhr.onload = function (){
		if(xhr.status == 200 || xhr.status == 201){
			console.log(xhr.response);
			let items = JSON.parse(xhr.response);
			let result = '';
			items.forEach(function(e){
				result += `<div class="follwdiv" style="clear: both">${e.wsender}<div>`;
			})
			document.getElementById('title').innerHTML = '팔로워';
			document.getElementById('showfollow').innerHTML = result;
		}
	}
}

function followdelete(name){
	
	let xhr = new XMLHttpRequest();
	
	let data = {};
	data.wsender = user;
	data.wreceiver = name;
	let temp = JSON.stringify(data);
	xhr.open('POST', '/followdelete');
	xhr.send(temp);
	xhr.onload = function (){
		if(xhr.status == 200 || xhr.status == 201){
			console.log(xhr.response);
			let items = JSON.parse(xhr.response);
			let result = '';
			items.forEach(function(e){
				result += `<div class="follwdiv" style="clear: both">${e.wreceiver}<button onclick="followdelete('${e.wreceiver}')" class="followdeletebutton">삭제</button><div>`;
			})
			document.getElementById('title').innerHTML = '팔로우';
			document.getElementById('showfollow').innerHTML = result;
			location.href = "/myposting";
		}
	}
}

