<%@page import="java.util.Iterator"%>
<%@page import="hjh.plan.PlanInfo"%>
<%@page import="java.util.List"%>
<%@page import="hjh.plan.PlanBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
PlanBean planbean = new PlanBean();
List<PlanInfo> plandata;
request.setCharacterEncoding("utf-8");
response.setCharacterEncoding("utf-8");
String userid = request.getParameter("userid");
plandata = planbean.getPlanData(userid);
Iterator<PlanInfo> iter = plandata.iterator();

out.println("[");
while(iter.hasNext()) {
	PlanInfo info = iter.next();
	System.out.printf("%s\n",info.toString());
	String yinhao = "\"";
	out.println("{");
	 out.println(yinhao+"dest" +"\":"+yinhao+ info.getDest() + yinhao);
	 out.println(","+yinhao+"starttime" +"\":"+yinhao+ info.getStarttime() + yinhao);
	 out.println(","+yinhao+"endtime" +"\":"+yinhao+ info.getEndtime() + yinhao);
	 out.println(","+yinhao+"choice_luggage" +"\":"+yinhao+ info.getChoice_luggage() + yinhao);
	 out.println("}");
	 if(iter.hasNext()){
		 out.print(",");
	 }
}
out.println("]");
%>