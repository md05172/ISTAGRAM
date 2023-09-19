<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 등록</title>
<link rel="stylesheet" type="text/css" href="/resources/css/posting.css">
<link href="resources/css/sidebar.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="/resources/css/nav.css">
<link href="resources/css/update.css" rel="stylesheet">
<link href="resources/css/sidebar.css" rel="stylesheet">
<link href="resources/css/search.css" rel="stylesheet">
<link href="resources/css/noti.css" rel="stylesheet">
</head>


<body>
<div id="modal">
   
    <div id="modal_content" style="display:none">

   <c:if test="${user.email==null }">
   <h3>게시글 등록</h3>
      <script type="text/javascript">
         alert('로그인 후 이용해주세요')
         location.href="/login";
      </script>
   </c:if>
   <c:if test="${user.email!=null}">
   <div class="top_profile">
   <span>접속 유저 email : </span>
   [<c:out value="${user.email }"/>]
   <br>
   <span>접속 유저 닉네임 : </span>
   [<c:out value="${user.nickname }"/>]
   </div>
   <br>
   <br>
   <div class="insertForm">
   <form action="save" method="post" enctype="multipart/form-data">
      <input type="hidden" name="nickname" value="${user.nickname }">
      <div>
      [<c:out value="${sessionScope.user.nickname }"/>]
      <span>사진을 등록해주세요.</span><br>
      </div>
      <div id="photoBox"></div>
      <div id="addBtn_box">
      <button id="addBtn" type="button">Add Photo</button>
      </div>
      <div id="hashtag-box">
      <input type="text" name="hashtag" class="hashtag" dir="ltr" placeholder="해시태그"><br>            <!-- 해시태그 -->
      <textarea name="content" class="content" placeholder="게시글 내용 입력"></textarea>
      </div>
      <!-- <input type="text" name="content"><br> -->            <!-- 게시글내용 -->
      <div>
      <button type="submit">등록하기</button>
      </div>
   </form>
   </div>
   </c:if>
   </div>
   <div class="modal_layer">
           <nav class="sidebar">
  <ul class="sidebar-links">
    <li>
      <div class="image-caption">
        <a href="/main">
          <img src="/resources/images/istagram1.png" style="width: 100%;" height : auto; alt="istagram">
        </a>
      </div>
    </li>
    <br>
    <li>
      <div class="image-caption">
        <a href="/main">
          <img src="/resources/images/home.png" style="width: 20%;" height : auto; alt="홈">
          <span class="caption">Home</span>
        </a>
      </div>
    </li>
    <br>
    <li>
      <div class="image-caption">
        <a href="#" class="search-btn">
          <img src="/resources/images/search.png" style="width: 20%;" height : auto; alt="Search">
          <span class="caption">Search</span>
        </a>
        <div class="search-box">
          <input type="search" id="find" class="search_input" placeholder="Search..."><br>
          <label id="search" class="label_search"></label>
          <span id="searchfind" class="searchfind"></span>
        </div>
      </div>
    </li>
    <br>
    <li>
      <div class="image-caption">
        <a href="javascript:openPop()">
          <img src="/resources/images/plus.png" style="width: 20%;" height : auto; alt="추가">
          <span class="caption">Add</span><span id="add"></span>
        </a> 
      </div>
    </li>
    <br>
    <li>
      <div class="image-caption">
        <a onclick="javascript:clickDM()">
          <img src="/resources/images/dm.png" style="width: 20%;" height : auto; alt="DM">
          <span class="caption">Direct Messages</span><span id="msgCnt"></span>
        </a>
      </div>
    </li>
    <br>
    <li>
      <div class="image-caption">
        <a href="/logout">
          <img src="/resources/images/logout.png" style="width: 20%;" height : auto; alt="로그아웃">
          <span class="caption">Logout</span>
        </a>
      </div>
    </li>
    <br>
    <li>
      <div class="image-caption">
        <a class="profile" href="##">
          <img src="/resources/images/heart.png" style="width: 20%;" height : auto; alt="개인정보수정">
          <span class="caption">Notification</span>
        </a>
         <div class="noti-box">
          <input type="submit" id="notifi" class="noti_input" placeholder="notification..."><br>
          <label id="noti" class="label_noti"></label>
          <span id="notifind" class="notifind"></span>
        </div>
      </div>
    </li>
    <br>
    <li>
      <div class="image-caption">
        <a class="profile" href="/myposting">
          <img src="/resources/images/profile.png" style="width: 20%;" height : auto; alt="프로필">
          <span class="caption">Profile</span>
        </a>
      </div>
    </li>
    
    
  </ul>
          </nav>
 </div>
   
</div>   
</body>
<script>

function openPop() {
    document.getElementById("modal_content").style.display = "block";

}

//팝업 닫기
function closePop() {
    document.getElementById("modal_content").style.display = "none";
}</script>
<script type="text/javascript">const id = "${sessionScope.user.nickname}"</script>
  <script type="text/javascript">const nickname = "${sessionScope.user.nickname}"</script>
  <script type="text/javascript" src="resources/js/chatt.js"></script>
  <script type="text/javascript" src="resources/js/search.js"></script>
  <script type="text/javascript" src="resources/js/noti.js"></script>
  <script type="text/javascript" src="resources/js/addpost.js"></script>
  <script type="text/javascript" src="resources/js/followconnect.js"></script>
  <script type="text/javascript" src="resources/js/posting.js"></script>
</html>