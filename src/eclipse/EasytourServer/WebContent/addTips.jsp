<%@page import="java.util.Calendar"%>
<%@page import="hjh.Tips.TipsBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
TipsBean bean = new TipsBean();

request.setCharacterEncoding("utf-8");
response.setCharacterEncoding("utf-8");
String author = request.getParameter("author");
String where = request.getParameter("where");
String userid = request.getParameter("userid");
String data = request.getParameter("tipsdata");
Calendar cal=Calendar.getInstance();      
int y=cal.get(Calendar.YEAR);      
int m=cal.get(Calendar.MONTH);      
int d=cal.get(Calendar.DATE);      
int h=cal.get(Calendar.HOUR_OF_DAY);      
int mi=cal.get(Calendar.MINUTE);      
int s=cal.get(Calendar.SECOND); 
String time = y+"-"+m+"-"+d+"-"+h+"-"+mi+"-"+s;
int Result = bean.AddTips(author, where, time,data,userid);
if(Result == 1) {
	System.out.println("OK");
}
%>