<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="ko">
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="css/bootstrap.min.css">
	<link rel="stylesheet" href="css/main_view.css">
	<link rel="stylesheet" href="css/idleTime.css">
</head>

<body>
	<%! 
		String getTimeData = null;	// "setTime" �÷��� ���� �����Ű�� ����
	%> 
	
	<% 
		boolean btn_true = false;
		String content = request.getParameter("result");	// DBidleTimeUpdate.jsp
		getTimeData = request.getParameter("getTimeData");	// DBidleTimeSelect.jsp
		String btn_status = null;
		btn_status = request.getParameter("accessible-radio");	
		
		if(content != null){		// btn Ŭ������ �� getTimeData ������ ���� ���� ���� ���� ��� NullPoint ��...
			getTimeData = content;
		}
	%>

	<div class="container">
		<div class="row">
				<div class="jb-cell">
					<a href="main_view.jsp"><img src="img/logo_01.png" class="img-responsive center-block"></a>
				</div>
		</div>
		<div class="row">
			<div class="col-sm-12">
				<jsp:include page="menubar.jsp" flush="false" />
			</div>
		</div>
		<div class="row">
			<div class="col-sm-12">
				<h3>
					<h1>��Ź�� ���޽ð� ����</h1>
					<p>Please choose.</p>
					<form action="DBidleTimeUpdate.jsp">
						<fieldset>
							<input id="item-1" class="radio-inline__input" type="radio"	name="accessible-radio" value="5" <% if(getTimeData.equals("5")){%>checked<%}%> /> 
							<label class="radio-inline__label" for="item-1"> 5 �� </label> 
							<input id="item-2" class="radio-inline__input" type="radio" name="accessible-radio" value="10" <% if(getTimeData.equals("10")){%>checked<%}%>/> 
							<label class="radio-inline__label" for="item-2"> 10 �� </label> 
							<input id="item-3" class="radio-inline__input" type="radio"	name="accessible-radio" value="15" <% if(getTimeData.equals("15")){%>checked<%}%> /> 
							<label class="radio-inline__label" for="item-3"> 15 �� </label>
							</br></br></br></br></br>
							<input type="submit" value="SAVE">
							&nbsp; &nbsp;
							<!-- <button onclick="location.href='main_view.jsp'">CANCLE</button> --> 
							
						</fieldset>
					</form>
					<div class="row">
						<br> <br> <br>
					</div>
			</div>
		</div>
	</div>
	<script	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>

</body>

</html>