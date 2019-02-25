package hjh;
import java.sql.Connection;
import java.sql.DriverManager;

public class DBConn {
	//获得数据库连接
	public static Connection getConnection() {
		Connection con = null;
		//MySql驱动
		String jd = "com.mysql.jdbc.Driver";
		//链接名 本机，端口号：３３０６，　数据库：easytour
		String url = "jdbc:mysql://localhost:3306/easytour";
		//Mysql用户名
		String user = "root";
		//Mysql密码
		String password = "123456";
		try {
			// 加载MySql数据驱动
			Class.forName(jd);
			//创建数据库连接
			con = DriverManager.getConnection(url, user, password);
			System.out.println("加载驱动");
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("连接失败");
			e.printStackTrace();
		}
		return con;
	}
}
