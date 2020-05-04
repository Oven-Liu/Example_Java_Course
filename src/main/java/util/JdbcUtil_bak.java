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
public class JdbcUtil_bak {
	private static String url = null;
	private static String user = null;
	private static String password = null;
	private static String driverClass = null;
	
	/**
	 * 注册mysql驱动程序
	 */
	static {
		try {
			Properties properties = new Properties();
//			FileInputStream in = new FileInputStream("./src/db.properties");
			/*
			 * 使用类路径的读取方式获取
			 * /：斜杠表示classpath的根目录
			 * 		在java项目中，classpath的根目录从bin目录开始
			 * 		在web中，classpath的根目录从WEB-INF/classes目录开始
			 */
			InputStream in = JdbcUtil_bak.class.getResourceAsStream("/db.properties");
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
	 * 获取连接对象
	 * @return Connection
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
	 * @param connection
	 * @param statement
	 */
	public static void closeResource(Connection con, Statement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
	}
	
	/**
	 * 关闭资源，方法重载
	 * 
	 * @param connection
	 * @param statement
	 * @param presta
	 */
	public static void closeResource(Connection con, Statement stmt, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
				rs = null;	// 建议垃圾回收站回收资源
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
		if (stmt != null) {
			try {
				stmt.close();
				stmt = null;	// 建议垃圾回收站回收资源
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
		if (con != null) {
			try {
				con.close();
				con = null;	// 建议垃圾回收站回收资源
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
	}
}
