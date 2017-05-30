<%@ page import="jdbc_power.PowerVO" %>
<%@ page import="jdbc_waCount.WaVO" %>
<%@ page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="css/bootstrap.min.css">
	<link rel="stylesheet" href="css/main_view.css">
	<link rel="stylesheet" href="css/pln.css">
	<link rel="stylesheet" href="css/btn.css">
	<script src="js/jquery-3.2.1.js"></script>
</head>
<body>
	<jsp:useBean id="dao" class="jdbc_power.PowerDAO" />
	<jsp:useBean id="washing" class="jdbc_waCount.WashingDAO" />
	<%!
		ArrayList<PowerVO> list = null; 
		ArrayList<WaVO> wa_list = null;
		int wm_count = 1;	// 세탁기 개수
		int count = 1;	// 세탁기 번호 
	%>
	<%
		count = 1;
		list = dao.getPowerlist();
		wa_list = washing.getWAlist();
		wm_count = Integer.parseInt(wa_list.get(0).getWaCount().toString());
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
		<form action="DBPowerUpdate.jsp">
			<%
				for(int loop=0; loop < wm_count; loop++){
			%>
			<div class="row">
				<div class="col-sm-1">
					<h3><%=count++%>번</h3>
				</div>
				<div class="col-sm-3">
					<img src="img/washing-01<%if(list.get(loop).getSetCheck().equals("1")){%>_1<%}%>.png" class="img-responsive center-block">
				</div>
				<div class="col-sm-4">
					<h3>경과 시간 : 00분 </h3>
					<h3>상태 : <%if(!list.get(loop).getSetCheck().equals("1")){%>사용가능<%}else{%>세탁중<%}%></h3>
					<label class="slideTwo"></label>
				</div>
				<div class="col-sm-4">
					<div class="switch-field">
					      <input type="radio" id="switch_left<%=loop%>" name="switch_<%=loop%>" value="1" <% if(list.get(loop).getSetCheck().equals("1")){%>checked<%}%>/>
					      <label for="switch_left<%=loop%>">ON</label>
					      <input type="radio" id="switch_right<%=loop%>" name="switch_<%=loop%>" value="0" <% if(!list.get(loop).getSetCheck().equals("1")){%>checked<%}%>/>
					      <label for="switch_right<%=loop%>">OFF</label>
					</div>
					
				</div>
			</div>
			<%}%>
			<%-- <input type="submit" value="SAVE"> --%>
			<button type="submit">SAVE</button>
		</form>
		<div class="row">
			<div class="col-sm-12">
				<div class="jb-cell">
					<h4>...</h4>
				</div>
			</div>
		</div>
	</div>
	<script	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/btn.js"></script>
</body>
</html>