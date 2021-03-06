<div class="col-sm-12"><%@ page language="java"
		contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
	<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<html lang="ko">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Bootstrap | Grid System</title>
	<link rel="stylesheet" href="css/bootstrap.min.css">
	<link rel="stylesheet" href="css/main_view.css">
	<!--[if lt IE 9]>
	      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
	      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	    <![endif]-->
</head>
<body>
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
			<div class="col-sm-4">
				<div class="jb-cell">
					<h3>경과 시간 : 07분 </h2>
					<h3>상태 : 탈수중</h3>
					<label class="slideTwo"></label>
				</div>
			</div>
			<div class="col-sm-4">
				<div class="jb-cell">
					<h3>경과 시간 : 37분 </h2>
					<h3>상태 : 세탁중</h3>
						<label class="slideThree"></label>
				</div>
			</div>
			<div class="col-sm-4">
				<div class="jb-cell">
					<h3>경과 시간 : 분 </h2>
					<h3>상태 : 사용가능</h3>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-12">
				<div class="jb-cell">
					<h4>Lorem</h4>
				</div>
			</div>
		</div>
	</div>
	<script	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>
	</html>