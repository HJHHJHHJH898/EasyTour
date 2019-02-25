package hjh.Tips;

import java.util.Calendar;
import java.util.Iterator;
import java.util.List;


public class TipsBeanTest {
	public static void main(String[] args) {
		TipsBean bean = new TipsBean();
		Calendar cal=Calendar.getInstance();      
		int y=cal.get(Calendar.YEAR);      
		int m=cal.get(Calendar.MONTH);      
		int d=cal.get(Calendar.DATE);      
		int h=cal.get(Calendar.HOUR_OF_DAY);      
		int mi=cal.get(Calendar.MINUTE);      
		int s=cal.get(Calendar.SECOND); 
		String time = y+"-"+m+"-"+d+"-"+h+"-"+mi+"-"+s;
		
		int Result = bean.AddReplys("15", "test", "hjh123", "hjh", time, "3");
		if(Result ==1)
			System.out.println("OK");		
//		List<TipscomInfo> commentdata = bean.SelectAlltip();
//		List<ReplyInfo> replydata;
//		
//		Iterator<TipscomInfo> iter = commentdata.iterator();
//		
//		while(iter.hasNext()) {
//			TipscomInfo tipscomInfo = iter.next();
//			replydata = bean.SelectAllreply(tipscomInfo.getId());
//			Iterator<ReplyInfo> reIterator = replydata.iterator();
//			
//			while(reIterator.hasNext()) {
//				ReplyInfo replyInfo = reIterator.next();
//				System.out.printf("%s\n",tipscomInfo.toString()+"\t"+replyInfo.toString());
//			}
//		}
		
//		List<TipsInfo> data = bean.SelectAll();
//		
//		System.out.printf("����%d����¼\n",data.size());
//		Iterator<TipsInfo> iter = data.iterator();
//		while(iter.hasNext()) {
//			TipsInfo inforesult = iter.next();
//			System.out.printf("%s\n",inforesult.toString());
//		}
//		int Result = bean.AddTips("hjh", "Zhangjiajie", "2016-5-1","你好","hjh123");
//		if(Result ==1)
//			System.out.println("OK");
		
	}
}
