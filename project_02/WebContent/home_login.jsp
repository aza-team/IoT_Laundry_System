<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
 <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title></title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/home_login.css">
</head>
<body>
	<div class="cont_principal">
	<div class="cont_centrar">
	<div class="cont_login">
		<form action="DBhome_login_insert.jsp">
			<div class="cont_tabs_login">
				<ul class='ul_tabs'>
					<li class="active"><a href="#" onclick="sign_in()">로그인</a>
						<span class="linea_bajo_nom"></span>
					</li>
					<li><a href="#up" onclick="sign_up()">회원 가입 </a><span class="linea_bajo_nom"></span>
					</li>
				</ul>
			</div>
			<div class="cont_text_inputs">
			<input type="text" class="input_form_sign " placeholder="NAME" name="name_us" />
			<input type="text" class="input_form_sign d_block active_inp" placeholder="ID" name="id_us" />
			<input type="password" class="input_form_sign d_block  active_inp" placeholder="PASSWORD" name="pass_us" />  
			<input type="password" class="input_form_sign" placeholder="CONFIRM PASSWORD" name="conf_pass_us" />
			
			<a href="#" class="link_forgot_pass d_block" >Forgot Password ?</a>    
			<div class="terms_and_cons d_none">
				<p><input type="checkbox" name="terms_and_cons" /> <label for="terms_and_cons">Accept  Terms and Conditions.</label></p>
			</div>
			</div>
			<div class="cont_btn">
				<button class="btn_sign">SIGN IN</button>
			</div>
		</form>
	</div>
	</div>
	</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/home_login.js"></script>
</body>
</html>