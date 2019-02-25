<%@page import="hjh.stacktip.TipbufferBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
TipbufferBean bean = new TipbufferBean();

request.setCharacterEncoding("utf-8");
response.setCharacterEncoding("utf-8");
String tipid = request.getParameter("tipid");
String from = request.getParameter("from");
String to = request.getParameter("to");
String content = request.getParameter("content");
int Result = bean.AddBuffer(Integer.parseInt(tipid),from, to,content);
if(Result == 1) {
	System.out.println("OK");
}
%>