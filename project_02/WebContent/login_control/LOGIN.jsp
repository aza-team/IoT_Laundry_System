<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%
    request.setCharacterEncoding("euc-kr");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="../css/bootstrap.min.css">
<link rel="stylesheet" href="../css/home_login.css">
<title>Login</title>
</head>
<body>
	<%
        // 현재 로그인된 아이디가 없다면 (= session에 저장된 id가 없다면)
        if(session.getAttribute("id") == null) {
            %>
	<div class="cont_principal">
	<div class="cont_centrar">
	<div class="cont_login">
	<form action="LOGIN_CHECK.jsp" method="post">
	<div class="cont_tabs_login">
	<ul class='ul_tabs'>
		<li class="active"><a href="#" onclick="">SIGN IN</a>
			<span class="linea_bajo_nom"></span>
		</li>
		<li><a href="#up" onClick="location.href='JOIN.jsp'">SIGN UP</a>
			<span class="linea_bajo_nom"></span>
		</li>
	</ul>
	</div>
		<input type="text" class="input_form_sign " placeholder="NAME" name="JOIN_name" />
		<input type="text" class="input_form_sign d_block active_inp" placeholder="ID" name="input_id" />
		<input type="password" class="input_form_sign d_block  active_inp" placeholder="PASSWORD" name="input_pw" />
		<a href="#" class="link_forgot_pass d_block" >비밀번호를 잃어버렸습니까 ?</a>    
		<div class="terms_and_cons d_none">
			<p><input type="checkbox" name="terms_and_cons" /> <label for="terms_and_cons">위의 정보가 맞습니까?</label></p>
		</div>
		<div class="cont_btn">
			<button class="btn_sign">로그인</button>
		</div>
		<br />
	</form>
	</div>
	</div>
	</div>
	<br />
	<!-- <button class="btn_sign" onClick="location.href='JOIN.jsp'">회원가입</button> -->
	<%
        } %>
      
	<br />
	<form action="LOGOUT.jsp" method="post">
		<br /> <br />
		<button>로그아웃</button>
		<br /> <br />
	</form>
	<br />
	<button onClick="location.href='CHANGEPROFILE.jsp'">개인정보 변경</button>
	
<script src="../js/home_login.js"></script>
<script src="../https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="../js/bootstrap.min.js"></script>
</body>
</html>

