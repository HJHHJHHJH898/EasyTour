package hjh.Login;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import hjh.DBConn;

public class LoginBean {
	private Connection conn=null; 
    private java.sql.PreparedStatement st=null;
	/**
	 * 返回所有用户
	 * @return
	 */
	public List<UserInfo> getUserData(){
        //所有用户的集合
        List<UserInfo> list=new ArrayList<UserInfo>();
        //MySQL语句
        String sql="select userid,password,username from user";
        //连接
        conn= DBConn.getConnection();
        try {
            st= conn.prepareStatement(sql);
            ResultSet rs= st.executeQuery();
            while(rs.next()){
                UserInfo info=new UserInfo();
                info.setUserid(rs.getString("userid"));
                info.setPassword(rs.getString("password"));
                info.setUsername(rs.getString("username"));
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
	
	/**
	 * 添加一个新用户
	 * @param userid	用户id
	 * @param password	密码
	 * @param username	用户名
	 * @return 返回1是成功
	 */
	public int AddUser(String userid,String password,String username){
		int result = -1;
		//MySQL语句
		String sql = "insert into user (userid,password,username) values (?,?,?)";
        //建立连接
        conn= DBConn.getConnection();
        try {
            st= conn.prepareStatement(sql);
            st.setString(1, userid);
            st.setString(2, password);
            st.setString(3, username);
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
