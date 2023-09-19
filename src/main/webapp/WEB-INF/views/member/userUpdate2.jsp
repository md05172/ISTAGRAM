<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보수정</title>
</head>
<body>
   <jsp:include page="../imember.jsp"/>
   <jsp:include page="./set.jsp" />
   <div class="qwe">
         <!-- <input id="pw" type="password" placeholder="비밀번호 입력해주세요"> -->
         <br>
         <div>
            <p id=an >현재 Password</p>
            <input id="pw" type="password" placeholder="이전 비밀번호"><br>
         </div>
         <div>
            <p id=an>새 비밀번호 </p>
            <input id="pw1" type="password" placeholder="새 비밀번호"><br>
         </div>
         <div>
            <p id=an>새 비밀번호 확인</p>
            <input id="pw2" type="password" placeholder="새 비밀번호 확인"><br>
         </div>
   </div>
      <div class="asd">
   <br>
   <div>
         <button id="pwch">패스워드 변경</button>
   
   </div>

   </div>

</body>

<script type="text/javascript">
   const email = '${sessionScope.user.email}';
</script>
<script type="text/javascript" src="/resources/js/pwUpdate.js"></script>
</html>