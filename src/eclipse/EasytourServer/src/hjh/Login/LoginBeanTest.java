package hjh.Login;

import java.util.Iterator;
import java.util.List;

import hjh.Tips.TipsBean;

public class LoginBeanTest {
	public static void main(String[] args) {
		LoginBean loginbean = new LoginBean();
		
//		String userid = "test1";
//		String password = "test1";
//		String username = "newer";
//		int Result = loginbean.AddUser(userid, password, username);
//		if(Result == 1) {
//			System.out.println("OK");
//		}
		
	    List<UserInfo> userdata;
	    userdata = loginbean.getUserData();
	    Iterator<UserInfo> iter = userdata.iterator();
        while (iter.hasNext()){
            UserInfo inforesult = iter.next();
            System.out.printf("%s\n",inforesult.toString());
        }
	}
}
