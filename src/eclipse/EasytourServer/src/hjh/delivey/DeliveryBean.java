package hjh.delivey;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import hjh.DBConn;
import hjh.Login.UserInfo;

public class DeliveryBean {
	private Connection conn = null;
	private PreparedStatement st = null;
	
	/**
	 * 
	 * @param userid 用户名
	 * @return 返回对应用户名的所有订单列表
	 */
	public List<DeliveryInfo> getDeliveryData(String userid){
        //所有用户的集合
        List<DeliveryInfo> list=new ArrayList<DeliveryInfo>();
        //MySQL语句
        String sql="select now_city,now_address,after_address,number,money from delivery	where userid = ?";
        //连接
        conn= DBConn.getConnection();
        try {
            st= conn.prepareStatement(sql);
            st.setString(1, userid);
            ResultSet rs= st.executeQuery();
            while(rs.next()){
            	DeliveryInfo info=new DeliveryInfo();
                info.setUsrid(userid);
                info.setNow_city(rs.getString("now_city"));
                info.setNow_address(rs.getString("now_address"));
                info.setAfter_address(rs.getString("after_address"));
                info.setNumber(rs.getInt("number"));
                info.setMoney(rs.getString("money"));
                list.add(info);
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
	
	
	public int AddDelivery(String userid,String now_city,String now_address,String after_address,int number,String money){
		int result = -1;
		//MySQL语句
		String sql = "insert into delivery (userid,now_city,now_address,after_address,number,money) values "
				+ "(?,?,?,?,?,?)";
        //连接
        Connection conn= DBConn.getConnection();
        try {
            PreparedStatement st= conn.prepareStatement(sql);
            st.setString(1, userid);
            st.setString(2, now_city);
            st.setString(3, now_address);
            st.setString(4, after_address);
            st.setInt(5, number);
            st.setString(6, money);
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
