<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	
<%
	String path=request.getContextPath();     //会获得 /itcastTax的值
	String basePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"; //http://localhost:8080/itcastTax/
	response.sendRedirect(basePath+"sys/login_toLoginUI.action");
	
%>
