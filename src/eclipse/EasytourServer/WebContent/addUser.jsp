<%@page import="hjh.Login.LoginBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
LoginBean loginbean = new LoginBean();
request.setCharacterEncoding("utf-8");
response.setCharacterEncoding("utf-8");
String userid = request.getParameter("userid");
String password = request.getParameter("password");
String username = "newer";
int Result = loginbean.AddUser(userid, password, username);
if(Result == 1) {
	System.out.println("OK");
}
%>