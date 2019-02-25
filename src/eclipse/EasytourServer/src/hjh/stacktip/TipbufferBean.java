package hjh.stacktip;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import hjh.DBConn;
import hjh.Tips.ReplyInfo;
import hjh.Tips.TipscomInfo;

public class TipbufferBean {
	public List<BufferInfo> SelectAlltip(String touserid){
		List<BufferInfo> data = new ArrayList<BufferInfo>();
		Connection con = DBConn.getConnection();
		try {
			String sql = "select * from TipBuffer where myto = ?";
			java.sql.PreparedStatement	st = con.prepareStatement(sql);
			st.setString(1, touserid);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				int tipid = rs.getInt("tipid");
				String from = rs.getString("myfrom");
				String content = rs.getString("content");
				
				BufferInfo info = new BufferInfo(tipid, from, touserid, content);
				data.add(info);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			if(con!=null) {
				try {
					con.close();
				}catch (SQLException e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		}
		return data;
	}
	
	
	
	public int AddBuffer(int tipid,String from,String to,String content){
		int result = -1;
		//MySQL语句
		String sql = "insert into TipBuffer (tipid,myfrom,myto,content) values "
				+ "(?,?,?,?)";
        //连接
        Connection conn= DBConn.getConnection();
        try {
            PreparedStatement st= conn.prepareStatement(sql);
            st.setInt(1, tipid);
            st.setString(2, from);
            st.setString(3, to);
            st.setString(4, content);
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
	
	public int DeleteTips(String to){
		int result = -1;
		//MySQL语句
		String sql = "delete from TipBuffer where myto = ? ";
        //连接
        Connection conn= DBConn.getConnection();
        try {
            PreparedStatement st= conn.prepareStatement(sql);
            st.setString(1, to);
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
