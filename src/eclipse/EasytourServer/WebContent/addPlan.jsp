<%@page import="hjh.plan.PlanBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
PlanBean planbean = new PlanBean();
request.setCharacterEncoding("utf-8");
response.setCharacterEncoding("utf-8");
String userid = request.getParameter("userid");
String dest = request.getParameter("dest");
System.out.println(dest);
String starttime = request.getParameter("starttime");
String endtime = request.getParameter("endtime");
String choice_luggage = request.getParameter("choice_luggage");
int Result = planbean.AddPlan(userid, dest, starttime, endtime, choice_luggage);
if(Result == 1) {
	System.out.println("OK");
}
%>