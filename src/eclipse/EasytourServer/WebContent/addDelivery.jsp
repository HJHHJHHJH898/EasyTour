<%@page import="hjh.delivey.DeliveryBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
DeliveryBean deliveryBean = new DeliveryBean();
request.setCharacterEncoding("utf-8");
String userid = request.getParameter("userid");
String now_city = request.getParameter("now_city");
String now_address = request.getParameter("now_address");
String after_address = request.getParameter("after_address");
String snumber = request.getParameter("number");
String money = request.getParameter("money");
int number = Integer.parseInt(snumber);
int Result  = deliveryBean.AddDelivery(userid, now_city, now_address, after_address, number, money);
if(Result == 1) {
	System.out.println("1");
}
%>
