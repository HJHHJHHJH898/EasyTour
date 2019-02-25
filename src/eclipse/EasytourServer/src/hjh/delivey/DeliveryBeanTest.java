package hjh.delivey;

import java.util.Iterator;
import java.util.List;


public class DeliveryBeanTest {
	public static void main(String[] args) {
		DeliveryBean deliveryBean = new DeliveryBean();
//		List<DeliveryInfo> info = deliveryBean.getDeliveryData("hjh123");
//		Iterator<DeliveryInfo> iter = info.iterator();
//		while(iter.hasNext()) {
//			DeliveryInfo inforesult = iter.next();
//			System.out.println(inforesult.toString());
//		}
		int Result  = deliveryBean.AddDelivery("hjh123", "ZheJiang", "HangZhou", "JiYang", 5, "120");
		if(Result == 1) {
			System.out.println("1");
		}
	}
}
