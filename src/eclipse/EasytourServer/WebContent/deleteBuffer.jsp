<%@page import="hjh.stacktip.TipbufferBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
TipbufferBean bean = new TipbufferBean();

request.setCharacterEncoding("utf-8");
response.setCharacterEncoding("utf-8");
String to = request.getParameter("to");
int Result = bean.DeleteTips(to);
if(Result == 1) {
	System.out.println("OK");
}
%>