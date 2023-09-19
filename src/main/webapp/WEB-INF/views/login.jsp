<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<jsp:include page="nav.jsp"/>
<body>
<form action="login" method="post">
Login id: 
<input name="email" type="text" placeholder="이메일 입력">
Login pw:
<input name="pw" type="password" placeholder="패스워드 입력">
<button type="submit">로그인</button>
</form>
</body>
</html>