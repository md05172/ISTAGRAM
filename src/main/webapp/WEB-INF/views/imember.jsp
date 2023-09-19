<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<link href="resources/css/update.css" rel="stylesheet">
<link href="resources/css/sidebar.css" rel="stylesheet">
<link href="resources/css/search.css" rel="stylesheet">
<link href="resources/css/noti.css" rel="stylesheet">

<meta charset="UTF-8">
<title>UPDATE</title>
</head>
<body>
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
        <a href="/posting">
          <img src="/resources/images/plus.png" style="width: 20%;" height : auto; alt="추가">
          <span class="caption">Add</span>
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
        <a class="profile" href="#" onclick="fCntClear()">
          <img src="/resources/images/heart.png" style="width: 20%;" height : auto; alt="개인정보수정">
          <span class="caption">Notification</span><span id="fCnt"></span>
        </a>
        <div class="noti-box">
          <input type="submit" id="notifi" class="noti_input" placeholder="notification..."><br>
          <label id="noti" class="label_noti"></label>
          <div id="notifind" class="notifind"></div>
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

</body>

<script type="text/javascript">const id = "${sessionScope.user.nickname}"</script>
<script type="text/javascript">const nickname = "${sessionScope.user.nickname}"</script>
<script type="text/javascript" src="resources/js/chatt.js"></script>
<script type="text/javascript" src="resources/js/search.js"></script>
<script type="text/javascript" src="resources/js/followconnect.js"></script>
<script type="text/javascript" src="resources/js/noti.js"></script>
</html>