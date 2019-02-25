<%@page import="java.util.Iterator"%>
<%@page import="hjh.stacktip.BufferInfo"%>
<%@page import="java.util.List"%>
<%@page import="hjh.stacktip.TipbufferBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
TipbufferBean tipbufferBean = new TipbufferBean();
List<BufferInfo> bufferdata;
request.setCharacterEncoding("utf-8");
response.setCharacterEncoding("utf-8");
String to = request.getParameter("to");
bufferdata = tipbufferBean.SelectAlltip(to);
Iterator<BufferInfo> iter = bufferdata.iterator();
out.println("[");
while(iter.hasNext()) {
	BufferInfo info = iter.next();
	System.out.printf("%s\n",info.toString());
	String yinhao = "\"";
	out.println("{");
	 out.println(yinhao+"tipid" +"\":"+yinhao+ info.getTipid() + yinhao);
	 out.println(","+yinhao+"myfrom" +"\":"+yinhao+ info.getFrom() + yinhao);
	 out.println(","+yinhao+"myto" +"\":"+yinhao+ to + yinhao);
	 out.println(","+yinhao+"content" +"\":"+yinhao+ info.getContent() + yinhao);
	 out.println("}");
	 if(iter.hasNext()){
		 out.print(",");
	 }
}
out.println("]");
%>