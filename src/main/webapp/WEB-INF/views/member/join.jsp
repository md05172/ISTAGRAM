<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>


<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Instagram</title>
<link rel="stylesheet" href="resources/css/join.css">
<link rel="stylesheet"
	href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"
	integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p"
	crossorigin="anonymous" />


</head>

<body>
	<div class="container">
		<main class="loginMain">
			<!--회원가입섹션-->
			<section class="login">
				<article class="login__form__container">

					<!--회원가입 폼-->
					<div class="login__form">
						<!--로고-->
						<h1>
							<img src="resources/images/logo.jpg" alt="">
						</h1>
						<!--로고end-->

						<!--회원가입 인풋-->
						<form class="login__input" action="/join" method="post">
							<input type="text" name="email" id="email" placeholder="이메일입력"
								maxlength="30" /><label id="idOk"></label><br> 
								 <input type="password" name="pw" id="pw"
								placeholder="패스워드" required="required" />
								<input type="password" name="repw" id="repw"
								placeholder="패스워드확인" required="required" />
								<span id="checkpw"></span>
								<input type="text" id="nickname" name="nickname" placeholder="닉네임" required="required" />
								 <label id="nickOk"></label>
							<button id="btn_save" class="btn btn-primary" type="button"
								onclick="func_join()">가입하기</button>
						</form>
						<!--회원가입 인풋end-->
					</div>
					<!--회원가입 폼end-->

					<!--계정이 있으신가요?-->
					<div class="login__register">
						<span>계정이 있으신가요?</span> <a href="/login">로그인</a>
					</div>
					<!--계정이 있으신가요?end-->

				</article>
			</section>
		</main>
	</div>
<script type="text/javascript" src="resources/js/join.js"></script>
</body>

</html>