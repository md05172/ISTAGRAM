<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script
	src="https://cdn.jsdelivr.net/npm/jquery@3.6.3/dist/jquery.slim.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet" href="resources/css/chat.css">
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<title>DM</title>
</head>
<body>
	<%@ include file="imember.jsp" %>
	<div id="main" style="margin-left: 500px;">
		<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#searchmyModal">
    메세지보내기
  </button>

  <!-- The Modal -->
  <div class="modal" id="searchmyModal">
    <div class="modal-dialog" style="margin-top: 10em; color: white;" >
      <div class="modal-content"style="background-color: #262626;">
      
        <!-- Modal Header -->
        <div class="modal-header">
          <h4 class="modal-title">검색:<input type="text" id="searchUser" style="color: black"></h4>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        
        <!-- Modal body -->
        <div class="modal-body" style="height: 365px;">
          <div id="show"></div>
        </div>
        
        <!-- Modal footer -->
        <div class="modal-footer">
          <button type="button" class="btn btn-danger" data-dismiss="modal" style="background-color: white; color: black; border: none;">
          Close</button>
        </div>
        
      </div>
    </div>
  </div>
		
		
		<a href="login">로그인</a> 나: ${user.nickname } <br> 
		
		
		<br><br><br><br><br><br>
		<c:set var="nick" value="${user.nickname}" />
		<c:forEach items="${roomList }" var="i" varStatus="st">
			<c:if test="${nick eq i.user1}">
				<button type="button" class="btn btn-primary" data-target="#myModal" data-toggle="modal" onclick="getNick('${i.user2}')">${i.user2 }</button><span></span>
				<br>
			</c:if>
			<c:if test="${i.user1 ne nick }">
				<button type="button" class="btn btn-primary" data-target="#myModal" data-toggle="modal" onclick="getNick('${i.user1}')">${i.user1 }</button><span></span>
				<br>
			</c:if>
		</c:forEach>


		<!-- The Modal -->
		<div class="modal" id="myModal" data-toggle="modal" style="display: none; focus">
			<div class="modal-dialog modal-xl" id="he" style="width: 37em;">
				<div class="modal-content" style="background-color: #262626;">

					<!-- Modal Header -->
					<div class="modal-header" style="color: white">
						<h4 class="modal-title" id="target" class="name"></h4>
						<button type="button" id="close" class="close"
							data-dismiss="modal">&times;</button>
					</div>

					<!-- Modal body -->
					<div id="DM" class="modal-body" style="height: 625px;color: white">
					</div>
					
					<!-- Modal footer -->
					<div class="modal-footer">

						<div class="input-group mb-3">

							<input type="text" id="chatMsg"
								class="border border-dark form-control-plaintext rounded"
								style="color: white;" placeholder="메시지입력" onkeyup="if(window.event.keyCode==13){send()}">&nbsp;
							<button type="button" class="btn btn-dark" onclick="send()">Send</button>
						</div>
					</div>

				</div>
			</div>
		</div>
	</div>
<script type="text/javascript">
	const id = "${sessionScope.user.nickname }"
	const list = "${roomList}"
	
</script>
<script type="text/javascript" src="resources/js/test.js"></script>
<script type="text/javascript" src="resources/js/chatt.js"></script>
</body>
</html>