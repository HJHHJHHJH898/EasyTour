<%@page import="hjh.Personal.PersonBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
PersonBean personBean = new PersonBean();
request.setCharacterEncoding("utf-8");
response.setCharacterEncoding("utf-8");
String userid = request.getParameter("userid");
String index = request.getParameter("index");
String value = request.getParameter("value");
if(personBean.UpdatePerson(userid, index, value)==1){
	System.out.printf("OK");
}

%>