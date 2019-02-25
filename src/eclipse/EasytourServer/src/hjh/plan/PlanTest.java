package hjh.plan;

import java.util.Iterator;
import java.util.List;

public class PlanTest {
	public static void main(String[] args) {
		PlanBean planbean = new PlanBean();
		int Result = planbean.AddPlan("hjh123", "ZheJiang", "2015-2-7", "2015-2-9", "2-5");
		if(Result == 1) {
			System.out.println("OK");
		}
//	    List<PlanInfo> plandata;
//	    plandata = planbean.getPlanData("hjh123");
//	    Iterator<PlanInfo> iter = plandata.iterator();
//        while (iter.hasNext()){
//        	PlanInfo inforesult = iter.next();
//            System.out.printf("%s\n",inforesult.toString());
//        }
	}
}
