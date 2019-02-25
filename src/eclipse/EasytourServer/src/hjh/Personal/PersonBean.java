package hjh.Personal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import hjh.DBConn;

public class PersonBean {
	private Connection conn=null; 
    private java.sql.PreparedStatement st=null;
	/**
	 * 返回所有用户
	 * @return
	 */
	public List<PersonInfo> getpersonData(String userid){
        //所有用户的集合
        List<PersonInfo> list=new ArrayList<PersonInfo>();
        //MySQL语句
        String sql="select * from user where userid = ?";
        //连接
        conn= DBConn.getConnection();
        try {
            st= conn.prepareStatement(sql);
            st.setString(1, userid);
            ResultSet rs= st.executeQuery();
            while(rs.next()){
            	PersonInfo info=new PersonInfo();
                info.setUsername(rs.getString("username"));
                info.setSex(rs.getString("sex"));
                info.setEmail(rs.getString("email"));
                info.setPhone(rs.getString("phone"));
                info.setMysignature(rs.getString("mysignature"));
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
	 * 更新个人资料
	 * @param userid 用户唯一id
	 * @param index	更改索引
	 * @param value 更改值
	 * @return 结果为1成功
	 */
	public int UpdatePerson(String userid,String index,String value) {
		int result = -1;
		String sql = "update user set "+index+" = ? where userid = ?";
		conn = DBConn.getConnection();
		try {
            st= conn.prepareStatement(sql);
            st.setString(1, value);
            st.setString(2, userid);
            result= st.executeUpdate();
		}catch(SQLException e) {
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
