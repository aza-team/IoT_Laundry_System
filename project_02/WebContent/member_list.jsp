<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="jdbc_member.MemberVO"%>
<%@ page import="jdbc_member.MemberDAO" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="ko">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="initial-scale=1.0; maximum-scale=1.0; width=device-width;">
	<title></title>
	<link rel="stylesheet" href="css/bootstrap.min.css">
	<link rel="stylesheet" href="css/member_list.css">
	<script>
	    function idDelete(delID){
	       
	        //alert(delID);
	        location.href = "delete.jsp?id=" + delID;   //get방식으로 삭제할아이디를 넘김
	               
	    }
	</script>
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
			<div class="col-sm-2">
		</div>
			<div class="col-sm-3">
				<jsp:useBean id="dao" class="jdbc_member.MemberDAO" />
				<%
					ArrayList<MemberVO> list = dao.getMemberlist();
				%>
				<div style="width: 600px;">
					<table id="my-table" style="width: 100%; text-align: left;">
						<thead>
							<tr>
								<th>아이디</th>
								<th>이름</th>
								<th>비밀번호</th>
								<th>연락처</th>
								<th>e-mail</th>
								<th>삭제</th>
							</tr>
						</thead>
						<tbody>
							<%
								for (MemberVO vo : list) {
							%>
							<tr>
								<td><%=vo.getId()%></td>
								<td><%=vo.getName()%></td>
								<td><%=vo.getPwd()%></td>
								<td><%=vo.getPhone()%></td>
								<td><%=vo.getEmail()%></td>
								<td><input type="button" value="삭제" onclick="idDelete('<%=vo.getId() %>');"></td>
							</tr>
							<%
								}
							%>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
		<script src="js/member_list.js"></script>
</body>
</html>

