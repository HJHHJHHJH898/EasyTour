
<%@page import="hjh.Tips.TipsInfo"%>
<%@page import="hjh.Tips.TipsBean"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
TipsBean bean = new TipsBean();
List<TipsInfo> data = bean.SelectAll();
request.setCharacterEncoding("utf-8");
response.setCharacterEncoding("utf-8");

System.out.printf("共有%d条记录\n",data.size());
Iterator<TipsInfo> iter = data.iterator();
out.println("[");
while(iter.hasNext()) {
	TipsInfo info = iter.next();
	System.out.printf("%s\n",info.toString());
	String yinhao = "\"";
	out.println("{");
	 out.println(yinhao+"id" +"\":"+yinhao+ info.get_id() + yinhao);
	 out.println(","+yinhao+"author" +"\":"+yinhao+ info.get_author() + yinhao);
	 out.println(","+yinhao+"wherewant" +"\":"+yinhao+ info.get_where() + yinhao);
	 out.println(","+yinhao+"time" +"\":"+yinhao+ info.get_time() + yinhao);
	 out.println("}");
	 if(iter.hasNext()){
		 out.print(",");
	 }
}
out.println("]");
%>