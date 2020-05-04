package util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * jdbc mysqlע���������򡢻�ȡ���Ӷ��󡢹ر���Դ����
 * 
 * @author mengs
 */
public class JdbcUtil {
	private static String url = null;
	private static String user = null;
	private static String password = null;
	private static String driverClass = null;
	
	
	/**
	 * ע����������
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
	 * ��ȡ����
	 * 
	 * @return  Connection���Ӷ���
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
	 * �ر���Դ
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
