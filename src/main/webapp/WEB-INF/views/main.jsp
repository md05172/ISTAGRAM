<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/resources/css/main.css">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script
	src="https://cdn.jsdelivr.net/npm/jquery@3.6.3/dist/jquery.slim.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<script type="text/javascript"
	src="http://code.jquery.com/jquery-latest.js"></script>
<link rel="stylesheet" type="text/css" href="/resources/css/nav.css">
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
</head>
<style>
.modal-dialog {
	max-width: 1200px;
}

div.carousel-inner {
	width: 600px;
	height: 400px;
	margin: 0 auto;
}

div.carousel-item active {
	width: 600px;
	height: 400px;
}

div.carousel-item {
	width: 600px;
	height: 400px;
}

div.carousel slide {
	width: 600px;
	height: 400px;
}

.modal {
	z-index: 9998;
}

.modal show {
	width: 1200px;
}

.insertbutton {
	background-color: white;
}

.modal-backdrop {
	z-index: 9994;
}

.clSpan{
    font-size: 24px;
}

</style>
<body>
	<jsp:include page="imember.jsp" />
	<main>
		<div class="photo_box">
			<c:forEach var="i" items="${list }" varStatus="status">
				<div id="demo${status.index }" class="carousel slide">
					<input type="hidden" value="${i.nickname }"> <input
						type="hidden" value="${status.index }"> <input
						type="hidden" value="${i.hashtag }"> <input type="hidden"
						value="${i.content }">
					<div class="carousel-inner">
						<div class="photo_gallery">


							<!-- 반복할 숫자 -->
							<c:forTokens var="photo" items="${i.photofiles }" delims=","
								varStatus="counts">



								<!-- 첫번째 반복시 -->

								<c:if test="${counts.index == 0 }">

									<!-- 1번째 -->
									<div class="carousel-item active">
										<img class="photo_file" src="/upload/${photo }">
									</div>

								</c:if>

								<c:if test="${counts.index !=0 }">
									<!-- 나머지 반복시 -->
									<div class="carousel-item">
										<img class="photo_file" src="/upload/${photo }">
									</div>
								</c:if>

							</c:forTokens>
							<!-- 좌우 태그 확인용 -->
							<a class="carousel-control-prev" href="#demo${status.index }"
								data-slide="prev"> <span class="carousel-control-prev-icon"></span>
							</a> <a class="carousel-control-next" href="#demo${status.index }"
								data-slide="next"> <span class="carousel-control-next-icon"></span>
							</a>



						</div>
					</div>
					<button data-toggle="modal" data-target="#myModal" type="button"
						class="btn btn-primary" style="margin-left: 210px;">댓글더보기</button>
					<input type="hidden" value="${i.pno}" />



					<!-- 좋아요 라인 -->
					<div class="my_checkbox" style="margin-left: 210px;">
						<input type="hidden" value="${i.pno}">
						<button class="my_checked" value=""
							style="background: none; border: none;">
							<img class="my_img${i.pno }" src="/resources/img/beanheart.png"
								style="width: 40px; height: 40px;">
						</button>
					</div>
					
					
					
					
					
					<!-- 댓글 라인 -->
					<div class="container"  style="margin-left: 210px;">
						<label for="content" style="color:white">댓글</label> <span id="conn${i.pno }" style="color:white"></span>
						<div class="input-group">
							<span class="input-group-btn"> <input type="text"
								class="form-control" id="content" name="content"
								placeholder="내용을 입력하세요.">
								<button class="btn btn-default" type="button"
									name="commentInsertBtn" style="background-color: yellow;">등록</button>
								<input type="hidden" name="pno" value="${i.pno }">
							</span>
						</div>
					</div>
				</div>


				<div class="container">
					<div class="commentList"></div>
				</div>
			</c:forEach>
		</div>

		<!-- 모달 -->
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="comment-box">
						<!-- The Modal -->
						<div class="modal" id="myModal">
							<div class="modal-dialog modal-lg">
								<div class="modal-content">
									<!-- Modal header -->
									<div class="modal-header">
										<h4 class="modal-title" id="writer"></h4>
										<button type="button" class="close" data-dismiss="modal">&times;</button>
									</div>
									<!-- Modal body -->
									<div class="modal-body">
										<div class="row">
											<div class="col-md-4">
												<div id="photo_zone">
													<!-- 사진 넣는 곳 -->
												</div>
											</div>
											<div class="col-md-8" 
												style="width: 500px;  flex: none; margin-left: 5.5cm;">
												<div id="pcontent" style="border-bottom: 1px solid #D3D3D3;">게시글 내용</div>
												<div id="phashtag" style="border-bottom: 1px solid #D3D3D3;">해시태그</div>
												<div id="contentList">댓글<br></div>
											</div>
										</div>
									</div>
									<!-- Modal footer -->
									<div class="modal-footer">
										<div class="container">
											<label for="content"></label> <span id="conn"></span>
											<div class="input-group">
												<span class="input-group-btn"> <input type="text"
													class="form-control" id="content" name="content"
													placeholder="내용을 입력하세요.">
													<button class="btn btn-default" type="button" id="btnInsS"
														name="commentInsertBtn">등록</button> 
												</span>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</main>
</body>
<script type="text/javascript">
   const user = '${sessionScope.user.nickname}'
   
   let hearts = "${likes}";
   console.log(hearts);
   
   
   document.querySelectorAll('button[class="my_checked"]').forEach(item =>{
      const pno = item.previousElementSibling.value
      console.log("pno", pno);
      const img = document.querySelector('img[class="my_img'+pno+'"]')
      console.log("img", img);
      console.log("??? = ", hearts.includes(pno))
      if(hearts.includes(pno)){
         item.value='active';
         img.setAttribute('src','/resources/img/heart.png')
      }else{
         item.value = '';
         img.setAttribute('src','/resources/img/beanheart.png')
      }
   })
</script>
<script type="text/javascript">const nickname = "${sessionScope.user.nickname}"</script>
<script type="text/javascript" src="resources/js/like.js"></script>
<script type="text/javascript" src="resources/js/main.js"></script>
<script type="text/javascript" src="resources/js/comment.js"></script>
</html>