<%@page import="java.util.Iterator"%>
<%@page import="hjh.Personal.PersonInfo"%>
<%@page import="java.util.List"%>
<%@page import="hjh.Personal.PersonBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
PersonBean personBean = new PersonBean();
request.setCharacterEncoding("utf-8");
response.setCharacterEncoding("utf-8");
List<PersonInfo> persondata;
String userid = request.getParameter("userid");
persondata = personBean.getpersonData(userid);
Iterator<PersonInfo> iter = persondata.iterator();
out.println("[");
while(iter.hasNext()) {
	PersonInfo info = iter.next();
	System.out.printf("%s\n",info.toString());
	String yinhao = "\"";
	out.println("{");
	 out.println(yinhao+"username" +"\":"+yinhao+ info.getUsername() + yinhao);
	 out.println(","+yinhao+"sex" +"\":"+yinhao+ info.getSex() + yinhao);
	 out.println(","+yinhao+"email" +"\":"+yinhao+ info.getEmail() + yinhao);
	 out.println(","+yinhao+"phone" +"\":"+yinhao+ info.getPhone() + yinhao);
	 out.println(","+yinhao+"mysignature" +"\":"+yinhao+ info.getMysignature() + yinhao);
	 out.println("}");
	 if(iter.hasNext()){
		 out.print(",");
	 }
}
out.println("]");
%>