package hjh.Tips;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import hjh.DBConn;

public class TipsBean {
	
	/**
	 * 返回所有tips的集合
	 * @return
	 */
	public List<TipscomInfo> SelectAlltip(){
		List<TipscomInfo> data = new ArrayList<TipscomInfo>();
		Connection con = DBConn.getConnection();
		try {
			String sql = "select * from Tips";
			java.sql.PreparedStatement	st = con.prepareStatement(sql);
			
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				String userid = rs.getString("userid");
				String author = rs.getString("author");
				String content = rs.getString("data");
				int pinglun = Integer.parseInt(rs.getString("pinglun"));
				int zan = Integer.parseInt(rs.getString("zan"));
				String where = rs.getString("wherewant");
				
				TipscomInfo info = new TipscomInfo(id,userid,author,content,pinglun,zan,where);
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
	
	/**
	 * 返回对应tip的评论
	 * @param tipid 评论的id 
	 * @return
	 */
	public List<ReplyInfo> SelectAllreply(int tipid){
		List<ReplyInfo> data = new ArrayList<ReplyInfo>();
		Connection con = DBConn.getConnection();
		try {
			String sql = "select * from tipspinglun where tipid = ?";
			java.sql.PreparedStatement	st = con.prepareStatement(sql);
			st.setString(1, Integer.toString(tipid));
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				String content = rs.getString("content");
				String username = rs.getString("username");
				String userid = rs.getString("userid");
				String status = rs.getString("status");
				String time = rs.getString("time");
				
				ReplyInfo info = new ReplyInfo(username,userid,tipid,content,status,time);
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
	
	public int AddTips(String author,String where,String time,String data,String userid){
		int result = -1;
		//MySQL语句
		String sql = "insert into tips (author,wherewant,time,data,userid) values "
				+ "(?,?,?,?,?)";
        //连接
        Connection conn= DBConn.getConnection();
        try {
            PreparedStatement st= conn.prepareStatement(sql);
            st.setString(1, author);
            st.setString(2, where);
            st.setString(3, time);
            st.setString(4, data);
            st.setString(5, userid);
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
	
	public int AddReplys(String tipid,String content,String userid,String username,String time,String status){
		int result = -1;
		//MySQL语句
		String sql = "insert into tipspinglun (tipid,content,userid,username,time,status) values "
				+ "(?,?,?,?,?,?)";
        //连接
        Connection conn= DBConn.getConnection();
        try {
            PreparedStatement st= conn.prepareStatement(sql);
            st.setString(1, tipid);
            st.setString(2, content);
            st.setString(3, userid);
            st.setString(4, username);
            st.setString(5, time);
            st.setString(6, status);
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
