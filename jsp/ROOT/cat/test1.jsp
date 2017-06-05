<%@ page language="java" contentType="text/html; charset=EUC-KR"%>
<%@ page import="java.sql.*" %>
<%@ page import= "org.json.simple.*" %>

<%
	
	JSONObject jsonMain = new JSONObject();
	JSONArray jArray = new JSONArray();
	JSONObject jobject = new JSONObject();
	
	jobject.put("id","kmnii");
	jobject.put("password","hacking");
	
	jArray.add(0,jobject);
	jArray.add(0,jobject);
	jArray.add(0,jobject);
	
	jsonMain.put("datasend",jArray);
	out.println(jsonMain);
	out.flush();
	
	out.println(jArray.Parse(jobject));
		
%>