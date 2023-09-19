<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내가 쓴 게시글</title>
<link rel="stylesheet" type="text/css" href="/resources/css/nav.css">
<link rel="stylesheet" type="text/css"
	href="/resources/css/myposting.css">
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.3/dist/jquery.slim.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<style type="text/css">
.photo_box * {
	color: white;
}
</style>
</head>

<body>
	<jsp:include page="imember.jsp" />
	<main>
		<c:if test="${user.email==null }">
			<script type="text/javascript">
				alert('로그인 후 이용해주세요')
				location.href = "/login";
			</script>
		</c:if>

		<div id="top">
			<div id="image">
				<div class="files">
						<div>
							<label for="board_file1" style="display: inline;"> <img
								id="board_file1Img"
								src="resources/images/${me.mphotofiles }"
								width="110px" height="110px" style="display: inline;">
							</label>
						</div>
						<input id="board_file1" style="display: none;" name="board_file1" type="file">

					</div>
			</div>
			<div id="option">
				<div id="first">
					<div id="nickname2">${me.nickname }</div>
					<div style="width: 310px;">
						<c:if test="${me.nickname eq user.nickname }">
							<a href="/userUpdate"><button id="infobutton" >편집</button></a>
						</c:if>
						<c:if test="${me.nickname ne user.nickname and follow.wsender ne user.nickname}">
							<button id="infobutton" onclick="sendfollow()">팔로우</button>
						</c:if>
						<c:if test="${follow.wsender eq user.nickname}">
							<button id="infobutton" onclick="">팔로우취소</button>
						</c:if>
					</div>
				</div>
				<div id="flow">
					<ul id="flowul1">
						<li>0</li>
						<li>${followerCnt }</li>
						<li>${followCnt }</li>
					</ul>
					<ul id="flowul2">
						<li>게시글</li>
						<li><button onclick="followerbutton('${me.nickname}')" type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal">
						    팔로워
						  </button>
					  	</li>
						<li><button onclick="followbutton('${me.nickname}')" type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal">
						    팔로우
						  </button>
					  	</li>
					</ul>
					<div id="showinfo">
						${me.info }
					</div>
				</div>
			</div>
		</div>
		<div class="photo_box">
			<ul class="photo_gallery">
				<c:forEach var="my" items="${mylist }" varStatus="status">
					<%--  <c:if test="${user.nickname==my.nickname }">
         <div class="deleteBtn_box">
         <button class="deleteBtn" onclick='delete_func(${my.pno })'>삭제</button>
         </div>
      </c:if> --%>
					<c:forTokens var="photo" items="${my.photofiles }" delims=",">
						<li><img class="photo_file" src="/upload/${photo }"></li>
					</c:forTokens>
				</c:forEach>
			</ul>
		</div>
	</main>
	
	 <!-- The Modal -->
  <div class="modal" id="myModal">
    <div class="modal-dialog" style="margin-top: 10em;">
      <div class="modal-content" style="background-color: #262626;">
      
        <!-- Modal Header -->
        <div class="modal-header" style="color: white">
          <h4 class="modal-title" id="title">Modal Heading</h4>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        
        <!-- Modal body -->
        <div id="showfollow" class="modal-body" style="height: 365px; color: white">
          
        </div>
        
        <!-- Modal footer -->
        <div class="modal-footer">
          <button type="button" id="buttonn" class="btn btn-danger" data-dismiss="modal">Close</button>
        </div>
        
      </div>
    </div>
  </div>
	
	
</body>
<script type="text/javascript">
	const user = '${sessionScope.user.nickname}'
	const target = '${me.nickname}'
</script>
<script type="text/javascript" src="resources/js/myposting.js"></script>
<script type="text/javascript" src="resources/js/comments.js"></script>
<script type="text/javascript" src="resources/js/follow.js"></script>
</html>