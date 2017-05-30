<div class="col-sm-12"><%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bootstrap | Grid System</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/main_view.css">
    <link rel="stylesheet" href="css/setting.css">

  </head>
  <body>
  	<%
  		String wa_count = request.getParameter("getCount");
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
		<form action="DB_WA_Count.jsp">
		<div class="title">
			<span>세탁기 개수 설정</span>
			Washing Machine Count
		</div>
		<div class="demo demo10">
			<div class="css">
				<label for="d19">현제 개수</label>
				<input id="d20" type="text" readonly name="selectWashing" value="<%=wa_count%>"/>
		  	</div>
			<div class="css">
				<label for="d19">변경할 개수 입력...</label>
				<input id="d19" type="text" name="washingCount"/>
			</div>
			
			<input type="submit" value="SAVE">
		</div>
		</form>
    </div>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/setting.js"></script>
  </body>
</html>