<%@page import="java.util.Iterator"%>
<%@page import="hjh.Tips.TipscomInfo"%>
<%@page import="hjh.Tips.ReplyInfo"%>
<%@page import="java.util.List"%>
<%@page import="hjh.Tips.TipsBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
TipsBean bean = new TipsBean();
List<TipscomInfo> commentdata = bean.SelectAlltip();
List<ReplyInfo> replydata;
request.setCharacterEncoding("utf-8");
response.setCharacterEncoding("utf-8");

Iterator<TipscomInfo> iter = commentdata.iterator();
int total = commentdata.size();
out.println("{\"code\":1,\"message\":\"check\",\"data\":{\"total\":"+total+",\"list\":[");
while(iter.hasNext()) {
	TipscomInfo tipscomInfo = iter.next();
	replydata = bean.SelectAllreply(tipscomInfo.getId());
	String yinhao = "\"";
	 out.println("{");
	 out.println(yinhao+"id" +"\":"+tipscomInfo.getId());
	 out.println(","+yinhao+"userid" +"\":"+yinhao+ tipscomInfo.getUserid() + yinhao);
	 out.println(","+yinhao+"author" +"\":"+yinhao+ tipscomInfo.getAuthor() + yinhao);
	 out.println(","+yinhao+"data" +"\":"+yinhao+ tipscomInfo.getData() + yinhao);
	 out.println(","+yinhao+"pinglun" +"\":"+tipscomInfo.getPinglun());
	 out.println(","+yinhao+"zan" +"\":"+tipscomInfo.getZan());
	 out.println(","+yinhao+"where" +"\":"+yinhao+ tipscomInfo.getWhere() + yinhao);
	 out.println(","+yinhao+"replyList" +"\":"+"[");
	 
	Iterator<ReplyInfo> reIterator = replydata.iterator();
	while(reIterator.hasNext()) {
		ReplyInfo replyInfo = reIterator.next();
		 out.println("{");
		 out.println(yinhao+"username" +"\":"+yinhao+ replyInfo.getUsername() + yinhao);
		 out.println(","+yinhao+"userid" +"\":"+yinhao+ replyInfo.getUserid() + yinhao);
		 out.println(","+yinhao+"tipid" +"\":"+yinhao+ replyInfo.getTipid() + yinhao);
		 out.println(","+yinhao+"content" +"\":"+yinhao+ replyInfo.getContent() + yinhao);
		 out.println(","+yinhao+"status" +"\":"+yinhao+ replyInfo.getStatus() + yinhao);
		 out.println(","+yinhao+"time" +"\":"+yinhao+ replyInfo.getTime() + yinhao);
		 out.println("}");
		 if(reIterator.hasNext()){
			 out.print(",");
		 }
		System.out.printf("%s\n",tipscomInfo.toString()+"\t"+replyInfo.toString());
	}out.println("]");
	
	out.println("}");
	if(iter.hasNext()){
		 out.print(",");
	 }
}out.println("]");out.println("}");out.println("}");
%>