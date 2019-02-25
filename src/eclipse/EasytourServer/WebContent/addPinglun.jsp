<%@page import="hjh.Tips.TipsBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
TipsBean bean = new TipsBean();

request.setCharacterEncoding("utf-8");
response.setCharacterEncoding("utf-8");
String tipid = request.getParameter("tipid");
String content = request.getParameter("content");
String userid = request.getParameter("userid");
String username = request.getParameter("username");
String time = request.getParameter("time");
String status = request.getParameter("status");
int Result = bean.AddReplys(tipid, content, userid, username, time, status);
if(Result == 1) {
	System.out.println("OK");
}
%>