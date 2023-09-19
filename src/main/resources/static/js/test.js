function show() {
	document.getElementById('myModal').style.display = 'block';
}
let key = document.getElementById('searchUser');
key.addEventListener('keyup', function() {
	let find = this.value
	if (find != '') {
		//console.log(this.value) //테스트
		let xhr = new XMLHttpRequest();
		xhr.open('GET', '/search/' + find);
		xhr.send();
		xhr.onload = function() {
			if (xhr.status == 200 || xhr.status == 201) {
				let items = JSON.parse(xhr.response);
				console.log(items);
				let result = ''
				items.forEach(function(e) {
					result += `<button data-target="#myModal" onclick="getNick('${e.nickname}')">${e.nickname}</button><br>`;
					console.log(result);
				})
				document.getElementById('show').innerHTML = result;
			}
		}
	} else {
		document.getElementById('show').innerHTML = '';
	}
})
const now = new Date();
const nowYear = now.getFullYear();
const nowMonth = now.getMonth() + 1;
const nowDate = now.getDate();
const nowResult = nowYear + nowMonth + nowDate;

function getNick(nickname) {
	console.log(nickname);
	document.getElementById('target').innerHTML = nickname;
	document.getElementById('myModal').style.display = 'block';
	let xhr = new XMLHttpRequest();
	xhr.open('POST', '/getMsg/' + nickname);
	xhr.send();
	xhr.onload = function() {
		if (xhr.status == 200 || xhr.status == 201) {
			let items = JSON.parse(xhr.response);
			console.log("items = ", items)
			let result = '';
			let getTime = '';
			items.forEach(function(e) {
				let time = new Date(e.msgCreatedAt);
				const timeYear = time.getFullYear();
				const timeMonth = time.getMonth() + 1;
				const timeDate = time.getDate();
				const timeHours = (time.getHours() > 12 ? time.getHours() % 12 : time.getHours());
				const timeResult = timeYear + timeMonth + timeDate;
				let showtime = timeHours + " : " + time.getMinutes() + (time.getHours() >= 12 ? '오후' : '오전')
				if (getTime != timeResult) {
					result += `<div class="timestyle" style="clear:both;">${timeYear}년 ${timeMonth}월 ${timeDate}일</div>`;
					getTime = timeResult;
				}

				if (e.msgreceivernick == nickname) {
					result += '<div class="mydiv"><div class="myspan"><span>' + showtime + '</span></div><div class="my">'+e.msgContent+'</div></div>'
				} else {
					result += '<div class="otherdiv"><div class="other">'+e.msgContent+'</div><div class="otherspan"><span>' + showtime + '</span></div></div>'
				}
			})
			document.getElementById('DM').innerHTML = result;
			const container = document.getElementById('DM')
			console.log('=====' + container.scrollHeight)
			container.scrollTo(0, container.scrollHeight);
		}
	}
}

document.getElementById('close').addEventListener('click', function() {
	document.getElementById('myModal').style.display = 'none';
});

