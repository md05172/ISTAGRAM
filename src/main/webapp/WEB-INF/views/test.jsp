<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>test</title>
</head>
<body>
	<form action="login">
		id = <input type="text" name="id">
		<button>로그인</button>
	</form>
</body>
<script type="text/javascript">const id = "${sessionScope.id}"</script>
<script src='resources/js/chatt.js' type="text/javascript"></script>
</html>