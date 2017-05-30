<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ page import="jdbc_power.PowerVO" %>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="css/bootstrap.min.css">
	<link rel="stylesheet" href="css/main_view.css">
	<link rel="stylesheet" href="css/pln.css">
	<script src="js/jquery-3.2.1.js"></script>
</head>
<body>
	<jsp:useBean id="dao" class="jdbc_power.PowerDAO" />
	<%! ArrayList<PowerVO> list = null; %>
	<%
		list = dao.getPowerlist();
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
			<div class="col-sm-4">
				<img src="img/washing-01<%if(list.get(0).getSetCheck().equals("1")){%>_1<%}%>.png" 
					class="img-responsive center-block">
				<h3> 1번 세탁기 </h3>
			</div>
			<div class="col-sm-4">
				<img src="img/washing-01<%if(list.get(1).getSetCheck().equals("1")){%>_1<%}%>.png" 
					class="img-responsive center-block">
				<h3> 2번 세탁기 </h3>
			</div>
			<div class="col-sm-4">
				<img src="img/washing-01<%if(list.get(2).getSetCheck().equals("1")){%>_1<%}%>.png"
					class="img-responsive center-block">
				<h3> 3번 세탁기 </h3>
			</div>
		</div>
		<div class="row">
			<form action="DBPowerUpdate.jsp">
				<div class="col-sm-4">
				    <div class="switch-field">
				      <input type="radio" id="switch_left1" name="switch_1" value="1" <% if(list.get(0).getSetCheck().equals("1")){%>checked<%}%>/>
				      <label for="switch_left1">Yes</label>
				      <input type="radio" id="switch_right1" name="switch_1" value="0" <% if(!list.get(0).getSetCheck().equals("1")){%>checked<%}%>/>
				      <label for="switch_right1">No</label>
					</div>
				</div>			
				<div class="col-sm-4">
				    <div class="switch-field">
				      <input type="radio" id="switch_left2" name="switch_2" value="1" <% if(list.get(1).getSetCheck().equals("1")){%>checked<%}%>/>
				      <label for="switch_left2">Yes</label>
				      <input type="radio" id="switch_right2" name="switch_2" value="0" <% if(!list.get(1).getSetCheck().equals("1")){%>checked<%}%>/>
				      <label for="switch_right2">No</label>
					</div>
				</div>
				<div class="col-sm-4">
				    <div class="switch-field">
				      <input type="radio" id="switch_left" name="switch_3" value="1" <% if(list.get(2).getSetCheck().equals("1")){%>checked<%}%>/>
				      <label for="switch_left">Yes</label>
				      <input type="radio" id="switch_right" name="switch_3" value="0" <% if(!list.get(2).getSetCheck().equals("1")){%>checked<%}%>/>
				      <label for="switch_right">No</label>
					</div>
				</div>
				<input type="submit" value="SAVE">
			</form>
		</div>
	<script	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	</div>
</body>
</html>