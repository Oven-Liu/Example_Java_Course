package util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * jdbc mysql注册驱动程序、获取连接对象、关闭资源方法
 * 
 * @author mengs
 */
public class JdbcUtil {
	private static String url = null;
	private static String user = null;
	private static String password = null;
	private static String driverClass = null;
	
	
	/**
	 * 注册驱动程序
	 */
	static {
		try {
			Properties properties = new Properties();
			InputStream in = JdbcUtil.class.getResourceAsStream("/util/db.properties");
			properties.load(in);
			url = properties.getProperty("url");
			user = properties.getProperty("user");
			password = properties.getProperty("password");
			driverClass = properties.getProperty("driverClass");
			Class.forName(driverClass);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 获取连接
	 * 
	 * @return  Connection连接对象
	 */
	public static Connection getConnection() {
		try {
			Connection con = DriverManager.getConnection(url, user, password);
			return con;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 关闭资源
	 * 
	 * @param con  Connection
	 * @param stmt  Statement
	 * @param rs  ResultSet
	 */
	public static void closeResource(Connection con, Statement stmt, ResultSet rs) {
		if (con != null) {
			try {
				con.close();
				con = null;
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
		if (stmt != null) {
			try {
				stmt.close();
				stmt = null;
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
		if (rs != null) {
			try {
				rs.close();
				rs = null;
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
	}
}
