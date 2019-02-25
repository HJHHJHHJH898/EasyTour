package hjh.Personal;

import java.util.Iterator;
import java.util.List;

public class PersonBeanTest {
	public static void main(String[] args) {
		PersonBean personBean = new PersonBean();
//	    List<PersonInfo> persondata;
//	    persondata = personBean.getpersonData("hjh123");
//	    Iterator<PersonInfo> iter = persondata.iterator();
//	    while (iter.hasNext()){
//	    	PersonInfo inforesult = iter.next();
//	        System.out.printf("%s\n",inforesult.toString());
//	    }
		int result = personBean.UpdatePerson("hjh", "sex", "man");
		if(result == 1){
			System.out.println("OK");
		}
	}
}
