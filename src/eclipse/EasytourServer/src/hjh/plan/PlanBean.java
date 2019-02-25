package hjh.plan;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import hjh.DBConn;

/**
 * 
 * @author 黄家慧
 *
 */
public class PlanBean {
	private Connection conn=null; //�����ݿ����
    private java.sql.PreparedStatement st=null;//��������sql���Ķ���
	/**
	 * 得到所有计划
	 * @param userid	按照userid找到对应所有id
	 * @return 返回一个所有plan的泛型
	 */
	public List<PlanInfo> getPlanData(String userid){
        List<PlanInfo> list=new ArrayList<PlanInfo>();
        //MySQL语句
        String sql="select dest,starttime,endtime,choice_luggage from userplan where userid=?";
        //连接
        conn= DBConn.getConnection();
        try {
            st= conn.prepareStatement(sql);
            st.setString(1, userid);
            ResultSet rs= st.executeQuery();
            while(rs.next()){
            	PlanInfo info=new PlanInfo();
            	System.out.println(rs.getString("dest"));
                list.add(new PlanInfo(rs.getString("dest"),rs.getString("starttime"),rs.getString("endtime"),rs.getString("choice_luggage")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
			if(conn!=null) {
				try {
					conn.close();
				}catch (SQLException e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		}
        return list;
    }
	
	/**
	 * 添加一个plan
	 * @param userid	用户id
	 * @param dest	目的地
	 * @param starttime	出发时间
	 * @param endtime	返回时间
	 * @param choice_luggage	做什么
	 * @return	返回1是成功
	 */
	public int AddPlan(String userid,String dest,String starttime,String endtime,String choice_luggage){
		int result = -1;
		//MySQL语句
		String sql = "insert into userplan (userid,dest,starttime,endtime,choice_luggage) values "
				+ "(?,?,?,?,?)";
        //连接
        conn= DBConn.getConnection();
        try {
            st= conn.prepareStatement(sql);
            st.setString(2, dest);
            st.setString(3, starttime);
            st.setString(4, endtime);
            st.setString(5, choice_luggage);
            st.setString(1, userid);
            result= st.executeUpdate();
                
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
			if(conn!=null) {
				try {
					conn.close();
				}catch (SQLException e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		}
        return result;
    }
}
