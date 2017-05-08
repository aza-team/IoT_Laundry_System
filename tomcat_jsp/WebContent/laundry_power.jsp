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
<link rel="stylesheet" href="css/checkbox.css">
</head>
<body>
	<jsp:useBean id="dao" class="jdbc_power.PowerDAO" />
	<%! ArrayList<PowerVO> list = null; %>
	<% 
		list = dao.getPowerlist();
	
		for(PowerVO vo : list){
			vo.getMacineNum();
			vo.getSetCheck();
		}
		
	%>
	
	<%! 
		String[] getCheckValue = null; 
	%>
	<%
		getCheckValue = request.getParameterValues("type");
		String content = request.getParameter("getCheckData");
	
	%>
	<div class="container">
		<div class="row">
			<div class="jb-cell">
				<a href="main_view.jsp"><img src="img/logo_01.png"
					class="img-responsive center-block"></a>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-12">
				<jsp:include page="menubar.jsp" flush="false" />
			</div>
		</div>
		<div class="row">
			<div class="col-sm-4">
				<img src="img/washing-01_1.png" class="img-responsive center-block">
			</div>
			<div class="col-sm-4">
				<img src="img/washing-01_1.png" class="img-responsive center-block">
			</div>
			<div class="col-sm-4">
				<img src="img/washing-01.png" class="img-responsive center-block">
			</div>
		</div>
		<div class="row">
			<form action="Test.jsp">
				<div class="col-sm-4">
					<section title="Slider checkbox with on/off state">
						<div class="slideThree">
							<input type="checkbox" value="None" id="slideThree" name="check" 
								/> 
							<label for="slideThree"></label>
						</div>
					</section>
				</div>
				<div class="col-sm-4">
					<section title="Slider checkbox with on/off state">
						<div class="slideThree">
							<input type="checkbox" value="None" id="slideThree2" name="check" checked /> 
							<label for="slideThree2"></label>
						</div>
					</section>
				</div>
				<div class="col-sm-4">
					<section title="Slider checkbox with on/off state">
						<div class="slideThree">
							<input type="checkbox" value="None" id="slideThree3" name="check" checked /> 
							<label for="slideThree3"></label>
						</div>
					</section>
				</div>
				<input type="submit" value="SAVE">
			</form>
		</div>
	</div>
</body>
</html>