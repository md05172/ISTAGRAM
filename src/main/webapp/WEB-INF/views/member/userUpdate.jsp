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
   <div class="qwe">
   <div >
      <jsp:include page="./set.jsp" />
   </div>
   <div  >
         <!-- <input id="pw" type="password" placeholder="비밀번호 입력해주세요"> -->
         <br>
            <p id=an>이메일</p>
         <div class="qwee">
            <input id="email" placeholder="이메일 email" value="${sessionScope.user.email }"><br>
         </div>
         <div>
            <p id=an> Nickname</p>
            <input id="nickname" placeholder="이름 Nickname" value="${sessionScope.user.nickname }"><br>
         </div>
         
         <div>
            <p id=an>Info</p>
            <input id="info" placeholder="내소개" value="${sessionScope.user.info }"><br> <label
               id="json"></label>
         </div>
   </div>
   </div>
   <div class="asd">
   <br>
   <div>
      <button id="update">회원 정보수정</button>
   </div>

   </div>
</body>
<script type="text/javascript" src="/resources/js/update.js"></script>
</html>