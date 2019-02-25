<%@page import="java.util.Iterator"%>
<%@page import="hjh.Login.UserInfo"%>
<%@page import="java.util.List"%>
<%@page import="hjh.Login.LoginBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
LoginBean loginbean = new LoginBean();
request.setCharacterEncoding("utf-8");
response.setCharacterEncoding("utf-8");
List<UserInfo> userdata = loginbean.getUserData();
Iterator<UserInfo> iter = userdata.iterator();
out.println("[");
while(iter.hasNext()) {
	UserInfo info = iter.next();
	System.out.printf("%s\n",info.toString());
	String yinhao = "\"";
	out.println("{");
	 out.println(yinhao+"userid" +"\":"+yinhao+ info.getUserid() + yinhao);
	 out.println(","+yinhao+"password" +"\":"+yinhao+ info.getPassword() + yinhao);
	 out.println(","+yinhao+"username" +"\":"+yinhao+ info.getUsername() + yinhao);
	 out.println("}");
	 if(iter.hasNext()){
		 out.print(",");
	 }
}
out.println("]");
%>