<%@page import="java.util.Iterator"%>
<%@page import="hjh.delivey.DeliveryInfo"%>
<%@page import="java.util.List"%>
<%@page import="hjh.delivey.DeliveryBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
DeliveryBean deliveryBean = new DeliveryBean();
request.setCharacterEncoding("utf-8");
response.setCharacterEncoding("utf-8");
String userid = request.getParameter("userid");
List<DeliveryInfo> infolist = deliveryBean.getDeliveryData(userid);
Iterator<DeliveryInfo> iter = infolist.iterator();
out.println("[");
while(iter.hasNext()) {
	DeliveryInfo info = iter.next();
	System.out.printf("%s\n",info.toString());
	String yinhao = "\"";
	out.println("{");
	 out.println(yinhao+"now_city" +"\":"+yinhao+ info.getNow_city() + yinhao);
	 out.println(","+yinhao+"now_address" +"\":"+yinhao+ info.getNow_address() + yinhao);
	 out.println(","+yinhao+"after_address" +"\":"+yinhao+ info.getAfter_address() + yinhao);
	 out.println(","+yinhao+"number" +"\":"+yinhao+ info.getNumber() + yinhao);
	 out.println(","+yinhao+"money" +"\":"+yinhao+ info.getMoney() + yinhao);
	 out.println("}");
	 if(iter.hasNext()){
		 out.print(",");
	 }
}
out.println("]");
%>