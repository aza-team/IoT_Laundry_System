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
<title>Join</title>
</head>
<body>
	<div class="cont_principal">
	<div class="cont_centrar">
	<div class="cont_login">
	<form action="JOIN_CHECK.jsp" method="post"	onsubmit="return input_check_func()">
		<div class="cont_tabs_login">
	<ul class='ul_tabs'>
		<li>
			<a href="#" onclick="location.href='LOGIN.jsp'">SIGN IN</a>
			<span class="linea_bajo_nom"></span>
		</li>
		<li class="active">
			<a href="#up" onClick="">SIGN UP</a>
			<span class="linea_bajo_nom"></span>
		</li>
	</ul>
	</div>
		<input type="text" class="input_form_sign d_block active_inp" placeholder="NAME" id="JOIN_name" name="JOIN_name"/>
		<input type="text" class="input_form_sign d_block active_inp" placeholder="ID" id="JOIN_id" name="JOIN_id" />
		<input type="password" class="input_form_sign d_block  active_inp" placeholder="PASSWORD" id="JOIN_pw" name="JOIN_pw" />
		<input type="password" class="input_form_sign d_block  active_inp" placeholder="CONFIRM PASSWORD" id="conf_pass_us" name="conf_pass_us" />
		<div class="cont_btn">
			<button class="btn_sign">�����ϱ�</button>
		</div>
	</form>
	</div>
	</div>
	</div>
	<script>
    // input_check_func�� ȸ�����Կ� �ʿ��� 4���� ������ ���δ� ä�� �־����� check ���ش�
    // �̴� form onsubmit�� ���� �ߵ��Ǹ� return ���� false �� ��� �������� �����Ͱ� action= ��ǥ�� �Ѿ�� �ʰԵȴ�
    function input_check_func() {
        var id = document.getElementById('JOIN_id').value;
        var pw = document.getElementById('JOIN_pw').value;
        var name = document.getElementById('JOIN_name').value;
        var pw_confirm = document.getElementById('conf_pass_us').value;
        if(id == null || pw == null || name == null || pw_confirm == null || 
         id == ""   || pw == ""   || name == ""  || pw_confirm == "") {
            alert("������ �ֽ��ϴ�");
            return false;
        } else if(pw !== pw_confirm){
        	 alert("��й�ȣ�� Ʋ���ϴ�");
             return false;
    	} else {
            // ��������� �����Ǹ� true�� ��ȯ�Ѵ� �̴� ���� �������� ������ action= ��ǥ�� �ѱ�ٴ°��� �ǹ�
            return true;
        }
    }    
    </script>
<script src="../js/home_login.js"></script>
</body>
</html>
